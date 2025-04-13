package org.Thietlan.Options;

import io.nayuki.qrcodegen.QrCode;
import org.Thietlan.Interfaces.Option;
import org.Thietlan.Utilities.FieldExtractors;
import org.Thietlan.Utilities.QRCodeSimplified;
import org.Thietlan.Utilities.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import static org.Thietlan.Utilities.QRCodeSimplified.stringToQR;

public class StringToQRCodeOption implements Option {
    QRCodeSimplified qrCodeUtility=new QRCodeSimplified();

    private HashMap<String, String> parameters=new HashMap<>();
    public StringToQRCodeOption() {
        parameters.put("text","");

    }

    @Override
    public String getActivationString() {
        return "1";
    }

    @Override
    public int run() throws IOException {
        stringToQR(parameters.get("text"));
        return 0;
    }

    @Override
    public void printOptionInfo() {
        System.out.printf("%s. Enter something cool to put inside of a QR Code\n",this.getActivationString());

    }

    @Override
    public void setParameters(HashMap<String, String> parameters) {
        this.parameters=parameters;
    }

    @Override
    public Set<String> getRequiredParameters() {
        return parameters.keySet();
    }


}
