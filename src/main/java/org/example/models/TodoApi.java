package org.example.models;

import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.BaseApi;
import org.example.businessobjects.Todo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class TodoApi extends BaseApi {

    private final String GET_POST_TODOS = "/todos";
    private final String PUT_DELETE_TODOS = "/todos/%s";
    public final List<Todo> TEST_TODOS;

    {
        try {
            TEST_TODOS = getTestTodos();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Get full TODO list")
    public Response getTodosList() {
        return get(GET_POST_TODOS);
    }

    @Step("Get TODO list with next params offset/limit")
    public Response getTodosList(HashMap<String, Integer> params) {
        return get(GET_POST_TODOS, params);
    }

    @Step("Create TODO with next params: {text}, {completed}, {id}")
    public Response createTodos(String text, boolean completed, long id) {
        Todo todo = new Todo(text, completed, String.valueOf(id));

        return post(GET_POST_TODOS, getGson().toJson(todo));
    }

    @Step("Create TODO with next params: {text}, {completed}, {id}")
    public Response createTodos(Todo todo) {

        return post(GET_POST_TODOS, getGson().toJson(todo));
    }

    @Step("Update TODO with next params: {text}, {completed}, {id}")
    public Response updateTodo(Todo todo) {

        return put(String.format(PUT_DELETE_TODOS, todo.getId()), getGson().toJson(todo));
    }

    @Step("Delete TODO with id: {id}")
    public Response deleteTodo(long id) {

        return delete(String.format(PUT_DELETE_TODOS, id));
    }

    public void createTestData() {
        TodoApi todoApi = new TodoApi();
        for (Todo todo : TEST_TODOS) {
            todoApi.createTodos(todo);
        }
    }

    public void clearTestData() {
        TodoApi todoApi = new TodoApi();
        for (Todo todo : TEST_TODOS) {
            todoApi.deleteTodo(todo.getId());
        }
    }

    private List<Todo> getTestTodos() throws FileNotFoundException {
        JsonReader jsonReader = new JsonReader(new FileReader("src/test/resources/todos.json"));
        Type listType = new TypeToken<List<Todo>>() {}.getType();
        return getGson().fromJson(jsonReader, listType);
    }
}
