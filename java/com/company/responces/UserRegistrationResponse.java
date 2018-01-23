package com.company.responces;

import com.company.entity.User;
import com.company.interfaces.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegistrationResponse implements Response {
    private String sessionId;
    private User user;
}
