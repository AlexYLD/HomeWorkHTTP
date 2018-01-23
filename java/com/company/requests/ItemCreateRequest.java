package com.company.requests;

import com.company.controller.annotations.PrintAnnotation;
import com.company.entity.ItemType;
import com.company.interfaces.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemCreateRequest implements Request {
    @PrintAnnotation(printValue = "Title")
    String title;

    @PrintAnnotation(printValue = "Description")
    String description;

    @PrintAnnotation(printValue = "Item Type (1 - TASK / 2 - BUG / 3 - TEST)")
    String itemType;

}
