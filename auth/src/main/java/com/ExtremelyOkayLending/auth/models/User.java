package com.ExtremelyOkayLending.auth.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor

public class User {
    private String user_id;
    private String user_name;
    private String pass;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public User(String user_id, String user_name, String pass) {

        this.user_id = user_id;
        this.user_name = user_name;
        this.pass = pass;
    }

    public User(String user_name, String pass) {
        this.user_name = user_name;
        this.pass = pass;
    }

    public String getUser_name(){
        return this.user_name;
    }

    public String getPass() {
        return pass;
    }

    @Override
    public String toString(){
        return "{\"user_id\": \""+this.user_id+"\"}";
    }
}
