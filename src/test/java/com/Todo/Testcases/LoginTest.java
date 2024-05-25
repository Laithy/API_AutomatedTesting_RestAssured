package com.Todo.Testcases;

import com.Todo.APIs.UserApi;
import com.Todo.Models.MessagePojo;
import com.Todo.Models.UserPojo;
import com.Todo.Steps.UserSteps;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class LoginTest {

    //Positive scenario
    @Test
    public void User_Login_With_Valid_Data() {

        //Test data
        UserPojo user = UserSteps.GenerateLoginData();

        //Sending request
        Response res = UserApi.Login(user);

        //Deserialization
        UserPojo responseBody = res.body().as(UserPojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(200));
        assertThat(responseBody.getAccessToken() , not(equalTo(null)));

    }

    //Negative scenario
    @Test
    public void User_Login_With_Invalid_Data() {

        //Test data
        UserPojo user = UserSteps.GenerateInvalidLoginData();

        //Sending request
        Response res = UserApi.Login(user);

        //Deserialization
        MessagePojo msg = res.body().as(MessagePojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(400));
        assertThat(msg.getMessage() , equalTo("We could not find the email in the database"));

    }

    @Test
    public void User_Login_With_Empty_Data_Field() {

        //Test data
        UserPojo user = UserSteps.GenerateLoginDataWithEmptyDataField();

        //Sending request
        Response res = UserApi.Login(user);

        //Assertions
        assertThat(res.statusCode() , equalTo(400));

    }

}
