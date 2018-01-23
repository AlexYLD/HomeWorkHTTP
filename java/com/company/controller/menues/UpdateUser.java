package com.company.controller.menues;

import com.company.requests.UserUpdateRequest;
import com.company.responces.ErrorResponse;
import com.company.responces.UserUpdateResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.HttpClientErrorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static com.company.controller.App.*;


public class UpdateUser implements com.company.interfaces.MenuItem {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getName() {
        return "Update user";
    }

    @Override
    @SneakyThrows
    public void doAction(BufferedReader br) throws IOException {

        UserUpdateRequest request = new UserUpdateRequest();
        UserUpdateResponse response = null;
        fillRequest(request, br);
        try {
            printObject(put(baseUrl + "users/" + System.getProperty("THIS_USER_ID"), request, new ParameterizedTypeReference<UserUpdateResponse>() {
            }), "30");
            System.out.println("Updated successfully\n");
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to update due to:");
            errorPorocessing(e);
        }
    }
}
