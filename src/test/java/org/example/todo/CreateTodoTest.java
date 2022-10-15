package org.example.todo;

import io.restassured.response.Response;
import org.example.businessobjects.Todo;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class CreateTodoTest extends BaseTodoTest {

    @Test
    public void createTodoTest() {
        try {
            Todo todo = new Todo("test_todo", false, "1938");
            Response response = todoApi.createTodos(todo);
            Assert.assertEquals(response.getStatusCode(), 201);
            List<Todo> todos = Arrays.asList(todoApi.getTodosList().getBody().as(Todo[].class));
            Assert.assertTrue(todos.contains(todo));
        } finally {
            todoApi.deleteTodo(1938);
        }
    }
}
