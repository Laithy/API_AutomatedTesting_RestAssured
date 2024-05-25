package com.Todo.ApiBase;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestSpec {

    public static String getEnv () {

        String env = System.getProperty("env" , "PRODUCTION");
        String baseURL;
        switch (env) {
            case "PRODUCTION":
                baseURL= "https://todo.qacart.com";
                break;
            case "LOCAL":
                baseURL= "http://localhost:8080";
                break;
            default:
                throw new RuntimeException("Environment is not supported");
        }

        return baseURL;

    }

    public static RequestSpecification getRequestSpec () {

        return
        given().baseUri(getEnv())
                .contentType(ContentType.JSON)
                .log().all()
                ;

    }

}
