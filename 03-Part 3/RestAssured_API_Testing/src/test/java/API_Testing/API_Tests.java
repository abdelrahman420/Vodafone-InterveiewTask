package API_Testing;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class API_Tests {
    @Test
    public void GetAllPosts() 
    {
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts");
        assertEquals(200, response.getStatusCode());
        assertEquals(100, response.jsonPath().getList("").size());
    }

    @Test
    public void GetAllUsers() 
    {
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/users");
        assertEquals(200, response.getStatusCode());
        assertEquals(10, response.jsonPath().getList("").size());
    }


    @Test
    public void GetPostById() 
    {
        int postId = 3;
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/" + postId);
        assertEquals(200, response.getStatusCode());
        assertEquals(postId, response.jsonPath().getInt("id"));
    }


    @Test
    public void GetUserById() 
    {
        int userId = 7;
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/users/" + userId);
        assertEquals(200, response.getStatusCode());
        assertEquals(userId, response.jsonPath().getInt("id"));
    }


    @Test
    public void GetCommentsForPost() 
    {
        int postId = 3;
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/" + postId + "/comments");
        assertEquals(200, response.getStatusCode());
        assertTrue(response.jsonPath().getList("").size() > 0);  
    }
    
    @Test
    public void CreateNewPost() 
    {
        String requestBody = "{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";
        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            .body(requestBody)
            .post("https://jsonplaceholder.typicode.com/posts");
        assertEquals(201, response.getStatusCode());
        assertEquals("foo", response.jsonPath().getString("title"));
    }

    @Test
    public void UpdatePost() 
    {
        int postId = 7;
        String requestBody = "{ \"id\": 1, \"title\": \"updated title\", \"body\": \"bar\", \"userId\": 1 }";
        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            .body(requestBody)
            .put("https://jsonplaceholder.typicode.com/posts/" + postId);
        assertEquals(200, response.getStatusCode());
        assertEquals("updated title", response.jsonPath().getString("title"));
    }

    @Test
    public void DeletePost() 
    {
        int postId = 32;
        Response response = RestAssured.delete("https://jsonplaceholder.typicode.com/posts/" + postId);
        assertEquals(200, response.getStatusCode());
    }

}
