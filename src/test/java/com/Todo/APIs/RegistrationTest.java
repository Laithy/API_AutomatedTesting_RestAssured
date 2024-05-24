package com.Todo.APIs;

import com.Todo.Models.UserPojo;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RegistrationTest {

    //Positive scenarios
    @Test
    public void User_Registration_With_Valid_Mail() {

        UserPojo user = new UserPojo("Mahmoud","Elaithy","mahmoud3@example.com","12345678");

        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(user)

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

        UserPojo user = new UserPojo("Mahmoud","Elaithy","mahmoud@example.com","12345678");

        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(user)

                .when().post("/api/v1/users/register")

                .then()
                .log().all()
                .assertThat().statusCode(400)
                .assertThat().body("message",equalTo("Email is already exists in the Database"))
        ;

    }

    @Test
    public void User_Registration_With_Invalid_Mail () {

        UserPojo user = new UserPojo("Mahmoud","Elaithy","mahmoud3","12345678");

        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(user)

                .when().post("/api/v1/users/register")

                .then()
                .log().all()
                .assertThat().statusCode(400)
                .assertThat().body("message",equalTo("\"email\" must be a valid email"))
        ;

    }

    @Test
    public void User_Registration_With_Empty_Data_Field() {

        UserPojo user = new UserPojo("","Elaithy","mahmoud3@example.com","12345678");

        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(user)

                .when().post("/api/v1/users/register")

                .then()
                .log().all()
                .assertThat().statusCode(400)
                .assertThat().body("message",equalTo("\"firstName\" is not allowed to be empty"))
        ;

    }

}
