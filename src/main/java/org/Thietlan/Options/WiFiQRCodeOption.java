package org.Thietlan.Options;

import org.Thietlan.Interfaces.Option;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

import static org.Thietlan.Utilities.QRCodeSimplified.generateWiFiQRCode;

public class WiFiQRCodeOption implements Option {
    private HashMap<String, String> parameters=new HashMap<>();
    private final String activationString;
    public WiFiQRCodeOption(String activationString) {
        this.activationString = activationString;
        parameters.put("ssid","");
        parameters.put("password","");
    }

    @Override
    public String getActivationString() {
        return activationString;
    }

    @Override
    public int run() throws IOException {
        //System.out.println(parameters.get("password"));
        generateWiFiQRCode(parameters.get("ssid"),parameters.get("password"));
        return 0;
    }

    @Override
    public void printOptionInfo() {
        System.out.printf("%s. %s\n",this.getActivationString(),this.parameters.get("ssid"));

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
