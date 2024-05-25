package com.Todo.APIs;

import com.Todo.ApiBase.RequestSpec;
import com.Todo.Data.Route;
import com.Todo.Models.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApi {

    public static Response register(UserPojo user) {

        return
                given()
                        .spec(RequestSpec.getRequestSpec())
                        .body(user)

                        .when().post(Route.REGISTER_ROUTE)

                        .then()
                        .log().all()
                        .extract().response();

    }

    public static Response login(UserPojo user) {

        return
                given()
                        .spec(RequestSpec.getRequestSpec())
                        .body(user)

                        .when().post(Route.LOGIN_ROUTE)

                        .then()
                        .log().all()
                        .extract().response();

    }

}
