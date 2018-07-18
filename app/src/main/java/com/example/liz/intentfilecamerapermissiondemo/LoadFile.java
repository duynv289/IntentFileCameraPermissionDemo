package com.example.liz.intentfilecamerapermissiondemo;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadFile {
    public static List<String> loadImageFileName(File rootDirectory, String[] extensions) {
        List<String> fileNames = new ArrayList<>();
        List<File> files = Arrays.asList(rootDirectory.listFiles());
        for(File file: files) {
            if(file.isFile()) {
                String name = file.getAbsolutePath();
                for(String extension: extensions) {
                    if(name.endsWith(extension)) {
                        fileNames.add(name);
                    }
                }
            } else if(file.isDirectory()) {
                fileNames.addAll(loadImageFileName(file, extensions));
            }
        }
        return fileNames;
    }
}
