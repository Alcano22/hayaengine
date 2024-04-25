package de.haya.engine.core;

import de.haya.engine.api.GameAPI;
import de.haya.engine.api.WindowProps;
import de.haya.engine.input.Input;
import de.haya.engine.scene.SceneManager;
import de.haya.engine.window.Window;

public class Engine {

    private static Engine instance;

    public final Window window;

    private final GameAPI game;
    private final GameLoop loop;

    public Engine(GameAPI game) {
        instance = this;

        this.game = game;

        WindowProps props = this.game.getWindowProps();

        this.window = new Window(props.title());
        this.window.run();

        this.loop = new GameLoop(this, props.limitFPS(), props.targetFPS());

        this.game.init();
    }

    public void run() {
        this.loop.start();
    }

    public void update() {
        this.window.update();
        Input.get().endFrame();
        SceneManager.update();
    }

    public void render() {
        this.window.render();
    }

    public static Engine get() {
        return instance;
    }

}
