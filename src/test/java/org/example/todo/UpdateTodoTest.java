package org.example.todo;

import io.restassured.response.Response;
import org.example.businessobjects.Todo;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class UpdateTodoTest extends BaseTodoTest {

    @Test
    public void updateTodoTest() {
        try {
            Todo todo = new Todo("updated_test1", false, "1");
            Response response = todoApi.updateTodo(todo);
            Assert.assertEquals(response.getStatusCode(), 200);
            List<Todo> todos = Arrays.asList(todoApi.getTodosList().getBody().as(Todo[].class));
            Assert.assertTrue(todos.contains(todo));
        } finally {
            Todo todo = new Todo("test1", true, "1");
            todoApi.updateTodo(todo);
        }
    }

    @Test
    public void updateNotExistingTodoTest() {
        Todo todo = new Todo("updated_todo", false, "100000");
        Response response = todoApi.updateTodo(todo);
        Assert.assertEquals(response.getStatusCode(), 404);
    }
}
