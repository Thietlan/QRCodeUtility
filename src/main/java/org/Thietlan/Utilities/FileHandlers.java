package org.Thietlan.Utilities;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHandlers {
    public static Set<String> setOfWiFiFiles(String dir) {


        File dirFile = new File(dir);
        if (dirFile.isDirectory() && dirFile.exists()) {
            return Stream.of(dirFile.listFiles())
                    .filter(file -> !file.isDirectory())
                    .map(File::getName)
                    .collect(Collectors.toSet());
        }
        else return null;


    }
}
