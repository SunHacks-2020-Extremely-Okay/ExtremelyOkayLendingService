package com.ExtremelyOkayLending.auth.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
public class User {
    private Long user_id;
    private String user_name;
    private String pass;

    public User(Long user_id, String user_name, String pass) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.pass = pass;
    }

    public User(String user_name, String pass) {
        this.user_name = user_name;
        this.pass = pass;
    }

    @Override
    public String toString(){
        return "{\"user_id\": \""+this.user_id+"\"}";
    }
}
