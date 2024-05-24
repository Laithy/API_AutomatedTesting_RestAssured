package com.Todo.APIs;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LoginTest {

    //Positive scenario
    @Test
    public void User_Login_With_Valid_Data() {

        File loginData = new File("src/test/resources/JsonFiles/LoginData/LoginDataValid.json");

        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(loginData)

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

        File loginData = new File("src/test/resources/JsonFiles/LoginData/LoginDataInvalid.json");

        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(loginData)

                .when().post("/api/v1/users/login")

                .then()
                .log().all()
                .assertThat().statusCode(400)
                .assertThat().body("message",equalTo("Please Fill a correct Password"))
        ;

    }

    @Test
    public void User_Login_With_Empty_Data_Field() {

        File loginData = new File("src/test/resources/JsonFiles/LoginData/LoginDataEmptyDataField.json");

        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(loginData)

                .when().post("/api/v1/users/login")

                .then()
                .log().all()
                .assertThat().statusCode(400)
        ;

    }

}
