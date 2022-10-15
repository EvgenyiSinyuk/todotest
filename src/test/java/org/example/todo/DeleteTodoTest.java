package org.example.todo;

import io.restassured.response.Response;
import org.example.businessobjects.Todo;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class DeleteTodoTest extends BaseTodoTest {

    @Test
    public void deleteTodoTest() {
        Todo todo = new Todo("test10", false, "10");
        try {
            Response response = todoApi.deleteTodo(10);
            Assert.assertEquals(response.getStatusCode(), 200);
            List<Todo> todos = Arrays.asList(todoApi.getTodosList().getBody().as(Todo[].class));
            Assert.assertFalse(todos.contains(todo));
        } finally {
            todoApi.createTodos(todo);
        }
    }

    @Test
    public void deleteNotExistingTodoTest() {
        Response response = todoApi.deleteTodo(1666666);
        Assert.assertEquals(response.getStatusCode(), 400);
    }
}
