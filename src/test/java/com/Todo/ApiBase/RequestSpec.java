package com.Todo.ApiBase;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestSpec {

    public static RequestSpecification getRequestSpec () {

        return
        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .log().all()
                ;

    }

}
