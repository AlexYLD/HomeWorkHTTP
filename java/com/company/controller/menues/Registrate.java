package com.company.controller.menues;

import com.company.requests.UserRegistrationRequest;
import com.company.responces.ErrorResponse;
import com.company.responces.UserRegistrationResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.HttpClientErrorException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;


import static com.company.controller.App.*;

public class Registrate implements com.company.interfaces.MenuItem {
    boolean isExit = true;

    @Override
    public boolean isExit() {
        return isExit;
    }

    @Override
    public String getName() {
        return "Registration";
    }

    @Override
    @SneakyThrows
    public void doAction(BufferedReader br) throws IOException {

        UserRegistrationResponse response = null;
        UserRegistrationRequest request = new UserRegistrationRequest();
        fillRequest(request, br);
        try {
            response = (UserRegistrationResponse) post(baseUrl + "users/" + "register", request, new ParameterizedTypeReference<UserRegistrationResponse>() {
            });
            isExit = true;
            saveSession(response.getSessionId(), response.getUser().getId());
            System.out.println(System.getProperty("SESSION_ID"));
            System.out.println(String.format("Welcome %s %s", response.getUser().getFirstName(), response.getUser().getLastName()));
        } catch (HttpClientErrorException e) {
            ErrorResponse errors = mapper.readValue(e.getResponseBodyAsString(), ErrorResponse.class);
            System.out.println("Failed to register due to:");
            errorPorocessing(e);
            isExit = false;
        }

    }
}
