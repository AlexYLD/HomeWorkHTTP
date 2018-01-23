package com.company.entity;

import com.company.controller.annotations.PrintAnnotation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @PrintAnnotation(printValue = "Name")
    String firstName;

    @PrintAnnotation(printValue = "Surname")
    String lastName;

    @PrintAnnotation(printValue = "Email")
    String email;

    @PrintAnnotation(printValue = "ID")
    String id;

    @Override
    public String toString() {
        return firstName + " " + lastName + " (Email: " + email + ")";
    }
}
