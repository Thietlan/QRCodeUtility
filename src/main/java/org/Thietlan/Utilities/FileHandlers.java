package org.Thietlan.Utilities;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHandlers {
    private static Set<String> setOfWiFiFiles(String dir) {


        File dirFile = new File(dir);
        System.out.println(dirFile.isDirectory());
        if (dirFile.isDirectory() && dirFile.exists()) {
            System.out.println("got here");
            return Stream.of(dirFile.listFiles())
                    .filter(file -> !file.isDirectory())
                    .map(File::getName)
                    .collect(Collectors.toSet());
        }
        else return null;


    }
}
