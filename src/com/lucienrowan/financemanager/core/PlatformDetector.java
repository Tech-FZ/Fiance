package com.lucienrowan.financemanager.core;

public class PlatformDetector {
    public static String getFilePath() {
        String filePath = System.getProperty("user.home");

        if (System.getProperty("os.name").startsWith("Windows")) {
            if (!filePath.endsWith("/") && !filePath.endsWith("\\")) {
                filePath = filePath + "\\";
            }

            filePath += "AppData\\Roaming\\Fiancé";
        }

        else if (System.getProperty("os.name").startsWith("Linux")) {
            if (!filePath.endsWith("/") && !filePath.endsWith("\\")) {
                filePath = filePath + "/";
            }

            filePath += ".local/share/Fiancé";
        }

        // todo: create directory if nonexistant

        return filePath;
    }
}
