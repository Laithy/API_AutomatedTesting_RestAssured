package com.Todo.APIs;

import com.Todo.Models.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApi {

    public static Response Register (UserPojo user) {

        return
                given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(user)

                .when().post("/api/v1/users/register")

                .then()
                .log().all()
                .extract().response();

    }

    public static Response Login (UserPojo user) {

        return
                given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(user)

                .when().post("/api/v1/users/login")

                .then()
                .log().all()
                .extract().response();

    }

}
