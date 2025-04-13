package org.Thietlan.Utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldExtractors {

    public static String extractFieldFromTOMLFile(String path,String field) {
        String regexField="%s=(.*)";
        regexField= String.format(regexField,field);
        try {
            Scanner file= new Scanner(new File(path));

            Pattern pattern = Pattern.compile(regexField);

            String password = file.findWithinHorizon(pattern, 0);

            if (password != null) {

                Matcher matcher = pattern.matcher(password);
                matcher.find();
                return matcher.group(1);
            } else {
                System.out.printf("No field %s found in file\n", field);
                return null;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return null;

    }
}
