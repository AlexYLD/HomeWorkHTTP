package com.company.responces;

import com.company.interfaces.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateResponse implements Response {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
