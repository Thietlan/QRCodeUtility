package org.Thietlan.Options;

import io.nayuki.qrcodegen.QrCode;
import org.Thietlan.Interfaces.Option;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

import static org.Thietlan.Utilities.QRCodeSimplified.stringToQR;

public class StringToQRCodeOption implements Option {

    private HashMap<String, String> parameters=new HashMap<>();
    private final String stringActivation;

    public StringToQRCodeOption(String stringActivation) {
        this.stringActivation = stringActivation;
        parameters.put("text","");

    }

    @Override
    public String getActivationString() {
        return stringActivation;
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

    @Override
    public TreeMap<String, Option> getNextOptions() {
        return null;
    }


}
