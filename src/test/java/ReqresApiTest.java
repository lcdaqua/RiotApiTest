import POJO.ReqresApi.User;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class ReqresApiTest {
    String createRequest = "/api/users";

    User user = new User("Antony", "Homeless");
    Gson gson = new Gson();

    @BeforeTest
    public void setUp(){
        RestAssured.baseURI = "https://reqres.in/";
    }

    @Test
    public void createUserTest(){
        String jsonString = gson.toJson(user);

        String response = given()
                .header("Content-type", "application/json")
                .and()
                .body(jsonString)
                .post(createRequest)
                .asString();

        User createdUser = gson.fromJson(response, User.class);
        Assert.assertTrue(!createdUser.getId().equals(null), "Пользователю не был присвоен ID");
        Assert.assertTrue(!createdUser.getCreatedAt().equals(null), "Пользователю не было присвоено время создания");
    }

}
