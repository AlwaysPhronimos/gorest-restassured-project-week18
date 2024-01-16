package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2";
    }

    @Test
    public void usersAssert() {
        response =
                given()
                        .queryParam("page", 1)
                        .queryParam("per_page", 25)
                        .when()
                        .get("/posts")
                        .then()
                        .statusCode(200);

//        1. Extract the title
        List<String> title = response.extract().path("title");
        System.out.println("List of all titles : " +title);

//        2. Extract the total number of record
        List <Integer> dataLength = response.extract().path("length");
        System.out.println("Total number of records : " +dataLength.size());

//        3. Extract the body of 15th record
        String body = response.extract().path("[14].body");
        System.out.println("Body of 15th record : " +body);

//        4. Extract the user_id of all the records
        List<Integer> allUserIds  = response.extract().path("user_id");
        System.out.println("User_id of all  records : " +allUserIds);

//        5. Extract the title of all the records
        List<String> titleOfAllRecords = response.extract().path("title");
        System.out.println("List of all titles : " +titleOfAllRecords );

//        6. Extract the title of all records whose user_id = 5914137
        String titleOfUserId = response.extract().path("[18].title");
        System.out.println("Title of all records : " +titleOfUserId);

//        7. Extract the body of all records whose id = 93902
        String bodyOfAllRecords = response.extract().path("[21].body");
        System.out.println("Body of all records : " +bodyOfAllRecords);
    }
}
