package com.Todo.APIs;

import com.Todo.Models.TodoPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TodoApi {

    public static Response AddTodo(TodoPojo item , String token) {

        return
                given().baseUri("https://todo.qacart.com")
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(item)

                .when().post("/api/v1/tasks")

                .then()
                .log().all()
                .extract().response();

    }

    public static Response ViewTodo(String id , String token) {

        return
                given().baseUri("https://todo.qacart.com")
                        .auth().oauth2(token)
                        .contentType(ContentType.JSON)

                        .when().get("/api/v1/tasks/" + id)

                        .then()
                        .log().all()
                        .extract().response();

    }

    public static Response DeleteTodo(String id , String token) {

        return
                given().baseUri("https://todo.qacart.com")
                        .auth().oauth2(token)
                        .contentType(ContentType.JSON)

                        .when().delete("/api/v1/tasks/" + id)

                        .then()
                        .log().all()
                        .extract().response();

    }

}
