package com.Todo.Testcases;

import com.Todo.APIs.TodoApi;
import com.Todo.Models.MessagePojo;
import com.Todo.Models.TodoPojo;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TodoTest {

    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY2NTA1NzcwNDE2MTE4MDAxNDNmYTBhOCIsImZpcnN0TmFtZSI6Ik1haG1vdWQiLCJsYXN0TmFtZSI6IkVsYWl0aHkiLCJpYXQiOjE3MTY2MTEwMDR9._twXOilzGZ3n31tCPwPLI7zu9k_oeE4zPXJN1x_WQ4A";
    String id = "66517a0241611800143fa6e5";

    /*------------------------------------------------------------------------------------------------------------------
    Adds Entry
    Positive scenario*/
    @Test
    public void User_Adds_a_New_Entry() {

        //Test data
        TodoPojo item = new TodoPojo(false , "Appium");

        //sending request
        Response res = TodoApi.AddTodo(item,token);

        //Deserialization
        TodoPojo responseBody = res.body().as(TodoPojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(201));
        assertThat(responseBody.getIsCompleted() , equalTo(false));
        assertThat(responseBody.getItem() , equalTo("Appium"));
        assertThat(responseBody.getId() , not(equalTo(null)));

    }

    //Negative scenario
    @Test
    public void User_Adds_a_New_Entry_No_Auth() {

        //Test data
        TodoPojo item = new TodoPojo(false , "Appium");

        //Sending request
        Response res = TodoApi.AddTodo(item,"");

        //Deserialization
        MessagePojo msg = res.body().as(MessagePojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(401));
        assertThat(msg.getMessage() , equalTo("Unauthorized, please insert a correct token"));

    }

    @Test
    public void User_Adds_a_New_Entry_Empty_Data_Field() {

        //Test data
        TodoPojo item = new TodoPojo("Appium");

        //Sending request
        Response res = TodoApi.AddTodo(item,token);

        //Deserialization
        MessagePojo msg = res.body().as(MessagePojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(400));
        assertThat(msg.getMessage() , equalTo("\"isCompleted\" is required"));


    }

    /*------------------------------------------------------------------------------------------------------------------
    Views Entry
    Positive scenario*/
    @Test
    public void User_Views_The_New_Entry() {

        //Sending request
        Response res = TodoApi.ViewTodo(id , token);

        //Deserialization
        TodoPojo responseBody = res.body().as(TodoPojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(200));
        assertThat(responseBody.getItem() , equalTo("Appium"));
        assertThat(responseBody.getIsCompleted() , equalTo(false));

    }

    //Negative scenario
    @Test
    public void User_Views_The_New_Entry_No_Auth() {

        //Sending request
        Response res = TodoApi.ViewTodo(id , "");

        //Deserialization
        MessagePojo msg = res.body().as(MessagePojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(401));
        assertThat(msg.getMessage() , equalTo("Unauthorized, please insert a correct token"));

    }

    /*------------------------------------------------------------------------------------------------------------------
    Deletes Entry
    Positive scenario*/
    @Test
    public void User_Deletes_The_New_Entry() {

        //Sending request
        Response res = TodoApi.DeleteTodo(id , token);

        //Deserialization
        TodoPojo responseBody = res.body().as(TodoPojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(200));
        assertThat(responseBody.getItem() , equalTo("Appium"));
        assertThat(responseBody.getIsCompleted() , equalTo(false));

    }

    //Negative scenario
    @Test
    public void User_Deletes_The_New_Entry_No_Auth() {

        //Sending request
        Response res = TodoApi.DeleteTodo(id , "");

        //Deserialization
        MessagePojo msg = res.body().as(MessagePojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(401));
        assertThat(msg.getMessage() , equalTo("Unauthorized, please insert a correct token"));

    }
    //------------------------------------------------------------------------------------------------------------------

}
