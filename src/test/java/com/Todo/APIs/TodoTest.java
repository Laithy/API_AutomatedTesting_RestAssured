package com.Todo.APIs;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class TodoTest {

    //Positive scenario
    @Test
    public void User_Adds_a_New_Entry() {

        File itemData = new File("src/test/resources/JsonFiles/TaskData/TaskDataValid.json");
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY2NGZmNTc0MzZhZTFhMDAxNDQwYThlYSIsImZpcnN0TmFtZSI6Ik1haG1vdWQiLCJsYXN0TmFtZSI6IkVMYWl0aHkiLCJpYXQiOjE3MTY1MTkwMjd9.LKcMqY6_A5YiQrWqOiDWXlppUys-Vv59DvaktabXeNM";

        given().baseUri("https://todo.qacart.com")
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(itemData)

                .when().post("/api/v1/tasks")

                .then()
                .log().all()
                .assertThat().statusCode(201)
                .assertThat().body("isCompleted",equalTo(false))
                .assertThat().body("item",equalTo("Learn Appium"))
                .assertThat().body("_id",not(equalTo(null)))
        ;

    }

    //Negative scenario
    @Test
    public void User_Adds_a_New_Entry_No_Auth() {

        File itemData = new File("src/test/resources/JsonFiles/TaskData/TaskDataValid.json");

        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(itemData)

                .when().post("/api/v1/tasks")

                .then()
                .log().all()
                .assertThat().statusCode(401)
                .assertThat().body("message",equalTo("Unauthorized, please insert a correct token"))
        ;

    }

    @Test
    public void User_Adds_a_New_Entry_Empty_Data_Field() {

        File itemData = new File("src/test/resources/JsonFiles/TaskData/TaskDataInvalid.json");
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY2NGZmNTc0MzZhZTFhMDAxNDQwYThlYSIsImZpcnN0TmFtZSI6Ik1haG1vdWQiLCJsYXN0TmFtZSI6IkVMYWl0aHkiLCJpYXQiOjE3MTY1MTkwMjd9.LKcMqY6_A5YiQrWqOiDWXlppUys-Vv59DvaktabXeNM";


        given().baseUri("https://todo.qacart.com")
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(itemData)

                .when().post("/api/v1/tasks")

                .then()
                .log().all()
                .assertThat().statusCode(400)
                .assertThat().body("message",equalTo("\"item\" is not allowed to be empty"))
        ;

    }

}
