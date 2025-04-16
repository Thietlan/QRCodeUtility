package org.Thietlan.Logic;

import org.Thietlan.Interfaces.Option;
import org.Thietlan.Options.ExitOption;
import org.Thietlan.Options.ScanWiFiFillesOption;
import org.Thietlan.Options.StringToQRCodeOption;
import org.Thietlan.Options.WiFiQRCodeOption;


import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.TreeMap;


public class Manager {
    private final TreeMap<String,Option> optionMap=new TreeMap<>();
    private Option currentOption;
    private final Scanner scanner;
    public Manager() {
        Option optionStringToQRCode = new StringToQRCodeOption("1");
        optionMap.put(optionStringToQRCode.getActivationString(),optionStringToQRCode);
        Option exit= new ExitOption("3");
        optionMap.put(exit.getActivationString(),exit);
        Option WifiScannerOption=new ScanWiFiFillesOption("2");
        optionMap.put(WifiScannerOption.getActivationString(),WifiScannerOption);
        scanner = new Scanner(System.in);
    }


    public void run() throws IOException {
        int exitCode=0;
        while(exitCode>=0){
            listOptions(optionMap);
            exitCode=executeOption(optionMap);
            if(exitCode==2){
                listOptions(currentOption.getNextOptions());
                executeOption(currentOption.getNextOptions());
            }

        }
    }

    private void listOptions(TreeMap<String,Option> optionMap) {
        for (Option option : optionMap.values()) {
            option.printOptionInfo();
        }
    }

    private int executeOption(TreeMap<String,Option> optionMap) throws IOException {
        String input=scanner.nextLine();
        if(optionMap.containsKey(input)){
            Option option=optionMap.get(input);

            HashMap<String,String> parameterMap=new HashMap<>();
            //System.out.println(option.getClass().getName());

            if(!option.getClass().getName().equals("org.Thietlan.Options.WiFiQRCodeOption")) {
                //System.out.println("Hmm shouldn't be here");
                for (String parameter : option.getRequiredParameters()) {
                    System.out.printf("Enter %s: \n", parameter);
                    parameterMap.put(parameter, scanner.nextLine());
                    option.setParameters(parameterMap);
                }
            }else{
               // System.out.println("So this that kind of wifiqr option");
                if(option instanceof WiFiQRCodeOption) {
                    //System.out.println("Hey this also worked");
                }
            }

            currentOption=option;
            return option.run();
        }
        else{
            System.out.println("No such option! Returning to main menu.");
            return 1;
        }


    }
}
