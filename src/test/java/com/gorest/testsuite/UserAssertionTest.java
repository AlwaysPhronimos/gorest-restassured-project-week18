package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;


public class UserAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public void inIt(){
    RestAssured.baseURI ="https://gorest.co.in";
    RestAssured.basePath ="/public/v2";
}
@Test
    public void usersAssert() {
        response =
                given()
                        .queryParam("page", 1)
                        .queryParam("per-page", 10)
                        .when()
                        .get("users")
                        .then()
                        .statusCode(200);

//1. Verify the if the total record is 20
    response.body(("size()"), equalTo(10));

//2. Verify the if the name of id = 5914134 is equal to ”Ekalavya Embranthiri”
    response.body("findAll{it.id == 5914134}.name", hasItem("Ekalavya Embranthiri"));

// 3. Check the single ‘Name’ in the Array list (Tanirika Khan)
    response.body("findAll{it.id == 5914131}.name", hasItem("Tanirika Khan"));

// 4. Check the multiple ‘Names’ in the ArrayList (Kailash Pillai., Chandrabhan Adiga, Arnesh Singh )
    response.body("findAll{it.id}.name", hasItems("Kailash Pillai", "Chandrabhan Adiga", "Arnesh Singh"));

// 5. Verify the emai of userid = 5914130 is equal “tandon_iv_aanandinii@prosacco.example”
    response.body("findAll{it.id == 5914130}.email", hasItem("chandrabhan_adiga@shields.example"));

    //6. Verify the status is “Active” of user name is “Malati Gupta II”
    response.body("findAll{it.name == 'Malati Gupta II'}.status", hasItem("active"));

// 7. Verify the Gender = male of user name is “Malati Gupta II”
    response.body("findAll{it.name == 'Chidambaram Talwar'}.gender", hasItem("female"));
    }
}


