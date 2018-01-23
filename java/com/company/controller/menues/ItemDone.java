package com.company.controller.menues;

import com.company.entity.Item;
import com.company.interfaces.MenuItem;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.client.HttpClientErrorException;

import java.io.BufferedReader;
import java.io.IOException;

import static com.company.controller.App.*;

public class ItemDone implements MenuItem {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getName() {
        return "Set DONE status";
    }

    @Override
    public void doAction(BufferedReader br) throws IOException {
        System.out.println("Insert item ID");
        String id = br.readLine();
        try {
            printObject(put(baseUrl + "/items/done/" + id, null, new TypeReference<Item>() {
            }), "30");
        } catch (HttpClientErrorException e) {
            errorPorocessing(e);
        }
    }
}
