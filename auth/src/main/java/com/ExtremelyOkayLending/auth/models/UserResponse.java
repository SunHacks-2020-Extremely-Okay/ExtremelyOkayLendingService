package com.ExtremelyOkayLending.auth.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    private String user_id;
    private int status;

    public UserResponse(String user_id, int status){
        this.user_id = user_id;
        this.status = status;
    }

    public UserResponse(int status) {
        this.status = status;
    }

}
