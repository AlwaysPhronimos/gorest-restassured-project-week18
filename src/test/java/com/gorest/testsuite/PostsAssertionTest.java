package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class PostsAssertionTest {
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

//        1. Verify  if the total record is 25
        response.body("size()" ,equalTo(25));

//        2. Verify if the title of id = 93997 is equal to ”Demitto conqueror atavus argumentum corrupti
//        cohaero libero.”
        response.body("[1].title" ,equalTo("Demitto conqueror atavus argumentum corrupti cohaero libero."));

//        3. Check the single user_id in the Array list (5914249)
        response.body("[2].user_id" ,equalTo(5914249));

//        4. Check the multiple ids in the ArrayList (5914254 , 5914251 ,5914249)
        response.body("user_id" ,hasItems(5914254 , 5914251 ,5914249));

//        5. Verify the body of userid = 5914197 is equal "Demonstro cubo curia. Canonicus fuga arcus. Culpo sub rerum.
//        Armarium deporto peccatus. Arguo vilitas absens. Sublime crux suscipio. Eaque somnus recusandae. Sursum usque deleo.
//        Alioqui vacuus contigo. Pel vorax adduco. Deleniti velit suggero. Culpo cibo illo. Volaticus altus constans."

        response.body("[5].body" ,equalTo("Demonstro cubo curia. Canonicus fuga arcus. Culpo sub rerum. " +
                "Armarium deporto peccatus. Arguo vilitas absens. Sublime crux suscipio. Eaque somnus recusandae. " +
                "Sursum usque deleo. Alioqui vacuus contigo. Pel vorax adduco. Deleniti velit suggero. Culpo cibo illo. " +
                "Volaticus altus constans."));

    }

}
