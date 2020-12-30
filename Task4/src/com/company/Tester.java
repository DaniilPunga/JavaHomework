package com.company;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


class Tester {

    @Test
    void testWithAddedFiles() throws IOException {
        // Директория объявлена тоько одн раз, существует пустая поддиректория
        List<String> space = createDirectoryWithFiles();
        File directory = new File(space.get(0));
        String outputPath = space.get(1);
        new Filer(outputPath, directory);
        String result = directory.getCanonicalFile() + "\\FILE1.doc\n" +
                directory.getCanonicalFile() + "\\FILE2.doc\n" +
                directory.getCanonicalFile() + "\\FILE3.doc\n" +
                directory.getCanonicalFile() + "\\FILE4.doc\n" +
                directory.getCanonicalFile() + "\\FOLDER\n" +
                directory.getCanonicalFile() + "\\output.txt\n";
        FileInputStream fin = new FileInputStream(outputPath);
        String realRes = new String();
        int i = -1;
        while ((i = fin.read()) != -1) {
            realRes = realRes + (char) i;
        }

        Assertions.assertEquals(result, realRes);
    }

    @Test
    void testWithAddedFileswithoutOut() throws IOException {
        // Директория объявлена тоько одн раз, существует пустая поддиректория, не существует файла с выводом,
        // тогда будет выведена ошибка, что файл не объявлен
        List<String> space = createDirectoryWithFilesWithoutoutput();
        File directory = new File(space.get(0));
        String outputPath = space.get(1);
        Assertions.assertThrows(FileNotFoundException.class, () -> new Filer(outputPath, directory));

    }

    @Test
    void testWithDoubledDirectory() throws IOException {
        //Если директория уже существует (была созданна до этого) и сейчас,
        // то файлы будут дублироваться
        List<String> space = createDirectoryWithFiles();
        File directory = new File(space.get(0));
        String outputPath = space.get(1);
        new Filer(outputPath, directory);
        String result = directory.getCanonicalFile() + "\\FILE1.doc\n" +
                directory.getCanonicalFile() + "\\FILE2.doc\n" +
                directory.getCanonicalFile() + "\\FILE3.doc\n" +
                directory.getCanonicalFile() + "\\FILE4.doc\n" +
                directory.getCanonicalFile() + "\\FOLDER\n" +
                directory.getCanonicalFile() + "\\output.txt\n" +
                directory.getCanonicalFile() + "\\FILE1.doc\n" +
                directory.getCanonicalFile() + "\\FILE2.doc\n" +
                directory.getCanonicalFile() + "\\FILE3.doc\n" +
                directory.getCanonicalFile() + "\\FILE4.doc\n" +
                directory.getCanonicalFile() + "\\FOLDER\n" +
                directory.getCanonicalFile() + "\\output.txt\n";
        FileInputStream fin = new FileInputStream(outputPath);
        String realRes = new String();
        int i = -1;
        while ((i = fin.read()) != -1) {
            realRes = realRes + (char) i;
        }

        Assertions.assertEquals(result, realRes);
    }

    @Test
    void testWithAddedFilesinDirect() throws IOException {
        // Директория с под директорией, содержащей файлы
        List<String> space = createDirectoryWithDirectory();
        File directory = new File(space.get(0));
        String outputPath = space.get(1);
        new Filer(outputPath, directory);
        String result = directory.getCanonicalFile() + "\\FILE1.doc\n" +
                directory.getCanonicalFile() + "\\FILE2.doc\n" +
                directory.getCanonicalFile() + "\\FILE3.doc\n" +
                directory.getCanonicalFile() + "\\FILE4.doc\n" +
                directory.getCanonicalFile() + "\\FOLDER\n" +
                directory.getCanonicalFile() + "\\FOLDER\\FILE1.doc\n" +
                directory.getCanonicalFile() + "\\FOLDER\\FILE2.doc\n" +
                directory.getCanonicalFile() + "\\output.txt\n";
        FileInputStream fin = new FileInputStream(outputPath);
        String realRes = new String();
        int i = -1;
        while ((i = fin.read()) != -1) {
            realRes = realRes + (char) i;
        }

        Assertions.assertEquals(result, realRes);
    }


    @Test
    void testNullDirectory() throws IOException {
        // Пустая Директория
        createDirectoryWithFiles();
        Assertions.assertThrows(FileNotFoundException.class, () -> new Filer("./DIRECTORY/NULL/output.txt", new File("./DIRECTORY/NULL")));
    }

    @Test
    void testNullFile() throws IOException {
        // Пустой файл
        createDirectoryWithFiles();
        Assertions.assertThrows(FileNotFoundException.class, () -> new Filer("./DIRECTORY/FOLDER/file.txt", new File("./DIRECTORY/FOLDER")));
    }


    static public boolean delDirectory(File dir) throws IOException {
        if (dir.exists()) {
            File[] allFiles = dir.listFiles();
            for (File file : allFiles) {
                if (file.isDirectory()) {
                    delDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        return (dir.delete());
    }


    private List<String> createDirectoryWithFiles() throws IOException {
        File directory = new File("./DIRECTORY").getCanonicalFile();
        if (directory.exists()) {
            delDirectory(directory);
        }
        directory.mkdir();
        List<String> res = new LinkedList<>();
        res.add(directory.getName());
        for (int i = 1; i < 5; ++i) {
            String newPath = "./DIRECTORY/FILE" + i + ".doc";
            File newFile = new File(newPath);
            newFile.createNewFile();
        }
        String newPath = "./DIRECTORY/FOLDER";

        File newDir = new File(newPath);
        newDir.mkdir();

        String outputPath = "./DIRECTORY/output.txt";
        res.add(outputPath);
        File outFile = new File(outputPath);
        outFile.createNewFile();
        return res;
    }

    private List<String> createDirectoryWithFilesWithoutoutput() throws IOException {
        File directory = new File("./DIRECTORY3").getCanonicalFile();
        if (directory.exists()) {
            delDirectory(directory);
        }
        directory.mkdir();
        List<String> res = new LinkedList<>();
        res.add(directory.getName());
        for (int i = 1; i < 5; ++i) {
            String newPath = "./DIRECTORY3/FILE" + i + ".doc";
            File newFile = new File(newPath);
            newFile.createNewFile();
        }
        String newPath = "./DIRECTORY3/FOLDER";

        File newDir = new File(newPath);
        newDir.mkdir();

        String outputPath = "./DIRECTORY3/output.txt";
        res.add(outputPath);
        //File outFile = new File(outputPath);
        //outFile.createNewFile();
        return res;
    }

    private List<String> createDirectoryWithDirectory() throws IOException {
        File directory = new File("./DIRECTORY2").getCanonicalFile();
        if (directory.exists()) {
            delDirectory(directory);
        }
        directory.mkdir();
        List<String> res = new LinkedList<>();
        res.add(directory.getName());
        for (int i = 1; i < 5; ++i) {
            String newPath = "./DIRECTORY2/FILE" + i + ".doc";
            File newFile = new File(newPath);
            newFile.createNewFile();
        }
        String outputPath = "./DIRECTORY2/output.txt";
        res.add(outputPath);
        String newPath = "./DIRECTORY2/FOLDER";

        File newDir = new File(newPath);
        newDir.mkdir();
        for (int i = 1; i < 3; ++i) {
            String newPathes = "./DIRECTORY2/FOLDER/FILE" + i + ".doc";
            File newFiles = new File(newPathes);
            newFiles.createNewFile();
        }

        File outFile = new File(outputPath);
        outFile.createNewFile();
        return res;
    }

}