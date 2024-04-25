package de.haya.engine.window;

import de.haya.engine.input.Input;
import de.haya.engine.input.KeyCode;
import de.haya.engine.scene.SceneManager;
import de.haya.engine.util.Time;
import de.haya.engine.util.Version;
import org.joml.Vector2f;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Window extends JFrame {

    private final Canvas rootCanvas;
    private final WindowDebugInfo debugInfo;

    public Window(String title) {
        super(title);

        this.rootCanvas = new Canvas();
        this.debugInfo = new WindowDebugInfo(new Vector2f(25f, 25f));
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

        this.debugInfo.update();
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

            this.debugInfo.render(gfx);

            bs.show();
        } finally {
            gfx.dispose();
        }
    }
}
