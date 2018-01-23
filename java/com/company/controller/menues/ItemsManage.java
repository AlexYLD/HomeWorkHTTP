package com.company.controller.menues;

import com.company.interfaces.MenuItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemsManage implements MenuItem {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getName() {
        return "Item Menu";
    }

    @Override
    public void doAction(BufferedReader br) throws IOException {
        Menu itemMenu = new Menu(getItemActions());
        itemMenu.runMenu(br);
    }

    public List<MenuItem> getItemActions() {
        ArrayList<MenuItem> res = new ArrayList<>();
        res.add(new GetItems());
        res.add(new CreateItem());
        res.add(new ItemDone());
        res.add(new GetItemByID());
        res.add(new DeleteItem());
        res.add(new BackItem());
        return res;
    }
}
