package in.reqres.api;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;

public class CommonHelper {

    public static void printNewUserIdAndName(String id, String newUserName){
        System.out.println("id: " + id + " userName: " + newUserName);
    }

    @Step("get total_pages from list users")
    public static Integer getTotalPages(){
        String pathUsers = "/users";
        RequestSpecification requestSpec = new TestsBase().requestSpec;
        return given(requestSpec)
                .when()
                .get(pathUsers + "")
                .then()
                .extract()
                .path("total_pages");
    }

    public static List<Integer> pages(){
        Integer limit = getTotalPages();
        return IntStream.rangeClosed(1, limit)
                .boxed()
                .collect(Collectors.toList());
    }
}
