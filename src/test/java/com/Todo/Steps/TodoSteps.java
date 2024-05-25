package com.Todo.Steps;

import com.Todo.APIs.TodoApi;
import com.Todo.Models.TodoPojo;
import com.github.javafaker.Faker;
import io.restassured.response.Response;

public class TodoSteps {

    public static TodoPojo generateTodo() {

        Faker faker = new Faker();
        String item = faker.programmingLanguage().name();
        return new TodoPojo( false, item);

    }

    public static TodoPojo generateTodoEmptyDataField() {

        return new TodoPojo( false, "");

    }

    public static String getTodoID (TodoPojo todo, String token) {

        Response res = TodoApi.addTodo(todo , token);

        return res.body().path("_id");

    }

}
