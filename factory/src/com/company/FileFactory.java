package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class FileFactory extends ObjectFactory<FileWriter> {

    AtomicInteger currentLogFileId = new AtomicInteger(0);
    String fileLogPath = System.getProperty("user.home") + File.separator + "Documents";

    @Override
    public FileWriter createObject() {

        File logFileDir = new File(fileLogPath);

        if (!logFileDir.exists()) {
            logFileDir.mkdir();
        }
        Integer FileId = currentLogFileId.getAndIncrement();
        String StrFileId = FileId.toString();
        String logFilePath = fileLogPath + File.separator + StrFileId + ".txt";
        File logFile = new File(logFilePath);
        try {
            return new FileWriter(logFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
