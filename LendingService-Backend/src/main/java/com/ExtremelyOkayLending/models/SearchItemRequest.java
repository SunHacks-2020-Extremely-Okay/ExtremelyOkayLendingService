package com.ExtremelyOkayLending.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

/* Longitude is X, Latitude is Y */
@Getter
@AllArgsConstructor
public class SearchItemRequest {
    private final Long itemId;
    private final String userId;
    private final Integer category;
    private final Integer proximity;
    private final Double locationX;
    private final Double locationY;
    private final Boolean verified;
    private final Boolean available;
    private final Long returnDate;
    private final String sku;
}
