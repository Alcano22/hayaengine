package de.haya.engine.scene;

import de.haya.engine.logging.Log;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tilemap {

	private final String filepath;

	private int width, height;
	private int[] data;
	private BufferedImage tilemap;

	public Tilemap(String filepath) {
		this.filepath = filepath;
		this.loadTMXFile();
	}

	private void loadTMXFile() {
		File file = new File(this.filepath);
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
			doc.getDocumentElement().normalize();

			String imagePath = "";
			int tileWidth = 0, tileHeight = 0, rows = 0, columns = 0;

			Node layerNode = doc.getElementsByTagName("layer").item(0);
			if (layerNode.getNodeType() == Node.ELEMENT_NODE) {
				Element layerElement = (Element) layerNode;
				this.width = Integer.parseInt(layerElement.getAttribute("width"));
				this.height = Integer.parseInt(layerElement.getAttribute("height"));

				Node dataNode = layerElement.getElementsByTagName("data").item(0);
				if (dataNode.getNodeType() == Node.ELEMENT_NODE) {
					Element dataElement = (Element) dataNode;
					String csvData = dataElement.getTextContent().replaceAll("\\s+", "");

					String[] splitCSVData = csvData.split(",");
					this.data = new int[splitCSVData.length];
					for (int i = 0; i < splitCSVData.length; i++)
						this.data[i] = Integer.parseInt(splitCSVData[i]) - 1;
				}
			}

			Node tilesetNode = doc.getElementsByTagName("tileset").item(0);
			if (tilesetNode.getNodeType() == Node.ELEMENT_NODE) {
				Element tilesetElement = (Element) tilesetNode;
				int firstGid = Integer.parseInt(tilesetElement.getAttribute("firstgid"));
				String source = tilesetElement.getAttribute("source");

				String tsxPath = file.getParent() + "/" + source;
				File tsxFile = new File(tsxPath);

				Document tsxDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(tsxFile);
				doc.getDocumentElement().normalize();

				Node tsxTilesetNode = tsxDoc.getElementsByTagName("tileset").item(0);
				if (tsxTilesetNode.getNodeType() == Node.ELEMENT_NODE) {
					Element tsxTilesetElement = (Element) tsxTilesetNode;
					tileWidth = Integer.parseInt(tsxTilesetElement.getAttribute("tilewidth"));
					tileHeight = Integer.parseInt(tsxTilesetElement.getAttribute("tileheight"));
					int tileCount = Integer.parseInt(tsxTilesetElement.getAttribute("tilecount"));
					columns = Integer.parseInt(tsxTilesetElement.getAttribute("columns"));
					rows = tileCount / columns;

					Node imageNode = tsxTilesetElement.getElementsByTagName("image").item(0);
					if (imageNode.getNodeType() == Node.ELEMENT_NODE) {
						Element imageElement = (Element) imageNode;
						String imageSource = imageElement.getAttribute("source");
						imagePath = tsxFile.getParent() + "/" + imageSource;
					}
				}
			}

			this.loadTilemap(imagePath, tileWidth, tileHeight, rows, columns);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			Log.error(e);
		}
	}

	private void loadTilemap(String imagePath, int tileWidth, int tileHeight, int rows, int columns) {
		this.tilemap = new BufferedImage(this.width * tileWidth, this.height * tileHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics tilemapGFX = this.tilemap.createGraphics();

		try {
			BufferedImage image = ImageIO.read(new File(imagePath));
			int index = 0;
			for (int tileY = 0; tileY < this.height; tileY++) {
				for (int tileX = 0; tileX < this.width; tileX++) {
					int data = this.data[index];
					int subTileX = data % columns;
					int subTileY = data / columns;
					BufferedImage tile = image.getSubimage(subTileX * tileWidth, subTileY * tileHeight, tileWidth, tileHeight);
					tilemapGFX.drawImage(tile, tileX * tileWidth, tileY * tileHeight, tileWidth, tileHeight, null);

					index++;
				}
			}
		} catch (IOException e) {
			Log.error(e);
		} finally {
			tilemapGFX.dispose();
		}
	}

	public void render(Graphics gfx, int x, int y, int width, int height) {
		gfx.drawImage(this.tilemap, x, y, width, height, null);
	}

	public void render(Graphics gfx, int x, int y) {
		this.render(gfx, x, y, this.tilemap.getWidth(), this.tilemap.getHeight());
	}

	public void render(Graphics gfx) {
		this.render(gfx, 0, 0);
	}

	public void renderScaled(Graphics gfx, int x, int y, float scaleX, float scaleY) {
		gfx.drawImage(this.tilemap, x, y,
				(int) (this.tilemap.getWidth() * scaleX),
				(int) (this.tilemap.getHeight() * scaleY),
				null
		);
	}

	public void renderScaled(Graphics gfx, int x, int y) {
		this.renderScaled(gfx, x, y, 1f, 1f);
	}

	public void renderScaled(Graphics gfx) {
		this.renderScaled(gfx, 0, 0);
	}

	public int getWidth() {
		return this.tilemap.getWidth();
	}

	public int getHeight() {
		return this.tilemap.getHeight();
	}
}
