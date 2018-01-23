package com.company.controller.menues;

import com.company.entity.Item;
import com.company.entity.User;
import com.company.responces.ErrorResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.HttpClientErrorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.company.controller.App.*;

public class GetAllUsers implements com.company.interfaces.MenuItem {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getName() {
        return "Get All Users";
    }

    @Override
    @SneakyThrows
    public void doAction(BufferedReader br) throws IOException {
        try {
            printObject(get(baseUrl + "users/", new ParameterizedTypeReference<List<User>>() {}), "30");
        } catch (HttpClientErrorException e) {
            System.out.println("Failed get users due to:");
            errorPorocessing(e);
        }
    }
}
