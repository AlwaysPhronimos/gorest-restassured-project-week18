package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2";
    }

    @Test
    public void usersExtract() {
        response =
                given()
                        .queryParam("page", 1)
                        .queryParam("per-page", 20)
                        .when()
                        .get("users")
                        .then()
                        .statusCode(200);

//        1. Extract the All Ids
        List<Integer> listOfIds = response.extract().path("id");
        System.out.println("List of All Ids are : " + listOfIds);

//        2. Extract the all Names
        List<String> listOfNames = response.extract().path("name");
        System.out.println("List of all names : " + listOfNames);

//        3. Extract the name of 5th object
        String name = response.extract().path("[5].name");
        System.out.println("Name of the 5th object is : " + name);

//        4. Extract the names of all object whose status = inactive
        List<String> nameOfAllObject = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("The List Of Names Having Inactive Status : " + nameOfAllObject);

//        5. Extract the gender of all the object whose status = active
        List<String> genderOfAllObject = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println("The List Of All Genders Having Active Status : " + genderOfAllObject);

//        6. Print the names of the object whose gender = female
        List<String> nameOfAllTheObject = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("The List of names of object whose gender is female : " + nameOfAllTheObject);

//        7. Get all the emails of the object where status = inactive
        List<String> allEmails = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("All emails : " + allEmails);

//        8. Get the ids of the object where gender = male
        List<Integer> idsOfMale = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("The Ids of the object with gender is male : ");

//        9. Get all the status
        List<String> allStatus = response.extract().path("status");
        System.out.println("The List Of All Statuses : " + allStatus);

//        10. Get email of the object where name = Lal Dwivedi
        String email = response.extract().path("[4].email");
        System.out.println("Get email for " + email);

//        11. Get gender of id = 5914189
        String gender = response.extract().path("[6].gender");
        System.out.println("Get email for " + gender);
    }
}
