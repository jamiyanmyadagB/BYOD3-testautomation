package csv302;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GradeApiTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    // ─── TASK 1: Core API Tests ─────────────────────────────────────────────

    @Test
    public void testGetAllPosts() {
        given()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testGetPostById() {
        given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", not(emptyOrNullString()));
    }

    @Test
    public void testCreatePost() {
        String requestBody = "{ \"title\": \"GradeHub Test\", \"body\": \"CSV302 POST test\", \"userId\": 1 }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("GradeHub Test"));
    }

    // ─── TASK 2: Negative Tests ──────────────────────────────────────────────

    @Test
    public void testGetNonExistentPost() {
        given()
                .when()
                .get("/posts/99999")
                .then()
                .statusCode(404);
    }

    @Test
    public void testDeletePost() {
        given()
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(200);
    }
}