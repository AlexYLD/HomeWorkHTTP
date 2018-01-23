package com.company.controller;

import com.company.controller.annotations.PrintAnnotation;
import com.company.controller.menues.*;
import com.company.entity.User;
import com.company.interfaces.MenuItem;
import com.company.interfaces.Request;
import com.company.responces.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Hello world!
 */
public class App {

    public static final RestTemplate restTemplate = new RestTemplate();
    public static String baseUrl = "http://localhost:8080/";
    public static ObjectMapper mapper = new ObjectMapper();
    public static Properties session = new Properties();

    @SneakyThrows
    public static void main(String[] args) throws JsonProcessingException {
        session.load(App.class.getResourceAsStream("/session.properties"));
        Menu logger = new Menu(getActions());
        Menu logedInMenu = new Menu(getLogedInActions());
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            if (session.getProperty("SESSION_ID").equals("")) {
                logger.runMenu(br);
            } else {
                System.setProperty("THIS_USER_ID", session.getProperty("THIS_USER_ID"));
                System.setProperty("SESSION_ID", session.getProperty("SESSION_ID"));
                User user = (User) get(baseUrl + "users/" + System.getProperty("THIS_USER_ID"), new TypeReference<User>() {
                });
                System.out.println(String.format("Welcome %s %s", user.getFirstName(), user.getLastName()));
            }
            logedInMenu.runMenu(br);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @SneakyThrows
    public static Object post(String url, Request request, TypeReference responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity entity = new HttpEntity(request, headers);
        ResponseEntity<String> responseStr = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        Object response = null;
        if (responseStr.getBody() != null) {
            response = mapper.readValue(responseStr.getBody(), responseType);
        }
        return response;
    }

    @SneakyThrows
    public static Object get(String url, TypeReference responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> responseStr = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        Object response = null;
        if (responseStr.getBody() != null) {
            response = mapper.readValue(responseStr.getBody(), responseType);
        }
        return response;
    }

    @SneakyThrows
    public static Object delete(String url, TypeReference responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> responseStr = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
        Object response = null;
        if (responseStr.getBody() != null) {
            response = mapper.readValue(responseStr.getBody(), responseType);
        }
        return response;
    }

    @SneakyThrows
    public static Object put(String url, Request request, TypeReference responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity entity = new HttpEntity(request, headers);
        ResponseEntity<String> responseStr = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
        Object response = null;
        if (responseStr.getBody() != null) {
            response = mapper.readValue(responseStr.getBody(), responseType);
        }
        return response;
    }

    @SneakyThrows
    public static void errorPorocessing(HttpClientErrorException e) {
        ErrorResponse errors = mapper.readValue(e.getResponseBodyAsString(), ErrorResponse.class);
        Map<String, List<String>> errorResponse = errors.getErrorResponse();
        if (errorResponse != null) {
            for (List<String> list : errorResponse.values()) {
                System.out.println(list);
            }
        } else {
            System.out.println(errors.getMessage());
        }
    }


    public static List<MenuItem> getActions() {
        List<MenuItem> res = new ArrayList<>();
        res.add(new LogIn());
        res.add(new Registrate());
        return res;
    }

    public static List<MenuItem> getLogedInActions() {
        List<MenuItem> res = new ArrayList<>();
        res.add(new UserInfo());
        res.add(new ItemsManage());
        res.add(new LogOut());
        return res;
    }

    @SneakyThrows
    public static void saveSession(String sessionID, String userId) {
        System.setProperty("THIS_USER_ID", userId);
        System.setProperty("SESSION_ID", sessionID);
        String newProps = "THIS_USER_ID=" + userId + "\nSESSION_ID=" + sessionID;
        FileUtils.writeStringToFile(new File("C:/Users/ayarygix/workspaceJava/ProjectTrackerClient/src/main/resources/session.properties"), newProps);
    }

    public static String getPrintName(Field field) {
        if (field.isAnnotationPresent(PrintAnnotation.class)) {
            return field.getAnnotation(PrintAnnotation.class).printValue();
        }
        return field.getName();
    }

    @SneakyThrows
    public static void printObject(Object object, String spacing) {
        if(object==null){
            System.out.println("Not found");
            return;
        }
        Collection list = new ArrayList<>();
        if (object instanceof Collection) {
            list = (Collection) object;
            if (!list.isEmpty()) {
                object = list.iterator().next();
            }
        } else {
            list.add(object);
        }
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            for (Object listMember : list) {
                System.out.print(String.format("%-" + spacing + "s", getPrintName(field) + ": " + field.get(listMember)));
            }
            System.out.println();
        }
        System.out.println();
    }

    @SneakyThrows
    public static void fillRequest(Request request, BufferedReader br) throws IOException {
        for (Field field : request.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            System.out.println("Insert " + getPrintName(field));
            field.set(request, br.readLine());
        }
    }
}
