package com.lucienrowan.financemanager.core;

import java.io.File;

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

        createAppDirectory(filePath); // create directory if nonexistant
        return filePath;
    }

    private static void createAppDirectory(String filePath) {
        File directory = new File(filePath);

        if (!directory.exists() && directory.mkdir()) {
            // todo: report that directory was created successfully
        }

        else {
            // todo: report that directory already exists
        }
    }
}
