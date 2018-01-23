package com.company.controller.menues;

import com.company.interfaces.MenuItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserInfo implements MenuItem {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getName() {
        return "User Info";
    }

    @Override
    public void doAction(BufferedReader br) throws IOException {
        Menu userMenu = new Menu(getUserActions());
        userMenu.runMenu(br);
    }

    public List<MenuItem> getUserActions() {
        ArrayList<MenuItem> res = new ArrayList<>();
        res.add(new GetAllUsers());
        res.add(new GetById());
        res.add(new UpdateUser());
        res.add(new BackItem());
        return res;
    }
}
