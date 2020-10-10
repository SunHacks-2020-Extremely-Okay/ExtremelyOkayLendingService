package com.ExtremelyOkayLending.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchItemRequest {
    private final Long itemId;
    private final String userId;
    private final Integer category;
    private final Double locationX;
    private final Double locationY;
    private final Boolean verified;
    private final Boolean available;
    private final Long returnDate;
    private final String sku;
}
