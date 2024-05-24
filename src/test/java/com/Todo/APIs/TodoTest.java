package com.Todo.APIs;

import com.Todo.Models.TodoPojo;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TodoTest {


    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY2NTA1NzcwNDE2MTE4MDAxNDNmYTBhOCIsImZpcnN0TmFtZSI6Ik1haG1vdWQiLCJsYXN0TmFtZSI6IkVsYWl0aHkiLCJpYXQiOjE3MTY1NDI3NDh9.cmwyiV1HSSxu3rstGkQVl2eq1OD0-wZpgD2T5NAwKv0";
    String id = "6650634041611800143fa108";

    //Adds Entry
    //Positive scenario
    @Test
    public void User_Adds_a_New_Entry() {

        TodoPojo item = new TodoPojo(false , "Appium");

        given().baseUri("https://todo.qacart.com")
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(item)

                .when().post("/api/v1/tasks")

                .then()
                .log().all()
                .assertThat().statusCode(201)
                .assertThat().body("isCompleted",equalTo(false))
                .assertThat().body("item",equalTo("Appium"))
                .assertThat().body("_id",not(equalTo(null)))
        ;

    }
    //Negative scenario
    @Test
    public void User_Adds_a_New_Entry_No_Auth() {

        TodoPojo item = new TodoPojo(false , "Appium");

        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(item)

                .when().post("/api/v1/tasks")

                .then()
                .log().all()
                .assertThat().statusCode(401)
                .assertThat().body("message",equalTo("Unauthorized, please insert a correct token"))
        ;

    }
    @Test
    public void User_Adds_a_New_Entry_Empty_Data_Field() {

        TodoPojo item = new TodoPojo("Appium");

        given().baseUri("https://todo.qacart.com")
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(item)

                .when().post("/api/v1/tasks")

                .then()
                .log().all()
                .assertThat().statusCode(400)
//                .assertThat().body("message",equalTo("\"item\" is not allowed to be empty"))
        ;

    }

    //Views Entry
    //Positive scenario
    @Test
    public void User_Views_The_New_Entry() {

        given().baseUri("https://todo.qacart.com")
                .auth().oauth2(token)
                .contentType(ContentType.JSON)

                .when().get("/api/v1/tasks/" + id)

                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("item" , equalTo("Appium"))
                .assertThat().body("isCompleted" , equalTo(false))
        ;

    }
    //Negative scenario
    @Test
    public void User_Views_The_New_Entry_No_Auth() {

        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)

                .when().get("/api/v1/tasks/" + id)

                .then()
                .log().all()
                .assertThat().statusCode(401)
                .assertThat().body("message" , equalTo("Unauthorized, please insert a correct token"))
        ;

    }

    //Deletes Entry
    //Positive scenario
    @Test
    public void User_Deletes_The_New_Entry() {

        given().baseUri("https://todo.qacart.com")
                .auth().oauth2(token)
                .contentType(ContentType.JSON)

                .when().delete("/api/v1/tasks/" + id)

                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("item" , equalTo("Appium"))
                .assertThat().body("isCompleted" , equalTo(false))
        ;

    }
    //Negative scenario
    @Test
    public void User_Deletes_The_New_Entry_No_Auth() {

        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)

                .when().delete("/api/v1/tasks/" + id)

                .then()
                .log().all()
                .assertThat().statusCode(401)
                .assertThat().body("message" , equalTo("Unauthorized, please insert a correct token"))
        ;

    }

}
