package dev.pbania;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusIntegrationTest
public class GithubResourceIT {

    @Test
    public void testGetUserRepositories_HappyPath() {
        String username = "calzaghe";

        given()
                .pathParam("username", username)
                .accept(ContentType.JSON)
                .when()
                .get("/github/repos/{username}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", greaterThan(0))
                .body("repositoryName", everyItem(notNullValue()))
                .body("ownerLogin", everyItem(equalTo(username)))
                .body("branches", everyItem(not(empty())));
    }
}
