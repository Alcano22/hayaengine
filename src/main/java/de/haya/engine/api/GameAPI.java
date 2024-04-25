package de.haya.engine.api;

public interface GameAPI {

    void init();
    Props getProps();

    record Props(String title) {}

}
