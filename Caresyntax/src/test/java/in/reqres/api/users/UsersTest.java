package in.reqres.api.users;

import in.reqres.api.TestsBase;
import in.reqres.api.users.request.User;
import in.reqres.api.users.response.DataItem;
import in.reqres.api.users.response.UsersResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static in.reqres.api.CommonHelper.*;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@Epic("API tests")
public class UsersTest extends TestsBase {
    private static String userId;
    private final String pathUsers = "/users/";
    private static List<DataItem> users = new ArrayList<>();

    @Description("GET list of all users")
    @MethodSource("in.reqres.api.CommonHelper#pages")
    @ParameterizedTest(name =
            "When send GET /users?page={index}" +
                    "Then status code 200 " +
                    "And return all user list")
    void whenGetUsersOnPage_thenReturnUsersResponse_andAddToUsersList(Integer pageNumber){
        UsersResponse response = given(requestSpec)
                .queryParam("page", pageNumber)
                .when()
                .get(pathUsers)
                .then()
                .assertThat()
                .statusCode(SC_OK)
                .extract()
                .response()
                .as(UsersResponse.class);
        users.addAll(response.getData());
        if (pageNumber.equals(getTotalPages())) System.out.println(users);
    }

    @Test
    @DisplayName("When send GET /users?page={pageNumber} " +
                "Then status code 200 " +
                "And return user list response")
    @Description("2. GET first names of all users on page 2 ")
    void whenGetUsersOnPage_thenReturnUsersResponse(){
        String page = "2";
        List<String> expectedNames = Arrays.asList("Michael", "Lindsay", "Tobias", "Byron", "George", "Rachel");

        UsersResponse response = given(requestSpec)
                .queryParam("page", page)
                .when()
                .get(pathUsers)
                .then()
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .extract()
                .response()
                .as(UsersResponse.class);

        List<String> firstNames = response.getData()
                .stream()
                .map(DataItem::getFirstName)
                .collect(Collectors.toList());

        assertThat(firstNames).isEqualTo(expectedNames);
    }


    @Nested
    @DisplayName("Tests user CRUD operations")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class CRUD {

        @Test
        @Order(1)
        @DisplayName("Given new user " +
                "When send POST /users " +
                "Then status code 201 " +
                "And return new user response")
        @Description("3. Create a new user with the name 'Arnold' and Job as 'Dev' and print new users details name and ID ")
        void givenUser_whenPostUsers_thenReturnUsersResponse(){
            String name = "Arnold";
            String job = "Dev";

            User user = new User().setName(name).setJob(job);

            Response response = given(requestSpec)
                    .body(user)
                    .when()
                    .post(pathUsers)
                    .then()
                    .assertThat()
                    .statusCode(SC_CREATED)
                    .and()
                    .extract()
                    .response();

            userId = response.path("id");
            String newUserName = response.path("name");
            printNewUserIdAndName(userId, newUserName);
        }

        @Test
        @Order(2)
        @DisplayName("Given created user " +
                "And new user name " +
                "When send PUT /users/{userId} " +
                "Then status code 200 " +
                "And return new user response")
        @Description("Update the user's name to 'Sam' ")
        void givenUser_AndNewUserName_whenPutUsers_thenReturnUsersResponse(){
            String name = "Sam";
            String job = "Dev";

            User user = new User().setName(name).setJob(job);

            given(requestSpec)
                    .body(user)
                    .when()
                    .put(pathUsers + userId)
                    .then()
                    .assertThat()
                    .statusCode(SC_OK)
                    .and()
                    .assertThat()
                    .body("name", equalTo(name));
        }

        @Test
        @Order(3)
        @DisplayName("Given created user " +
                "And new user name " +
                "When send DELETE /users/{userId} " +
                "Then status code 204 " +
                "And return new user response")
        @Description("Update the user's name to 'Sam' ")
        void givenUser_whenDeleteUsers_thenReturnSC204(){
            given(requestSpec)
                    .when()
                    .delete(pathUsers + userId)
                    .then()
                    .assertThat()
                    .statusCode(SC_NO_CONTENT);
        }
    }
}
