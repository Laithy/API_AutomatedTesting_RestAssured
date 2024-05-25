package com.Todo.APIs;

import com.Todo.Models.TodoPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TodoTest {


    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY2NTA1NzcwNDE2MTE4MDAxNDNmYTBhOCIsImZpcnN0TmFtZSI6Ik1haG1vdWQiLCJsYXN0TmFtZSI6IkVsYWl0aHkiLCJpYXQiOjE3MTY2MTEwMDR9._twXOilzGZ3n31tCPwPLI7zu9k_oeE4zPXJN1x_WQ4A";
    String id = "6651685541611800143fa67c";

    //------------------------------------------------------------------------------------------------------------------
    //Adds Entry
    //Positive scenario
    @Test
    public void User_Adds_a_New_Entry() {

        //Test data
        TodoPojo item = new TodoPojo(false , "Appium");

        //sending request
        Response res =
        given().baseUri("https://todo.qacart.com")
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(item)

                .when().post("/api/v1/tasks")

                .then()
                .log().all()
                .extract().response();

        //Assertions
        assertThat(res.statusCode() , equalTo(201));
        assertThat(res.path("isCompleted") , equalTo(false));
        assertThat(res.path("item") , equalTo("Appium"));
        assertThat(res.path("_id") , not(equalTo(null)));

    }

    //Negative scenario

    @Test
    public void User_Adds_a_New_Entry_No_Auth() {

        //Test data
        TodoPojo item = new TodoPojo(false , "Appium");

        //Sending request
        Response res =
        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(item)

                .when().post("/api/v1/tasks")

                .then()
                .log().all()
                .extract().response();

        //Assertions
        assertThat(res.statusCode() , equalTo(401));
        assertThat(res.path("message") , equalTo("Unauthorized, please insert a correct token"));

    }

    @Test
    public void User_Adds_a_New_Entry_Empty_Data_Field() {

        //Test data
        TodoPojo item = new TodoPojo("Appium");

        //Sending request
        Response res =
        given().baseUri("https://todo.qacart.com")
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(item)

                .when().post("/api/v1/tasks")

                .then()
                .log().all()
                .extract().response();

        //Assertions
        assertThat(res.statusCode() , equalTo(400));
        assertThat(res.path("message") , equalTo("\"isCompleted\" is required"));


    }

    //------------------------------------------------------------------------------------------------------------------
    //Views Entry
    //Positive scenario
    @Test
    public void User_Views_The_New_Entry() {

        //Sending request
        Response res =
        given().baseUri("https://todo.qacart.com")
                .auth().oauth2(token)
                .contentType(ContentType.JSON)

                .when().get("/api/v1/tasks/" + id)

                .then()
                .log().all()
                .extract().response();

        //Assertions
        assertThat(res.statusCode() , equalTo(200));
        assertThat(res.path("item") , equalTo("Appium"));
        assertThat(res.path("isCompleted") , equalTo(false));

    }

    //Negative scenario
    @Test
    public void User_Views_The_New_Entry_No_Auth() {

        //Sending request
        Response res =
        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)

                .when().get("/api/v1/tasks/" + id)

                .then()
                .log().all()
                .extract().response();

        //Assertions
        assertThat(res.statusCode() , equalTo(401));
        assertThat(res.path("message") , equalTo("Unauthorized, please insert a correct token"));

    }

    //------------------------------------------------------------------------------------------------------------------
    //Deletes Entry
    //Positive scenario
    @Test
    public void User_Deletes_The_New_Entry() {

        //Sending request
        Response res =
        given().baseUri("https://todo.qacart.com")
                .auth().oauth2(token)
                .contentType(ContentType.JSON)

                .when().delete("/api/v1/tasks/" + id)

                .then()
                .log().all()
                .extract().response();

        //Assertions
        assertThat(res.statusCode() , equalTo(200));
        assertThat(res.path("item") , equalTo("Appium"));
        assertThat(res.path("isCompleted") , equalTo(false));

    }

    //Negative scenario
    @Test
    public void User_Deletes_The_New_Entry_No_Auth() {

        //Sending request
        Response res =
        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)

                .when().delete("/api/v1/tasks/" + id)

                .then()
                .log().all()
                .extract().response();

        //Assertions
        assertThat(res.statusCode() , equalTo(401));
        assertThat(res.path("message") , equalTo("Unauthorized, please insert a correct token"));

    }
    //------------------------------------------------------------------------------------------------------------------

}
