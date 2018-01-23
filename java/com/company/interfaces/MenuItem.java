package com.company.interfaces;

import java.io.BufferedReader;
import java.io.IOException;

public interface MenuItem {
    public boolean isExit();

    public String getName();

    public void doAction(BufferedReader br) throws IOException;
}
