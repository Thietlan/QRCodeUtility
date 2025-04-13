package org.Thietlan.DriverClass;


// Simple operation
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

import org.Thietlan.Logic.Manager;
import org.Thietlan.Utilities.Utils;
import io.nayuki.qrcodegen.QrCode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    public static  void main(String[] args) throws IOException {
//        Scanner myObj = new Scanner(System.in);
//        // Create a Scanner object
//        Utils obj = new Utils();
//        obj.sayHello();
//        System.out.println("Enter a cool thing to put into a qr code:");
//        String QRCodeText = myObj.nextLine();
//        QrCode qr0 = QrCode.encodeText(QRCodeText, QrCode.Ecc.MEDIUM);
//        BufferedImage img = toImage(qr0, 20, 1,0xffffff,0x000000);  // See QrCodeGeneratorDemo
//        ImageIO.write(img, "png", new File("qr-code.png"));
//          // Output user input


//        System.out.println(extractFieldFromTOMLFile("testNetworkFile.txt","psk"));
//        System.out.println(extractFieldFromTOMLFile("testNetworkFile.txt","ssid"));
//        String ssid = extractFieldFromTOMLFile("testNetworkFile.txt","ssid");
//        String psk = extractFieldFromTOMLFile("testNetworkFile.txt","psk");
//        Set<String> files= setOfWiFiFiles("src\\main\\java\\org\\Thietlan\\Main.java");
//        for (String file : files) {
//            System.out.println(file);
//        }
//        generateWiFiQRCode(extractFieldFromTOMLFile("testNetworkFile.txt","ssid"),extractFieldFromTOMLFile("testNetworkFile.txt","psk"));


        Manager mainManager=new Manager();
        mainManager.run();


    }











}