package org.Thietlan.Utilities;

import io.nayuki.qrcodegen.QrCode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class QRCodeSimplified {


    public  static void generateWiFiQRCode(String SSID, String psk) throws IOException {
        String WiFi="WIFI:S:%s;T:WPA;P:%s;;";
        WiFi=String.format(WiFi,SSID,psk);
        stringToQR(WiFi);
    }
    public static void stringToQR(String s) throws IOException {
        QrCode qr0 = QrCode.encodeText(s, QrCode.Ecc.MEDIUM);
        BufferedImage img = toImage(qr0, 20, 1,0xffffff,0x000000);  // See QrCodeGeneratorDemo
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
