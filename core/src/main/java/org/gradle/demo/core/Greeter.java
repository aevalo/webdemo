package org.gradle.demo.core;

import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Greeter {
    private static final Logger logger = LogManager.getLogger(Greeter.class.getName());
    private static Greeter instance = null;
    private Greeter() {}

    public static synchronized Greeter getInstance() {
        if (instance == null) {
            logger.debug("Instantiating Greeter...");
            instance = new Greeter();
        }
        return instance;
    }

    public String greet(String maybeName) {
        logger.debug(String.format("Received %s", maybeName));
        String target = Optional.ofNullable(maybeName).filter(name -> !name.isEmpty()).orElse("World");
        return String.format("Hello, %s!!!", target);
    }
}