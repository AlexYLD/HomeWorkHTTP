package com.company.controller.menues;

import com.company.entity.Item;
import com.company.entity.User;
import com.company.interfaces.MenuItem;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;
import org.springframework.web.client.HttpClientErrorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.company.controller.App.*;

public class GetItems implements MenuItem {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getName() {
        return "Show items";
    }

    @Override
    @SneakyThrows
    public void doAction(BufferedReader br) throws IOException {
        String input;
        String query = "";
        boolean breaker = true;
        while (breaker) {
            System.out.println("1.All items\n2.Your items only");
            input = br.readLine();
            switch (input) {
                case "1":
                    query = "?my-items=false";
                    breaker = false;
                    break;
                case "2":
                    query = "?my-items=true";
                    breaker = false;
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
        try {

            printObject(get(baseUrl + "items/" + query, new TypeReference<List<Item>>() {
            }), "50");

        } catch (HttpClientErrorException e) {
            System.out.println("Failed get users due to:");
            errorPorocessing(e);
        }
    }


}
