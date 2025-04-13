package org.Thietlan.Interfaces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public interface Option {
    public String getActivationString();
    public int run() throws IOException;
    public void printOptionInfo();
    public void setParameters(HashMap<String, String> parameters);
    public Set<String> getRequiredParameters();

}
