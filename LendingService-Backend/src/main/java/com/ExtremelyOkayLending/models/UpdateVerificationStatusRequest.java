package com.ExtremelyOkayLending.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class UpdateVerificationStatusRequest {
    @NonNull
    private final Integer itemId;
    @NonNull
    private final Boolean verified;
}
