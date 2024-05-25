package com.Todo.Testcases;

import com.Todo.APIs.TodoApi;
import com.Todo.Data.ErrorMessage;
import com.Todo.Models.MessagePojo;
import com.Todo.Models.TodoPojo;
import com.Todo.Steps.TodoSteps;
import com.Todo.Steps.UserSteps;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Feature("Todo feature")
public class TodoTest {

    /*------------------------------------------------------------------------------------------------------------------
    Adds Entry
    Positive scenario*/
    @Story("User adds a new entry")
    @Test(description = "User adds a new entry")
    public void User_Adds_a_New_Entry() {

        //Test data
        TodoPojo item = TodoSteps.generateTodo();
        String token = UserSteps.getUserToken();

        //sending request
        Response res = TodoApi.addTodo(item, token);

        //Deserialization
        TodoPojo responseBody = res.body().as(TodoPojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(201));
        assertThat(responseBody.getIsCompleted() , equalTo(item.getIsCompleted()));
        assertThat(responseBody.getItem() , equalTo(item.getItem()));
        assertThat(responseBody.getId() , not(equalTo(null)));

    }

    //Negative scenario
    @Story("User add a new entry but with no authentication")
    @Test(description = "User add a new entry but with no authentication")
    public void User_Adds_a_New_Entry_No_Auth() {

        //Test data
        TodoPojo item = TodoSteps.generateTodo();

        //Sending request
        Response res = TodoApi.addTodo(item,"");

        //Deserialization
        MessagePojo msg = res.body().as(MessagePojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(401));
        assertThat(msg.getMessage() , equalTo(ErrorMessage.UNAUTHORIZED_ACCESS));

    }

    @Story("User add a new entry but with empty data field")
    @Test(description = "User add a new entry but with empty data field")
    public void User_Adds_a_New_Entry_Empty_Data_Field() {

        //Test data
        TodoPojo item = TodoSteps.generateTodoEmptyDataField();
        String token = UserSteps.getUserToken();

        //Sending request
        Response res = TodoApi.addTodo(item,token);

        //Deserialization
        MessagePojo msg = res.body().as(MessagePojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(400));
        assertThat(msg.getMessage() , equalTo(ErrorMessage.EMPTY_ITEM_FIELD));


    }

    /*------------------------------------------------------------------------------------------------------------------
    Views Entry
    Positive scenario*/
    @Story("User views a new entry")
    @Test(description = "User views a new entry")
    public void User_Views_The_New_Entry() {

        //Test data
        TodoPojo todo = TodoSteps.generateTodo();
        String token = UserSteps.getUserToken();
        String id = TodoSteps.getTodoID(todo, token);
        String item = todo.getItem();

        //Sending request
        Response res = TodoApi.viewTodo(id , token);

        //Deserialization
        TodoPojo responseBody = res.body().as(TodoPojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(200));
        assertThat(responseBody.getItem() , equalTo(item));
        assertThat(responseBody.getIsCompleted() , equalTo(false));

    }

    //Negative scenario
    @Story("User tries to view a new entry but with no authentication")
    @Test(description = "User tries to view a new entry but with no authentication")
    public void User_Views_The_New_Entry_No_Auth() {

        //Test data
        TodoPojo todo = TodoSteps.generateTodo();
        String token = UserSteps.getUserToken();
        String id = TodoSteps.getTodoID(todo, token);

        //Sending request
        Response res = TodoApi.viewTodo(id , "");

        //Deserialization
        MessagePojo msg = res.body().as(MessagePojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(401));
        assertThat(msg.getMessage() , equalTo(ErrorMessage.UNAUTHORIZED_ACCESS));

    }

    /*------------------------------------------------------------------------------------------------------------------
    Deletes Entry
    Positive scenario*/
    @Story("User deletes an entry")
    @Test(description = "User deletes an entry")
    public void User_Deletes_The_New_Entry() {

        //Test data
        TodoPojo todo = TodoSteps.generateTodo();
        String token = UserSteps.getUserToken();
        String id = TodoSteps.getTodoID(todo, token);
        String item = todo.getItem();

        //Sending request
        Response res = TodoApi.deleteTodo(id , token);

        //Deserialization
        TodoPojo responseBody = res.body().as(TodoPojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(200));
        assertThat(responseBody.getItem() , equalTo(item));
        assertThat(responseBody.getIsCompleted() , equalTo(false));

    }

    //Negative scenario
    @Story("User tries to delete an entry but with no authentication")
    @Test(description = "User tries to delete an entry but with no authentication")
    public void User_Deletes_The_New_Entry_No_Auth() {

        //Test data
        TodoPojo todo = TodoSteps.generateTodo();
        String token = UserSteps.getUserToken();
        String id = TodoSteps.getTodoID(todo, token);

        //Sending request
        Response res = TodoApi.deleteTodo(id , "");

        //Deserialization
        MessagePojo msg = res.body().as(MessagePojo.class);

        //Assertions
        assertThat(res.statusCode() , equalTo(401));
        assertThat(msg.getMessage() , equalTo(ErrorMessage.UNAUTHORIZED_ACCESS));

    }
    //------------------------------------------------------------------------------------------------------------------

}
