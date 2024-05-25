package com.Todo.APIs;

import com.Todo.ApiBase.RequestSpec;
import com.Todo.Data.Route;
import com.Todo.Models.TodoPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TodoApi {

    public static Response addTodo(TodoPojo item , String token) {

        return
                given()
                        .spec(RequestSpec.getRequestSpec())
                        .auth().oauth2(token)
                        .body(item)

                        .when().post(Route.TASKS_ROUTE)

                        .then()
                        .log().all()
                        .extract().response();

    }

    public static Response viewTodo(String id , String token) {

        return
                given()
                        .spec(RequestSpec.getRequestSpec())
                        .auth().oauth2(token)

                        .when().get(Route.TASKS_ROUTE + id)

                        .then()
                        .log().all()
                        .extract().response();

    }

    public static Response deleteTodo(String id , String token) {

        return
                given()
                        .spec(RequestSpec.getRequestSpec())
                        .auth().oauth2(token)

                        .when().delete(Route.TASKS_ROUTE + id)

                        .then()
                        .log().all()
                        .extract().response();

    }

}
