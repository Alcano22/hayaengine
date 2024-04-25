package de.haya.engine.window;

import de.haya.engine.input.Input;
import de.haya.engine.scene.SceneManager;
import de.haya.engine.util.Time;
import de.haya.engine.util.Version;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Window extends JFrame {

    private final Canvas rootCanvas;

    public Window(String title) {
        super(title);

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

        Input input = Input.get();
        this.addKeyListener(input.getKeyboard());
        this.addMouseListener(input.getMouse());

        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }

    public void update() {
        this.requestFocus();
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

            SceneManager.render(gfx);

            this.renderDebugInfo(gfx);

            bs.show();
        } finally {
            gfx.dispose();
        }
    }

    private void renderDebugInfo(Graphics gfx) {
        gfx.setColor(Color.YELLOW);
        gfx.setFont(gfx.getFont().deriveFont(14f));
        gfx.drawString("The Legend of Yan", 25, 25);
        gfx.drawString("Running on " + Version.get() + "!", 25, 25 + gfx.getFont().getSize() + 5);
        gfx.drawString(Time.fps + " FPS", 25, 25 + gfx.getFont().getSize() * 2 + 10);
    }
}
