package com.company.controller.menues;

import com.company.entity.Item;
import com.company.interfaces.MenuItem;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.HttpClientErrorException;

import java.io.BufferedReader;
import java.io.IOException;

import static com.company.controller.App.*;

public class GetItemByID implements MenuItem {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getName() {
        return "Get item by ID";
    }

    @Override
    public void doAction(BufferedReader br) throws IOException {
        System.out.println("Insert item ID");
        String id = br.readLine();
        try {
            printObject(get(baseUrl + "/items/" + id, new ParameterizedTypeReference<Item>() {
            }), "30");
        } catch (HttpClientErrorException e) {
            errorPorocessing(e);
        }
    }
}
