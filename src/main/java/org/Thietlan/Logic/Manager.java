package org.Thietlan.Logic;

import org.Thietlan.Interfaces.Option;
import org.Thietlan.Options.ExitOption;
import org.Thietlan.Options.StringToQRCodeOption;


import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Manager {
    private final TreeMap<String,Option> optionMap=new TreeMap<>();

    private final Scanner scanner;
    public Manager() {
        Option optionStringToQRCode = new StringToQRCodeOption();
        optionMap.put(optionStringToQRCode.getActivationString(),optionStringToQRCode);
        Option exit= new ExitOption();
        optionMap.put(exit.getActivationString(),exit);
        scanner = new Scanner(System.in);
    }


    public void run() throws IOException {
        int exitCode=0;
        while(exitCode==0){
            listAllOptions();
            String input=scanner.nextLine();
            if(optionMap.containsKey(input)){
                Option option=optionMap.get(input);
                    HashMap<String,String> parameterMap=new HashMap<>();
                    for (String parameter:option.getRequiredParameters()){
                        System.out.printf("Enter %s: \n",parameter);
                        parameterMap.put(parameter,scanner.nextLine());
                    }
                option.setParameters(parameterMap);
                exitCode=option.run();
            }
            else{
                System.out.println("No such option");
            }
        }
    }

    private void listAllOptions() {
        for (Option option : optionMap.values()) {
            option.printOptionInfo();
        }
    }
}
