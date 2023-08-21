package ru.yandex.practicum.stella.register;

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
import ru.yandex.practicum.stella.dto.UserLoginDTO;
import ru.yandex.practicum.stella.pages.MainPage;
import ru.yandex.practicum.stella.pages.PersonalAreaPage;
import ru.yandex.practicum.stella.pages.RegisterPage;
import static org.hamcrest.CoreMatchers.is;

@RunWith(Parameterized.class)
@DisplayName("Тесты регистрации пользователя")
public class RegisterSuccessTest extends BeforeTest {

    private String token;
    private UserSteps userSteps = new UserSteps();
    private final String name;
    private final String email;
    private final String password;

    public RegisterSuccessTest(String name, String email, String password) {
        this.name = name;
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
    @DisplayName("Проверяем возможность авторизации через кнопку Войти")
    public void registerOnSignInButtonTest(){
        MainPage mainPage = new MainPage(driver);
        PersonalAreaPage personalAreaPage = new PersonalAreaPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        UserLoginDTO userLogin = new UserLoginDTO(email, password);
        mainPage.singInPress();
        personalAreaPage.registerPress();
        registerPage.sendTheRegistrationForm(name, email, password);
        ValidatableResponse responseLogin = userSteps.userLogin(userLogin);
        token=responseLogin.extract().path("accessToken");
        String result = personalAreaPage.checkSuccess();
        MatcherAssert.assertThat("Вы не зарегистрировались", result, is("Войти"));
    }

    @Test
    @DisplayName("Проверяем возможность авторизации через кнопку Личный кабинет")
    public void registerOnPersonalAreaButtonTest(){
        MainPage mainPage = new MainPage(driver);
        PersonalAreaPage personalAreaPage = new PersonalAreaPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        UserLoginDTO userLogin = new UserLoginDTO(email, password);
        mainPage.personalAreaPress();
        personalAreaPage.registerPress();
        registerPage.sendTheRegistrationForm(name, email, password);
        ValidatableResponse responseLogin = userSteps.userLogin(userLogin);
        token=responseLogin.extract().path("accessToken");
        String result = personalAreaPage.checkSuccess();
        MatcherAssert.assertThat("Вы не зарегистрировались", result, is("Войти"));
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
