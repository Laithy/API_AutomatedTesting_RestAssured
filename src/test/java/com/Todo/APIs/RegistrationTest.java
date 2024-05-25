package com.Todo.APIs;

import com.Todo.Models.UserPojo;
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
        UserPojo user = new UserPojo("Mahmoud","Elaithy","mahmoud89@example.com","12345678");

        //Sending request
        Response res =
        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(user)

                .when().post("/api/v1/users/register")

                .then()
                .log().all()
                .extract().response();

        //Assertions
        assertThat(res.statusCode() , equalTo(201));
        assertThat(res.path("firstName") , equalTo("Mahmoud"));

    }

    //Negative scenarios
    @Test
    public void User_Registration_With_Existing_Mail () {

        //Test data
        UserPojo user = new UserPojo("Mahmoud","Elaithy","mahmoud@example.com","12345678");

        //Sending request
        Response res =
        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(user)

                .when().post("/api/v1/users/register")

                .then()
                .log().all()
                .extract().response();

        //Assertions
        assertThat(res.statusCode() , equalTo(400));
        assertThat(res.path("message") , equalTo("Email is already exists in the Database"));

    }

    @Test
    public void User_Registration_With_Invalid_Mail () {

        //Test data
        UserPojo user = new UserPojo("Mahmoud","Elaithy","mahmoud3","12345678");

        //Sending request
        Response res =
        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(user)

                .when().post("/api/v1/users/register")

                .then()
                .log().all()
                .extract().response();

        //Assertions
        assertThat(res.statusCode() , equalTo(400));
        assertThat(res.path("message") , equalTo("\"email\" must be a valid email"));

    }

    @Test
    public void User_Registration_With_Empty_Data_Field() {

        //Test data
        UserPojo user = new UserPojo("","Elaithy","mahmoud3@example.com","12345678");

        //Sending request
        Response res =
        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(user)

                .when().post("/api/v1/users/register")

                .then()
                .log().all()
                .extract().response();

        //Assertions
        assertThat(res.statusCode() , equalTo(400));
        assertThat(res.path("message") , equalTo("\"firstName\" is not allowed to be empty"));

    }

}
