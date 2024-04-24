package de.haya.tloy.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Version {

    private Version() {}

    public static String get() {
        try (InputStream in = Version.class.getResourceAsStream("/version.properties")) {
            Properties props = new Properties();
            props.load(in);
            return props.getProperty("version");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
