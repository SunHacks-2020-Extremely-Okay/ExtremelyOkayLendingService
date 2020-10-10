package com.ExtremelyOkayLending.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class UpdateAvailabilityStatusRequest {
    @NonNull
    private final Long itemId;
    @NonNull
    private final Boolean available;
    private final Long returnDate;
}
