package com.ExtremelyOkayLending.auth.models;

import org.apache.logging.slf4j.*;


import java.util.UUID;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.amazonaws.Request;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;

import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
//import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
//import software.amazon.awssdk.http.SdkHttpClient;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.dynamodb.*;

import com.amazonaws.services.dynamodbv2.*;
import org.springframework.context.annotation.Bean;


public class DynamoAdapter {

    final Logger logger = LoggerFactory.getLogger(DynamoAdapter.class);
    final AmazonDynamoDB dynamoClient;
    final DynamoDB db;
    final Table table;
    public DynamoAdapter() {
        dynamoClient = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withRegion("us-east-2")
                .build();
        db = new DynamoDB(dynamoClient);

        table = db.getTable("users");

    }



    public User getUser(String user_name) {

        try {
            Item item = table.getItem("user_name", user_name);
            if (item == null) {
                return null;
            }
            User ret = new User((Long) item.get("user_id"), (String) item.get("user_name"), (String) item.get("pass"));

            return ret;
        } catch(Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }


    public boolean addUser(String user_name, String pass) {

        boolean userExists = getUser(user_name) != null;
        if(userExists){
            return false;
        }


        //TODO: implement adding user
        return true;

    }



}
