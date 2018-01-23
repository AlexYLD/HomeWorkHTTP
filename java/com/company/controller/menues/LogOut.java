package com.company.controller.menues;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import static com.company.controller.App.*;


public class LogOut implements com.company.interfaces.MenuItem {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String getName() {
        return "Log Out";
    }

    @Override
    public void doAction(BufferedReader br) throws IOException {
        post(baseUrl + "users/" + "logout", null, null);
        String newProps = "THIS_USER_ID=" + "\nSESSION_ID=";
        FileUtils.writeStringToFile(new File("C:/Users/ayarygix/workspaceJava/ProjectTrackerClient/src/main/resources/session.properties"), newProps);
    }
}
