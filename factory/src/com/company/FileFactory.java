package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class FileFactory extends ObjectFactory<FileWriter> {

    AtomicInteger currentLogFileId = new AtomicInteger(0);

    @Override
    public FileWriter createObject() {

        String fileLogPath = System.getProperty("user.dir") + File.separator + "Log";
        File logDir = new File(fileLogPath);

        if (currentLogFileId.get() == 0) {
            rmLogDir(logDir);
        }
        if (!logDir.exists()) {
            logDir.mkdir();
        }
        String logFilePath = fileLogPath + File.separator + currentLogFileId.getAndIncrement() + ".txt";
        File logFile = new File(logFilePath);
        try {
            return new FileWriter(logFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void rmLogDir(final File folder) {
        if (folder.isDirectory()) {
            File[] list = folder.listFiles();
            if (list != null) {
                for (int i = 0; i < list.length; i++) {
                    File tmpF = list[i];
                    if (tmpF.isDirectory()) {
                        rmLogDir(tmpF);
                    }
                    tmpF.delete();
                }
            }
        }
    }

}
