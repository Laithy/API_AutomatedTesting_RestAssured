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

@Feature("Registration feature")
public class RegistrationTest {

    //Positive scenarios
    @Story("User register with valid mail")
    @Test(description = "User register with valid mail")
    public void User_Registration_With_Valid_Mail() {

        //Test data
        UserPojo user = UserSteps.generateUser();

        //Sending request
        Response res = UserApi.register(user);

        //Deserialization
        UserPojo responseBody = res.body().as(UserPojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(201));
        assertThat(responseBody.getFirstName() , equalTo(user.getFirstName()));

    }

    //Negative scenarios
    @Story("User register with an existing mail")
    @Test(description = "User register with an existing mail")
    public void User_Registration_With_Existing_Mail () {

        //Test data
        UserPojo user = UserSteps.generateRegisteredUser();

        //Sending request
        Response res = UserApi.register(user);

        //Deserialization
        MessagePojo msg = res.body().as(MessagePojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(400));
        assertThat(msg.getMessage() , equalTo(ErrorMessage.EMAIL_ALREADY_REGISTERED));

    }

    @Story("User register with an invalid mail")
    @Test(description = "User register with an invalid mail")
    public void User_Registration_With_Invalid_Mail () {

        //Test data
        UserPojo user = UserSteps.generateUserWithInvalidMail();

        //Sending request
        Response res = UserApi.register(user);

        //Deserialization
        MessagePojo msg = res.body().as(MessagePojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(400));
        assertThat(msg.getMessage() , equalTo(ErrorMessage.EMAIL_NOT_VALID));

    }

    @Story("User registers with an empty data field")
    @Test(description = "User registers with an empty data field")
    public void User_Registration_With_Empty_Data_Field() {

        //Test data
        UserPojo user = UserSteps.generateUserWithEmptyDataField();

        //Sending request
        Response res = UserApi.register(user);

        //Deserialization
        MessagePojo msg = res.body().as(MessagePojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(400));
        assertThat(msg.getMessage() , equalTo(ErrorMessage.EMPTY_FIRSTNAME_FIELD));

    }

}
