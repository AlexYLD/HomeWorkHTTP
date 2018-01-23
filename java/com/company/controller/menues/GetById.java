package com.company.controller.menues;

import static com.company.controller.App.*;

import com.company.entity.User;
import com.company.responces.ErrorResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.client.HttpClientErrorException;

import java.io.BufferedReader;
import java.io.IOException;

public class GetById implements com.company.interfaces.MenuItem {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getName() {
        return "Get User By ID";
    }

    @Override

    public void doAction(BufferedReader br) throws IOException {
        String id;
        System.out.println("Inser User's ID");
        id = br.readLine();
        try {
            printObject(get(baseUrl + "users/" + id, new TypeReference<User>() {
            }), "30");

        } catch (HttpClientErrorException e) {
            System.out.println("Failed get user due to:");
            errorPorocessing(e);
        }
    }
}
