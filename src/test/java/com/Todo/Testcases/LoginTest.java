package com.Todo.Testcases;

import com.Todo.APIs.UserApi;
import com.Todo.Models.MessagePojo;
import com.Todo.Models.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class LoginTest {

    //Positive scenario
    @Test
    public void User_Login_With_Valid_Data() {

        //Test data
        UserPojo user = new UserPojo("mahmoud3@example.com","12345678");

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
        UserPojo user = new UserPojo("mahmoud3@example.com","12345");

        //Sending request
        Response res = UserApi.Login(user);

        //Deserialization
        MessagePojo msg = res.body().as(MessagePojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(400));
        assertThat(msg.getMessage() , equalTo("Please Fill a correct Password"));

    }

    @Test
    public void User_Login_With_Empty_Data_Field() {

        //Test data
        UserPojo user = new UserPojo("","12345678");

        //Sending request
        Response res = UserApi.Login(user);

        //Assertions
        assertThat(res.statusCode() , equalTo(400));

    }

}
