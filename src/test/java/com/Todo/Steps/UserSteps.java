package com.Todo.Steps;

import com.Todo.APIs.UserApi;
import com.Todo.Models.UserPojo;
import com.github.javafaker.Faker;

public class UserSteps {

    public static UserPojo GenerateUser () {

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        return new UserPojo(firstName, lastName, email, password);
    }

    public static UserPojo GenerateRegisteredUser () {

        UserPojo user = GenerateUser();
        UserApi.Register(user);
        return user;

    }

    public static UserPojo GenerateUserWithInvalidMail () {

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.name().username();
        String password = faker.internet().password();

        return new UserPojo(firstName, lastName, email, password);
    }

    public static UserPojo GenerateUserWithEmptyDataField () {

        Faker faker = new Faker();
        String lastName = faker.name().lastName();
        String email = faker.name().username();
        String password = faker.internet().password();

        return new UserPojo("", lastName, email, password);
    }

    public static  UserPojo GenerateLoginData () {
        UserPojo user = GenerateRegisteredUser();
        return new UserPojo(user.getEmail(), user.getPassword());
    }

    public static  UserPojo GenerateInvalidLoginData () {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        return new UserPojo(email, password);
    }

    public static  UserPojo GenerateLoginDataWithEmptyDataField () {
        UserPojo user = GenerateRegisteredUser();
        return new UserPojo(user.getEmail(), "");
    }

}
