package com.Todo.Testcases;

import com.Todo.APIs.UserApi;
import com.Todo.Data.ErrorMessage;
import com.Todo.Models.MessagePojo;
import com.Todo.Models.UserPojo;
import com.Todo.Steps.UserSteps;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
@Feature("Login feature")
public class LoginTest {

    //Positive scenario
    @Story("User login")
    @Test(description = "User login")
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
    @Story("User tries to login with wrong data")
    @Test(description = "User tries to login with wrong data")
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

    @Story("User tries to login but with empty data fields")
    @Test(description = "User tries to login but with empty data fields")
    public void User_Login_With_Empty_Data_Field() {

        //Test data
        UserPojo user = UserSteps.generateLoginDataWithEmptyDataField();

        //Sending request
        Response res = UserApi.login(user);

        //Assertions
        assertThat(res.statusCode() , equalTo(400));

    }

}
