package com.Todo.Testcases;

import com.Todo.APIs.UserApi;
import com.Todo.Models.MessagePojo;
import com.Todo.Models.UserPojo;
import com.Todo.Steps.UserSteps;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class RegistrationTest {

    //Positive scenarios
    @Test
    public void User_Registration_With_Valid_Mail() {

        //Test data
        UserPojo user = UserSteps.GenerateUser();

        //Sending request
        Response res = UserApi.Register(user);

        //Deserialization
        UserPojo responseBody = res.body().as(UserPojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(201));
        assertThat(responseBody.getFirstName() , equalTo(user.getFirstName()));

    }

    //Negative scenarios
    @Test
    public void User_Registration_With_Existing_Mail () {

        //Test data
        UserPojo user = UserSteps.GenerateRegisteredUser();

        //Sending request
        Response res = UserApi.Register(user);

        //Deserialization
        MessagePojo msg = res.body().as(MessagePojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(400));
        assertThat(msg.getMessage() , equalTo("Email is already exists in the Database"));

    }

    @Test
    public void User_Registration_With_Invalid_Mail () {

        //Test data
        UserPojo user = UserSteps.GenerateUserWithInvalidMail();

        //Sending request
        Response res = UserApi.Register(user);

        //Deserialization
        MessagePojo msg = res.body().as(MessagePojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(400));
        assertThat(msg.getMessage() , equalTo("\"email\" must be a valid email"));

    }

    @Test
    public void User_Registration_With_Empty_Data_Field() {

        //Test data
        UserPojo user = UserSteps.GenerateUserWithEmptyDataField();

        //Sending request
        Response res = UserApi.Register(user);

        //Deserialization
        MessagePojo msg = res.body().as(MessagePojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(400));
        assertThat(msg.getMessage() , equalTo("\"firstName\" is not allowed to be empty"));

    }

}
