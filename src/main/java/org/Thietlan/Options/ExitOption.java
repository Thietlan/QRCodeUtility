package org.Thietlan.Options;

import org.Thietlan.Interfaces.Option;

import java.util.*;

public class ExitOption implements Option {

    private final Set<String> parameters=Collections.emptySet();
    private final String stringActivation;
    public ExitOption(String stringActivation) {
        this.stringActivation = stringActivation;
    }

    @Override
    public String getActivationString() {
        return stringActivation;
    }

    @Override
    public int run() {
        return -1;
    }

    @Override
    public void printOptionInfo() {

        System.out.printf("%s. Exit\n",this.getActivationString());
    }

    @Override
    public void setParameters(HashMap<String, String> parameters) {
        return;
    }

    @Override
    public Set<String> getRequiredParameters() {
        return parameters;
    }

    @Override
    public TreeMap<String, Option> getNextOptions() {
        return null;
    }


}
