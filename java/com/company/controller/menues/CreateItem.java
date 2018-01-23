package com.company.controller.menues;

import com.company.entity.Item;
import com.company.interfaces.MenuItem;
import com.company.interfaces.Request;
import com.company.requests.ItemCreateRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.HttpClientErrorException;

import java.io.BufferedReader;
import java.io.IOException;
import static com.company.controller.App.*;

public class CreateItem implements MenuItem {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getName() {
        return "Create item";
    }

    @Override
    public void doAction(BufferedReader br) throws IOException {
        Request request = new ItemCreateRequest();
        fillRequest(request,br);
        try {
            printObject(post(baseUrl + "/items/", request, new ParameterizedTypeReference<Item>() {}),"30");
        }catch (HttpClientErrorException ex){
            errorPorocessing(ex);
        }

    }
}
