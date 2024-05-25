package com.Todo.Testcases;

import com.Todo.APIs.UserApi;
import com.Todo.Data.ErrorMessage;
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
        UserPojo user = UserSteps.generateLoginData();

        //Sending request
        Response res = UserApi.login(user);

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
        UserPojo user = UserSteps.generateInvalidLoginData();

        //Sending request
        Response res = UserApi.login(user);

        //Deserialization
        MessagePojo msg = res.body().as(MessagePojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(400));
        assertThat(msg.getMessage() , equalTo(ErrorMessage.EMAIL_NOT_FOUND));

    }

    @Test
    public void User_Login_With_Empty_Data_Field() {

        //Test data
        UserPojo user = UserSteps.generateLoginDataWithEmptyDataField();

        //Sending request
        Response res = UserApi.login(user);

        //Assertions
        assertThat(res.statusCode() , equalTo(400));

    }

}
