package com.company.requests;

import com.company.controller.annotations.PrintAnnotation;
import com.company.interfaces.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserLogInRequest implements Request {
    @PrintAnnotation(printValue = "Email")
    private String email;

    @PrintAnnotation(printValue = "Password")
    private String password;
}
