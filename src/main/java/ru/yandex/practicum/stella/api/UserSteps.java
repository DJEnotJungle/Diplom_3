package ru.yandex.practicum.stella.api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ru.yandex.practicum.stella.jsonclass.UserCreateJson;
import ru.yandex.practicum.stella.jsonclass.UserLoginJson;
import static io.restassured.RestAssured.given;
import static ru.yandex.practicum.stella.Constants.*;

public class UserSteps {

    @Step("Создание уникального пользователя")
    public ValidatableResponse userCreate(UserCreateJson userCreateJson){
        return given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .header("Content-type", "application/json")
                .body(userCreateJson)
                .when()
                .post(CREATE_USER_PATH)
                .then();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse userDelete(String token){
        return given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .delete(CHANGE_USER_DATA_OR_DELETE_OR_GET_DATA_PATH)
                .then();
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse userLogin(UserLoginJson userLoginJson){
        return given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .header("Content-type", "application/json")
                .body(userLoginJson)
                .when()
                .post(LOG_USER_PATH)
                .then();
    }
}
