package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


class QuadraticEquationTest {

    @Test
    void testMainLogSize() {
        String[] poolSizes = {"4", "8"};
        QuadraticEquation.main(poolSizes);
        String fileLogPath = System.getProperty("user.dir") + File.separator + "Log";
        File dir = new File(fileLogPath);
        File[] arrFiles = dir.listFiles();
        List<File> lstFiles = Arrays.asList(arrFiles);
        Assertions.assertEquals(4, lstFiles.size());
    }

    @Test
    void testMainLogFilesSize() {
        String[] poolSizes = {"1", "8"};
        QuadraticEquation.main(poolSizes);
        String fileLogPath = System.getProperty("user.dir") + File.separator + "Log";
        File dir = new File(fileLogPath);
        File[] arrFiles = dir.listFiles();
        List<File> lstFiles = Arrays.asList(arrFiles);
        for (File outputFile : lstFiles) {
            Stream<String> lines = null;
            try {
                lines = Files.lines(outputFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Assertions.assertEquals(10000, lines.count());
        }
    }

}
