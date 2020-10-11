package com.ExtremelyOkayLending.auth.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    private String user_id;

    public UserResponse(String user_id){
        this.user_id = user_id;
    }

}
