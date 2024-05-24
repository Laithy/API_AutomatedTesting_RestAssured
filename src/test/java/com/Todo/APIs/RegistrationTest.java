package com.Todo.APIs;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RegistrationTest {

    //Positive scenarios
    @Test
    public void User_Registration_With_Valid_Mail() {

        File regData = new File("src/test/resources/JsonFiles/RegistrationData/RegistrationDataValidData.json");

        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(regData)

                .when().post("/api/v1/users/register")

                .then()
                .log().all()
                .assertThat().statusCode(201)
                .assertThat().body("firstName",equalTo("Mahmoud"))
        ;

    }

    //Negative scenarios
    @Test
    public void User_Registration_With_Existing_Mail () {

        File regData = new File("src/test/resources/JsonFiles/RegistrationData/RegistrationDataExistingMail.json");

        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(regData)

                .when().post("/api/v1/users/register")

                .then()
                .log().all()
                .assertThat().statusCode(400)
                .assertThat().body("message",equalTo("Email is already exists in the Database"))
        ;

    }

    @Test
    public void User_Registration_With_Invalid_Mail () {

        File regData = new File("src/test/resources/JsonFiles/RegistrationData/RegistrationDataInvalidMail.json");

        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(regData)

                .when().post("/api/v1/users/register")

                .then()
                .log().all()
                .assertThat().statusCode(400)
                .assertThat().body("message",equalTo("\"email\" must be a valid email"))
        ;

    }

    @Test
    public void User_Registration_With_Empty_Data_Field() {

        File regData = new File("src/test/resources/JsonFiles/RegistrationData/RegistrationDataEmptyDataField.json");

        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(regData)

                .when().post("/api/v1/users/register")

                .then()
                .log().all()
                .assertThat().statusCode(400)
                .assertThat().body("message",equalTo("\"firstName\" is not allowed to be empty"))
        ;

    }

}
