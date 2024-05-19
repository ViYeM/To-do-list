package com.labdessoft.roteiro01.integration;

import com.labdessoft.roteiro01.Roteiro01Application;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@SpringBootTest(classes = {Roteiro01Application.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class TaskControllerIntegrationTest {

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.port = 8080;
    }

    @Test
    public void givenUrl_whenSuccessOnGetsResponseAndJsonHasRequiredKV_thenCorrect() {
        get("/api/tasks").then().statusCode(200);
    }

    @Test
    public void givenUrl_whenSuccessOnGetsResponseAndJsonHasOneTask_thenCorrect() {
        get("/api/tasks/1").then().statusCode(200)
                .assertThat().body("description", equalTo("Primeira tarefa"));
    }

    @Test
    public void givenTaskDetails_whenAddTask_thenCorrect() {
        given().contentType("application/json")
                .body(new Task("Nova tarefa"))
                .when().post("/api/tasks")
                .then().statusCode(201)
                .assertThat().body("description", equalTo("Nova tarefa"));
    }

    @Test
    public void givenUpdatedTask_whenEditTask_thenCorrect() {
        given().contentType("application/json")
                .body(new Task("Tarefa atualizada"))
                .when().put("/api/tasks/1")
                .then().statusCode(200)
                .assertThat().body("description", equalTo("Tarefa atualizada"));
    }

    @Test
    public void givenTaskId_whenTaskCompleted_thenCorrect() {
        given().when().patch("/api/tasks/1/complete")
                .then().statusCode(200)
                .assertThat().body("completed", equalTo(true));
    }

    @Test
    public void givenTaskId_whenDeleteTask_thenCorrect() {
        given().when().delete("/api/tasks/1")
                .then().statusCode(200);
    }
}