package org.example.todo;

import org.example.models.TodoApi;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTodoTest {

    TodoApi todoApi = new TodoApi();

    @BeforeTest
    void prepareTestData() {
        todoApi.createTestData();
    }

    @AfterTest
    void clearTestData() {
        todoApi.clearTestData();
    }
}
