package com.Todo.APIs;

import com.Todo.Models.UserPojo;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LoginTest {

    //Positive scenario
    @Test
    public void User_Login_With_Valid_Data() {

        UserPojo user = new UserPojo("mahmoud3@example.com","12345678");

        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(user)

                .when().post("/api/v1/users/login")

                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("access_token",not(equalTo(null)))
        ;

    }

    //Negative scenario
    @Test
    public void User_Login_With_Invalid_Data() {

        UserPojo user = new UserPojo("mahmoud3@example.com","12345");

        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(user)

                .when().post("/api/v1/users/login")

                .then()
                .log().all()
                .assertThat().statusCode(400)
                .assertThat().body("message",equalTo("Please Fill a correct Password"))
        ;

    }

    @Test
    public void User_Login_With_Empty_Data_Field() {

        UserPojo user = new UserPojo("","12345678");

        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(user)

                .when().post("/api/v1/users/login")

                .then()
                .log().all()
                .assertThat().statusCode(400)
        ;

    }

}
