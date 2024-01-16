package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest {

        @BeforeClass
        public void inIt()
        {
            RestAssured.baseURI = "https://gorest.co.in/public/v2";
            RestAssured.basePath = "/users" ;                                     //baseURI, basePath, endpoint = complete URL
        }
        static String name = "Abdul Kalaam";
        static String gender = "male";
        static String status = "active";
        static String email = TestUtils.getRandomValue() + "akwin123@gmail.com";
        static long id = 5914078;
        static String bearerToken = "0ecb2e0ced293e0295e93f650e31cabfd88fa4afb92584f55df29d203e5e2a7b";

        @Test
        public void verifyUserCreatedSuccessfully()
        {
            UserPojo userPojo = new UserPojo();
            userPojo.setName(name);                                             //setting all the values by passing parameters
            userPojo.setGender(gender);
            userPojo.setStatus(status);
            userPojo.setEmail(email);

            Response response =
                    given()
                            .header("Authorization" ,"Bearer" + bearerToken)
                            .contentType(ContentType.JSON)
                            .when()
                            .body(userPojo)
                            .post();
            response.prettyPrint();
            response.then().statusCode(401);
        }

        @Test
        public void getUser()
        {
            Response response =
                    given().log().all()
                            .when()
                            .get();
            response.prettyPrint();
            response.then().statusCode(200);
        }
        @Test
        public void sigleUser()
        {
            Response response =
                    given().log().all()
                            .when()
                            .get("/" +id);
            response.prettyPrint();
            response.then().statusCode(200);
        }

        @Test
        public void verifyUserUpdateSuccessfully()
        {
            UserPojo userPojo = new UserPojo();
            //setting all the values by passing parameters
            userPojo.setGender("female");
            userPojo.setStatus(status);


            Response response =
                    given().log().all()
                            .header("Authorization" ,"Bearer" + bearerToken)
                            .contentType(ContentType.JSON)
                            .when()
                            .body(userPojo)
                            .put("/" +id);

            response.prettyPrint();
            response.then().statusCode(404);
        }
        @Test
        public void verifyUserDeleteSuccessfully()
        {
            Response response =
                    given().log().all()
                            .header("Authorization" ,"Bearer" + bearerToken)
                            .contentType(ContentType.JSON)
                            .when()
                            .delete("/" +id);

            response.prettyPrint();
            response.then().statusCode(404);
        }
}
