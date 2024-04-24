package de.haya.tloy.window;

import de.haya.tloy.util.Time;
import de.haya.tloy.util.Version;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Window extends JFrame {

    private static final int WIDTH = 1024, HEIGHT = 720;
    private static final String TITLE = "The Legend of Yan | " + Version.get();

    private final Canvas rootCanvas;

    public Window() {
        super(TITLE);

        this.rootCanvas = new Canvas();
    }

    public void run() {
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setUndecorated(true);
        this.setExtendedState(MAXIMIZED_BOTH);

        this.rootCanvas.setSize(this.getWidth(), this.getHeight());
        this.add(this.rootCanvas);
        this.pack();

        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }

    public void render() {
        BufferStrategy bs = this.rootCanvas.getBufferStrategy();
        if (bs == null) {
            this.rootCanvas.createBufferStrategy(3);
            return;
        }

        Graphics gfx = bs.getDrawGraphics();
        try {
            gfx.setColor(Color.BLACK);
            gfx.fillRect(0, 0, this.getWidth(), this.getHeight());

            this.renderDebugInfo(gfx);
        } finally {
            gfx.dispose();
        }

        bs.show();
    }

    private void renderDebugInfo(Graphics gfx) {
        gfx.setColor(Color.YELLOW);
        gfx.setFont(gfx.getFont().deriveFont(14f));
        gfx.drawString("The Legend of Yan", 25, 25);
        gfx.drawString("Running on " + Version.get() + "!", 25, 25 + gfx.getFont().getSize() + 5);
        gfx.drawString(Time.fps + " FPS", 25, 25 + gfx.getFont().getSize() * 2 + 10);
    }
}
