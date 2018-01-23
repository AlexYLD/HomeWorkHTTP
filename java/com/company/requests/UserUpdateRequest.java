package com.company.requests;

import com.company.controller.annotations.PrintAnnotation;
import com.company.interfaces.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateRequest implements Request {
    @PrintAnnotation(printValue = "First name")
    private String firstName;

    @PrintAnnotation(printValue = "Last name")
    private String lastName;

    @PrintAnnotation(printValue = "Email")
    private String email;
}
