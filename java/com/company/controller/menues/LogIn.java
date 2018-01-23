package com.company.controller.menues;

import com.company.requests.UserLogInRequest;
import com.company.responces.UserRegistrationResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import static com.company.controller.App.*;

public class LogIn implements com.company.interfaces.MenuItem {
    boolean isExit = true;

    @Override
    public boolean isExit() {
        return isExit;
    }

    @Override
    public String getName() {
        return "Log In";
    }

    @Override
    @SneakyThrows
    public void doAction(BufferedReader br) throws IOException {
        UserRegistrationResponse response = null;
        UserLogInRequest request = new UserLogInRequest();
        fillRequest(request, br);
        try {
            response = (UserRegistrationResponse) post(baseUrl + "users/" + "login", request, new TypeReference<UserRegistrationResponse>() {
            });
            isExit = true;
            saveSession(response.getSessionId(), response.getUser().getId());
            System.out.println(System.getProperty("SESSION_ID"));
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to log in due to:");
            errorPorocessing(e);
            isExit = false;
        }


    }
}
