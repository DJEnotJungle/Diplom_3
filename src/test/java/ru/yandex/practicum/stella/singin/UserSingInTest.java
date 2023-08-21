package ru.yandex.practicum.stella.singin;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.stella.BeforeTest;
import ru.yandex.practicum.stella.api.UserSteps;
import ru.yandex.practicum.stella.dto.UserCreateDTO;
import ru.yandex.practicum.stella.pages.MainPage;
import ru.yandex.practicum.stella.pages.PersonalAreaPage;
import ru.yandex.practicum.stella.pages.RegisterPage;
import static org.hamcrest.CoreMatchers.is;

@RunWith(Parameterized.class)
@DisplayName("Тесты авторизации пользователя")
public class UserSingInTest extends BeforeTest {

    private String token;
    private UserSteps userSteps = new UserSteps();
    private final String name;
    private final String email;
    private final String password;

    public UserSingInTest(String name, String email, String password) {
        this.name =name;
        this.email = email;
        this.password = password;
    }

    @Parameterized.Parameters(name = "{0} {1}")
    public static Object[][] getText(){
        return new Object[][]{
                {"dsads", "saddsfdgf@gmail.com", "gelooleg"},
                {"asddas", "ldkjdsnbf@gmail.com", "gsdfssdo"}
        };
    }

    @Test
    @DisplayName("Проверяем возможность входа через кнопку Войти")
    public void loginOnSignInButtonTest(){
        MainPage mainPage = new MainPage(driver);
        PersonalAreaPage personalAreaPage = new PersonalAreaPage(driver);
        UserCreateDTO userCreate = new UserCreateDTO(email, password, name);
        ValidatableResponse responseCreate = userSteps.userCreate(userCreate);
        token=responseCreate.extract().path("accessToken");
        mainPage.singInPress();
        personalAreaPage.sendTheLoginForm(email, password);
        String result = mainPage.checkMakeOrderButton();
        MatcherAssert.assertThat("Вы не авторизовались", result, is("Оформить заказ"));
        mainPage.personalAreaPress();
        personalAreaPage.exitButtonClick();
    }

    @Test
    @DisplayName("Проверяем возможность входа через кнопку Личный кабинет")
    public void loginOnPersonalArealTest(){
        MainPage mainPage = new MainPage(driver);
        PersonalAreaPage personalAreaPage = new PersonalAreaPage(driver);
        UserCreateDTO userCreate = new UserCreateDTO(email, password, name);
        ValidatableResponse responseCreate = userSteps.userCreate(userCreate);
        token=responseCreate.extract().path("accessToken");
        mainPage.personalAreaPress();
        personalAreaPage.sendTheLoginForm(email, password);
        String result = mainPage.checkMakeOrderButton();
        MatcherAssert.assertThat("Вы не авторизовались", result, is("Оформить заказ"));
        mainPage.personalAreaPress();
        personalAreaPage.exitButtonClick();
    }

    @Test
    @DisplayName("Проверяем возможность входа через регистрацию")
    public void loginOnRegisterTest(){
        MainPage mainPage = new MainPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        PersonalAreaPage personalAreaPage = new PersonalAreaPage(driver);
        UserCreateDTO userCreate = new UserCreateDTO(email, password, name);
        ValidatableResponse responseCreate = userSteps.userCreate(userCreate);
        token=responseCreate.extract().path("accessToken");
        mainPage.personalAreaPress();
        personalAreaPage.registerPress();
        registerPage.singInButtonClick();
        personalAreaPage.sendTheLoginForm(email, password);
        String result = mainPage.checkMakeOrderButton();
        MatcherAssert.assertThat("Вы не авторизовались", result, is("Оформить заказ"));
        mainPage.personalAreaPress();
        personalAreaPage.exitButtonClick();
    }

    @Test
    @DisplayName("Проверяем возможность входа через востановление пароля")
    public void loginOnPasswordRecoveryTest(){
        MainPage mainPage = new MainPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        PersonalAreaPage personalAreaPage = new PersonalAreaPage(driver);
        UserCreateDTO userCreate = new UserCreateDTO(email, password, name);
        ValidatableResponse responseCreate = userSteps.userCreate(userCreate);
        token=responseCreate.extract().path("accessToken");
        mainPage.personalAreaPress();
        personalAreaPage.loginInPasswordRecoveryClick();
        personalAreaPage.sendTheLoginForm(email, password);
        String result = mainPage.checkMakeOrderButton();
        MatcherAssert.assertThat("Вы не авторизовались", result, is("Оформить заказ"));
        mainPage.personalAreaPress();
        personalAreaPage.exitButtonClick();
    }

    @After
    @Step("Проверяем надо ли удалить пользователя и закрываем браузер")
    public void deleteUserTurningOffTheBrowser(){
        driver.quit();
        if (!(token == null || token.isEmpty())) {
            System.out.println("DELETE");
            userSteps.userDelete(token);
        } else {
            System.out.println("NOT DELETE");
        }
    }
}
