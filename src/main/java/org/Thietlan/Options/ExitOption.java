package org.Thietlan.Options;

import org.Thietlan.Interfaces.Option;

import java.util.*;

public class ExitOption implements Option {

    private Set<String> parameters=Collections.emptySet();
    @Override
    public String getActivationString() {
        return "2";
    }

    @Override
    public int run() {
        return 1;
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


}
