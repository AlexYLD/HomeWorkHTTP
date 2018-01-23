package com.company.entity;

import com.company.controller.annotations.PrintAnnotation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {
    @PrintAnnotation(printValue = "Item title")
    String title;

    @PrintAnnotation(printValue = "Created on")
    String createdDate;

    @PrintAnnotation(printValue = "Description")
    String description;

    @PrintAnnotation(printValue = "Type")
    ItemType itemType;

    @PrintAnnotation(printValue = "Status")
    ItemStatus itemStatus;

    @PrintAnnotation(printValue = "Created by")
    User user;

    @PrintAnnotation(printValue = "ID")
    int id;

    @Override
    public String toString() {
        return "Item title: " + title + "\n" +
                "Created on: " + createdDate + "\n" +
                "Description: " + description + "\n" +
                "Status: " + itemStatus + "\n" +
                "Type: " + itemType + "\n" +
                "Created by: " + user.getFirstName() + " " + user.getLastName() + " " + user.getEmail() + "\n"
                ;
    }
}
