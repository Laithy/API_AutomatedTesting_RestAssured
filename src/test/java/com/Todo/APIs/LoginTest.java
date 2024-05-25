package com.Todo.APIs;

import com.Todo.Models.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.groovy.control.io.ReaderSource;
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
        Response res =
        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(user)

                .when().post("/api/v1/users/login")

                .then()
                .log().all()
                .extract().response();

        //Assertions
        assertThat(res.statusCode() , equalTo(200));
        assertThat(res.path("access_token") , not(equalTo(null)));

    }

    //Negative scenario
    @Test
    public void User_Login_With_Invalid_Data() {

        //Test data
        UserPojo user = new UserPojo("mahmoud3@example.com","12345");

        //Sending request
        Response res =
        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(user)

                .when().post("/api/v1/users/login")

                .then()
                .log().all()
                .extract().response();

        //Assertions
        assertThat(res.statusCode() , equalTo(400));
        assertThat(res.path("message") , equalTo("Please Fill a correct Password"));

    }

    @Test
    public void User_Login_With_Empty_Data_Field() {

        //Test data
        UserPojo user = new UserPojo("","12345678");

        //Sending request
        Response res =
        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(user)

                .when().post("/api/v1/users/login")

                .then()
                .log().all()
                .extract().response();

        //Assertions
        assertThat(res.statusCode() , equalTo(400));

    }

}
