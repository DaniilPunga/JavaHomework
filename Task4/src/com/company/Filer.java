package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

public class Filer {

    public Filer(String out, File dir) throws IOException {
        if (!dir.exists()) {
            throw new FileNotFoundException("Directory is not exist! Please enter other file or directory");
        } else if (!(new File(out).getCanonicalFile().exists())) {
            throw new FileNotFoundException("File is not exist! Please enter other file or directory");
        }
        WriteFilesInDirectory(dir, out);

    }


    private void WriteFilesInDirectory(File dir, String output) {
        try (FileOutputStream out = new FileOutputStream(output, true)) {
            for (File file : dir.listFiles()) {

                if (file.isDirectory()) {
                    out.write((file.getCanonicalFile() + "\n").getBytes());
                    WriteFilesInDirectory(file.getCanonicalFile(), output);
                } else if (file.isFile()) {
                    out.write((file.getCanonicalFile() + "\n").getBytes());
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException();
        }

    }


}