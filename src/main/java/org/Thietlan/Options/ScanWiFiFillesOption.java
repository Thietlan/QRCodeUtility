package org.Thietlan.Options;

import org.Thietlan.Interfaces.Option;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

import static org.Thietlan.Utilities.FieldExtractors.extractFieldFromTOMLFile;
import static org.Thietlan.Utilities.FileHandlers.setOfWiFiFiles;

public class ScanWiFiFillesOption implements Option {
    private final TreeMap<String,Option> nextOptions=new TreeMap<>();
    private final String activationString;
    private final Set<String> parameters= Collections.emptySet();
    public ScanWiFiFillesOption(String activationString) {
        this.activationString = activationString;
    }
    @Override
    public String getActivationString() {
        return activationString;
    }

    @Override
    public int run() throws IOException {
        createNextOptions();
        if (nextOptions.isEmpty()) {
            System.out.println("No Wi-Fi details files found in the default directory");
            return 0;
        }else {
            System.out.println("I can generate a QR code for the following networks:");
            return 2;
        }
    }

    @Override
    public void printOptionInfo() {
        System.out.printf("%s. Scan the computer for stored Wi-FI networks and generate QRCodes for them\n",this.getActivationString());
    }

    @Override
    public void setParameters(HashMap<String, String> parameters) {

    }

    @Override
    public Set<String> getRequiredParameters() {
        return parameters;
    }

    @Override
    public TreeMap<String, Option> getNextOptions() {
        return nextOptions;
    }

    public  void createNextOptions(){
        int len=1;

        Set<String> files=setOfWiFiFiles("/etc/NetworkManager/system-connections");
        if(files!=null) {
            for (String file : files) {
                //System.out.printf("%s. I can generate QR codes for the following%s\n",len,file);
                Option optionWIFI = createWIFIOptionTOMLFile(file, len);
                if (optionWIFI != null) {
                    nextOptions.put(optionWIFI.getActivationString(), optionWIFI);
                    len++;

                }

            }
        }

    }

    private Option createWIFIOptionTOMLFile(String file,int len) {
        String psk=extractFieldFromTOMLFile(file,"psk");
        String ssid=extractFieldFromTOMLFile(file,"ssid");

        if(psk!=null&&ssid!=null){
            Option optionWIFI=new WiFiQRCodeOption(String.valueOf(len));
            HashMap<String, String> params=new HashMap<>();
            params.put("password",psk);
            params.put("ssid",ssid);
            optionWIFI.setParameters(params);
            return optionWIFI;
        }

        return null;
    }
}
