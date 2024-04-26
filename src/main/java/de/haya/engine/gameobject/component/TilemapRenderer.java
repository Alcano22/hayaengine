package de.haya.engine.gameobject.component;

import de.haya.engine.core.Engine;
import de.haya.engine.scene.Tilemap;
import de.haya.engine.window.Window;
import org.joml.Vector2f;

import java.awt.*;

public class TilemapRenderer extends Component {

    public Tilemap tilemap;
    public boolean center = false, stretch = false;

    @Override
    public void render(Graphics gfx) {
        Window window = Engine.get().window;
        if (this.stretch) {
            this.tilemap.render(gfx, 0, 0, window.getWidth(), window.getHeight());
            return;
        }

        int tilemapWidth = this.tilemap.getWidth();
        int tilemapHeight = this.tilemap.getHeight();
        Vector2f scale = this.transform().scale();
        int scaledWidth = tilemapWidth * (int) scale.x;
        int scaledHeight = tilemapHeight * (int) scale.y;
        Vector2f pos = this.transform().position();
        int x = center ? (window.getWidth() - scaledWidth) / 2 : (int) pos.x;
        int y = center ? (window.getHeight() - scaledHeight) / 2 : (int) pos.y;

        this.tilemap.render(gfx, x, y, scaledWidth, scaledHeight);
    }
}
