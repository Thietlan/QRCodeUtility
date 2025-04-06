package org.example;


// Simple operation
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.imageio.ImageIO;
import io.nayuki.qrcodegen.QrCode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import io.nayuki.qrcodegen.QrSegment;
import io.nayuki.qrcodegen.QrSegmentAdvanced;


public class Main {
    public static void main(String[] args) throws IOException {

        //generateWiFiQRCode("TP-Link_acasa5Ghz","WelcomeTakeASeat123");
        /*Scanner s = new Scanner(new File("testNetworkFile.txt"));
        Pattern pattern = Pattern.compile("psk=(.*)");
        String nextMatch = s.findWithinHorizon(pattern, 0);
        Matcher matcher = pattern.matcher(nextMatch);

        if (matcher.find()) {
            String pskValue = matcher.group(1); // Capture group 1
            System.out.println("Extracted PSK: " + pskValue);
        } else {
            System.out.println("No match found.");
        }
        System.out.println(nextMatch);
        */

        System.out.println(extractFieldFromTOMLFile("testNetworkFile.txt","psk"));
        System.out.println(extractFieldFromTOMLFile("testNetworkFile.txt","ssid"));
    }




    private static String extractFieldFromTOMLFile(String path,String field) {
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


    private static void generateWiFiQRCode(String SSID, String psk) throws IOException {
        String WiFi="WIFI:S:%s;T:WPA;P:%s;;";
        WiFi=String.format(WiFi,SSID,psk);
        QrCode qr0 = QrCode.encodeText(WiFi, QrCode.Ecc.MEDIUM);
        //QrCodeGeneratorDemo.doBasicDemo();
        BufferedImage img = toImage(qr0, 20, 1,0xFFFFFF,0x000000);  // See QrCodeGeneratorDemo
        ImageIO.write(img, "png", new File("qr-code.png"));
    }

    private static BufferedImage toImage(QrCode qr, int scale, int border, int lightColor, int darkColor) {
        Objects.requireNonNull(qr);
        if (scale <= 0 || border < 0)
            throw new IllegalArgumentException("Value out of range");
        if (border > Integer.MAX_VALUE / 2 || qr.size + border * 2L > Integer.MAX_VALUE / scale)
            throw new IllegalArgumentException("Scale or border too large");

        BufferedImage result = new BufferedImage((qr.size + border * 2) * scale, (qr.size + border * 2) * scale, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < result.getHeight(); y++) {
            for (int x = 0; x < result.getWidth(); x++) {
                boolean color = qr.getModule(x / scale - border, y / scale - border);
                result.setRGB(x, y, color ? darkColor : lightColor);
            }
        }
        return result;
    }

}