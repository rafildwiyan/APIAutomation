import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static java.time.LocalDate.now;
import static org.hamcrest.Matchers.*;

@Test
public class FitHub {
    // TC 1 : As a user, I able to get data from specific array index
    public void MetGET(){
        given().get("https://reqres.in/api/users?page=1").
                then().statusCode(200).body("data.email[4]",equalTo("charles.morris@reqres.in"));
    }

    // TC 2 : As a user, I able to create new user data
    public void MetPOST(){
        String jsonData= "{\n    \"name\": \"Rafil\",\n    \"job\": \"QA Engineer\"\n}";
        given().body(jsonData).
                when().post("https://reqres.in/api/users").
                then().statusCode(201).body("id",greaterThan("0"));
    }

    // TC 3 : As a user, I able to update existing user data
    public void MetPUT(){
        String jsonData= "{\n    \"name\": \"Rafil\",\n    \"job\": \"QA Engineer\"\n}";
        given().body(jsonData).
                when().put("https://reqres.in/api/api/users/1").
                then().statusCode(200).body("updatedAt",containsString(now().toString()));
    }
}
