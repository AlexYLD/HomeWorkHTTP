package com.company.controller.menues;


import com.company.interfaces.MenuItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Menu {
    List<MenuItem> items;

    public Menu(List<MenuItem> items) {
        this.items = items;
    }

    public void runMenu(BufferedReader br) throws IOException {
        String choice;
        boolean notLastLoop = true;
        MenuItem item;

        while (notLastLoop) {
            for (int i = 1; i <= items.size(); i++) {
                System.out.println(i + ". " + items.get(i-1).getName());
            }
            choice = br.readLine();
            if (choice.matches("\\d+") && Integer.parseInt(choice) <= items.size()) {
                item = items.get(Integer.parseInt(choice)-1);
                item.doAction(br);
                notLastLoop = !item.isExit();
            } else {
                System.out.println("Wrong input");
            }
        }
    }


}
