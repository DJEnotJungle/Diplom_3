package ru.yandex.practicum.stella.api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ru.yandex.practicum.stella.dto.UserCreateDTO;
import ru.yandex.practicum.stella.dto.UserLoginDTO;
import static io.restassured.RestAssured.given;
import static ru.yandex.practicum.stella.Constants.*;

public class UserSteps {

    @Step("Создание уникального пользователя")
    public ValidatableResponse userCreate(UserCreateDTO userCreateDTO){
        return given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .header("Content-type", "application/json")
                .body(userCreateDTO)
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
    public ValidatableResponse userLogin(UserLoginDTO userLoginDTO){
        return given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .header("Content-type", "application/json")
                .body(userLoginDTO)
                .when()
                .post(LOG_USER_PATH)
                .then();
    }
}
