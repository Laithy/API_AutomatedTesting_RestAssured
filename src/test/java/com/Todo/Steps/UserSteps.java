package com.Todo.Steps;

import com.Todo.APIs.UserApi;
import com.Todo.Models.UserPojo;
import com.github.javafaker.Faker;
import io.restassured.response.Response;

public class UserSteps {

    public static UserPojo generateUser() {

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        return new UserPojo(firstName, lastName, email, password);
    }

    public static UserPojo generateRegisteredUser() {

        UserPojo user = generateUser();
        UserApi.register(user);
        return user;

    }

    public static UserPojo generateUserWithInvalidMail() {

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.name().username();
        String password = faker.internet().password();

        return new UserPojo(firstName, lastName, email, password);
    }

    public static UserPojo generateUserWithEmptyDataField() {

        Faker faker = new Faker();
        String lastName = faker.name().lastName();
        String email = faker.name().username();
        String password = faker.internet().password();

        return new UserPojo("", lastName, email, password);
    }

    public static  UserPojo generateLoginData() {
        UserPojo user = generateRegisteredUser();
        return new UserPojo(user.getEmail(), user.getPassword());
    }

    public static  UserPojo generateInvalidLoginData() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        return new UserPojo(email, password);
    }

    public static  UserPojo generateLoginDataWithEmptyDataField() {
        UserPojo user = generateRegisteredUser();
        return new UserPojo(user.getEmail(), "");
    }

    public static String getUserToken () {

        UserPojo user = generateUser();
        Response res = UserApi.register(user);
        return res.body().path("access_token");

    }

}
