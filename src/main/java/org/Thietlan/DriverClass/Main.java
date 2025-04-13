package org.Thietlan.DriverClass;


// Simple operation
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

import org.Thietlan.Utilities.Utils;
import io.nayuki.qrcodegen.QrCode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner myObj = new Scanner(System.in);
        // Create a Scanner object
        Utils obj = new Utils();
        obj.sayHello();
        System.out.println("Enter a cool thing to put into a qr code:");
        String QRCodeText = myObj.nextLine();
        QrCode qr0 = QrCode.encodeText(QRCodeText, QrCode.Ecc.MEDIUM);
        BufferedImage img = toImage(qr0, 20, 1,0xffffff,0x000000);  // See QrCodeGeneratorDemo
        ImageIO.write(img, "png", new File("qr-code.png"));
          // Output user input


//        System.out.println(extractFieldFromTOMLFile("testNetworkFile.txt","psk"));
//        System.out.println(extractFieldFromTOMLFile("testNetworkFile.txt","ssid"));
//        String ssid = extractFieldFromTOMLFile("testNetworkFile.txt","ssid");
//        String psk = extractFieldFromTOMLFile("testNetworkFile.txt","psk");
//        Set<String> files= setOfWiFiFiles("src\\main\\java\\org\\Thietlan\\Main.java");
//        for (String file : files) {
//            System.out.println(file);
//        }
//        generateWiFiQRCode(extractFieldFromTOMLFile("testNetworkFile.txt","ssid"),extractFieldFromTOMLFile("testNetworkFile.txt","psk"));

    }



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
        BufferedImage img = toImage(qr0, 20, 1,0xcc185a,0x2721d9);  // See QrCodeGeneratorDemo
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