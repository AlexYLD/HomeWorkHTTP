package com.company.responces;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
@NoArgsConstructor
@Data
public class ErrorResponse {
    int errorStatus;
    Map<String,List<String>> errorResponse;
    String message;
}
