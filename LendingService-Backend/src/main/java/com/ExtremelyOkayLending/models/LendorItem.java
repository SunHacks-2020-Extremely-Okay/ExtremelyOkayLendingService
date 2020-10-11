package com.ExtremelyOkayLending.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class LendorItem {
    private final Integer itemId;
    @NonNull
    private final String userId;
    @NonNull
    private final Integer category;
    @NonNull
    private final Double locationX;
    @NonNull
    private final Double locationY;
    private final Boolean verified;
    private final Boolean available;
    private final Long returnDate;
    @NonNull
    private final String sku;
}
