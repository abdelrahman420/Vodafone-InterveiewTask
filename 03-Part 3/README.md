# RestAssured_API_Testing

## Table of Contents
1. [Overview and Requirements](#overview-and-requirements)
2. [Prerequisites](#prerequisites)
3. [Setup of the Project](#setup-of-the-project)
3. [Creating Test Cases](#creating-test-cases)
4. [Run the Tests](#run-the-tests)

## Overview and Requirements
The Rest-Assured API Testing project goal is to automate API tests using the Rest-Assured library. I will use JSONPlaceholder APIs for testing but you can use another APIs.

Requirements for this project:  
- 1 - List of test cases proposed for automation 
- 2 - Please automate proposed list of test cases ( using rest-assured technique)

## Prerequisites

- 1 - Ensure JDK 11 or higher is installed on your machine.
- 2 - Maven is required for project build and dependencies management.
- 3 - install aproper IDE that supports java development (Iam using Eclipse IDE)

## Setup of the Project
- 1 - in Eclipse click on file then new then select maven project.
 
- 2 - Click on "Create a simple project(skip archetype selection)" to check it then click next. 

- 3 - Type the name of the project and click finish.

- 4 - open the `pom.xml` file and add rhese dependencies

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>RestAssured_API_Testing</groupId>
  <artifactId>RestAssured_API_Testing</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  
  <dependencies>
<!-- https://mvnrepository.com/artifact/io.rest-assured/rest-assured -->
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>5.5.0</version>
    <scope>test</scope>
</dependency>


<!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.11.0</version>
    <scope>test</scope>
</dependency>

<!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.11.0</version>
    <scope>test</scope>
</dependency>

</dependencies>


</project>			
```
## Creating Test Cases

- Test Case 1: Get All Posts 
    ```java
    @Test
    public void GetAllPosts() 
    {
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts");
        assertEquals(200, response.getStatusCode());
        assertEquals(100, response.jsonPath().getList("").size());
    }
    ```
- Test Case 2: Get All users
    ```java
    @Test
    public void GetAllUsers() 
    {
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/users");
        assertEquals(200, response.getStatusCode());
        assertEquals(10, response.jsonPath().getList("").size());
    }
    ```
- Test Case 3: Get post by id
    ```java
    @Test
    public void GetPostById() 
    {
        int postId = 3;
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/" + postId);
        assertEquals(200, response.getStatusCode());
        assertEquals(postId, response.jsonPath().getInt("id"));
    }
    ```
- Test Case 4: Get user by id
    ```java
    @Test
    public void GetUserById() 
    {
        int userId = 7;
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/users/" + userId);
        assertEquals(200, response.getStatusCode());
        assertEquals(userId, response.jsonPath().getInt("id"));
    }
    ```
- Test Case 5: Get comments for a post
    ```java
    @Test
    public void testGetCommentsForPost() 
    {
        int postId = 3;
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/" + postId + "/comments");
        assertEquals(200, response.getStatusCode());
        assertTrue(response.jsonPath().getList("").size() > 0);  
    }
    ```
- Test Case 6: Create a New Post
    ```java
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
    ```
- Test Case 7: Update an Existing Post
    ```java
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

    ```
- Test Case 8: Delete a Post
    ```java
    @Test
    public void DeletePost() 
    {
        int postId = 32;
        Response response = RestAssured.delete("https://jsonplaceholder.typicode.com/posts/" + postId);
        assertEquals(200, response.getStatusCode());
    }
    ```
 
After we defined all tests create a package under `src/test/java` name it `API_Testing` under it create the class `API_Tests.java`

```java
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

```
## Run the Tests

to run tests in eclipse Right-click on the JsonPlaceholderTests class 
Select Run As -> JUnit Test.
