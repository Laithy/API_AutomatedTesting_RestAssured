package com.Todo.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TodoPojo {

    //Constructors
    public TodoPojo () {}
    public TodoPojo (Boolean isCompleted ,String item) {
        this.isCompleted = isCompleted;
        this.item = item;
    }
    public TodoPojo (String item) {
        this.item = item;
    }

    //Variables
    private Boolean isCompleted;
    @JsonProperty ("_id")
    private String id;
    private String item;
    private String userID;
    private String createdAt;
    @JsonProperty ("__v")
    private String v;

    //Getters and Setters
    public Boolean getIsCompleted() {
        return isCompleted;
    }
    public void setIsCompleted(Boolean completed) {
        isCompleted = completed;
    }
    @JsonProperty ("_id")
    public String getId() {
        return id;
    }
    @JsonProperty ("_id")
    public void setId(String id) {
        this.id = id;
    }
    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }
    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    @JsonProperty ("__v")
    public String getV() {
        return v;
    }
    @JsonProperty ("__v")
    public void setV(String v) {
        this.v = v;
    }



}
