package org.global.api.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLog {
    private static final Logger logger = LoggerFactory.getLogger(AppLog.class);

    public static void debug(String message, Object... args) {
        if (logger.isDebugEnabled()) {
            logger.debug(message, args);
        } else {
            // Fallback for simple console logging if logback isn't fully configured
            System.out.println("[DEBUG] " + message + " " + (args.length > 0 ? args[0] : ""));
        }
    }

    public static void info(String message, Object... args) {
        logger.info(message, args);
    }

    public static void error(String message, Throwable t) {
        logger.error(message, t);
    }
}
