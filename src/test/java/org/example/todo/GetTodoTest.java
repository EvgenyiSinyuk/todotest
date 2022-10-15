package org.example.todo;

import io.restassured.response.Response;
import org.example.businessobjects.Todo;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GetTodoTest extends BaseTodoTest {

    @Test
    public void getTodoListTest() {
        List<Todo> todos = Arrays.asList(todoApi.getTodosList().getBody().as(Todo[].class));
        Assert.assertEquals(todos, todoApi.TEST_TODOS);
    }

    @Test
    public void getTodoListOffsetTest() {
        Response response = todoApi.getTodosList(new HashMap() {
            {
                put("offset", 5);
            }
        });
        Assert.assertEquals(response.getStatusCode(), 200);
        List<Todo> todos = Arrays.asList(response.getBody().as(Todo[].class));
        Assert.assertEquals(todos, todoApi.TEST_TODOS.subList(5, 10));
    }

    @Test
    public void getTodoListLimitTest() {
        Response response = todoApi.getTodosList(new HashMap() {
            {
                put("limit", 5);
            }
        });
        Assert.assertEquals(response.getStatusCode(), 200);
        List<Todo> todos = Arrays.asList(response.getBody().as(Todo[].class));
        Assert.assertEquals(todos, todoApi.TEST_TODOS.subList(0, 5));
    }

    @Test
    public void getTodoListLimitNOffsetTest() {
        Response response = todoApi.getTodosList(new HashMap() {
            {
                put("offset", 6);
                put("limit", 9);
            }
        });
        Assert.assertEquals(response.getStatusCode(), 200);
        List<Todo> todos = Arrays.asList(response.getBody().as(Todo[].class));
        Assert.assertEquals(todos, todoApi.TEST_TODOS.subList(6, 9));
    }

    @Test
    public void getTodoListZeroLimitTest() {
        Response response = todoApi.getTodosList(new HashMap() {
            {
                put("limit", 0);
            }
        });
        Assert.assertEquals(response.getStatusCode(), 200);
        List<Todo> todos = Arrays.asList(response.getBody().as(Todo[].class));
        Assert.assertTrue(todos.size() == 0);
    }

    @Test
    public void getTodoListNegativeLimitTest() {
        Response response = todoApi.getTodosList(new HashMap() {
            {
                put("limit", -1);
            }
        });
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test
    public void getTodoListMaxOffsetTest() {
        Response response = todoApi.getTodosList(new HashMap() {
            {
                put("offset", 10);
            }
        });
        Assert.assertEquals(response.getStatusCode(), 200);
        List<Todo> todos = Arrays.asList(response.getBody().as(Todo[].class));
        Assert.assertTrue(todos.size() == 0);
    }

    @Test
    public void getTodoListNegativeOffsetTest() {
        Response response = todoApi.getTodosList(new HashMap() {
            {
                put("offset", -1);
            }
        });
        Assert.assertEquals(response.getStatusCode(), 400);
    }
}
