package com.company.controller.menues;

import com.company.interfaces.MenuItem;

import java.io.BufferedReader;
import java.io.IOException;

public class BackItem implements MenuItem {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String getName() {
        return "Back";
    }

    @Override
    public void doAction(BufferedReader br) throws IOException {

    }
}
