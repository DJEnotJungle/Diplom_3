package ru.yandex.practicum.stella.register;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.stella.BeforeTest;
import ru.yandex.practicum.stella.pages.MainPage;
import ru.yandex.practicum.stella.pages.PersonalAreaPage;
import ru.yandex.practicum.stella.pages.RegisterPage;
import static org.hamcrest.CoreMatchers.is;

@RunWith(Parameterized.class)
@DisplayName("Тесты регистрации пользователя с ошибкой")
public class RegisterErrorTest extends BeforeTest {

    private final String name;
    private final String email;
    private final String password;

    public RegisterErrorTest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Parameterized.Parameters(name = "{0} {1}")
    public static Object[][] getText(){
        return new Object[][]{
                {"dsads", "saddsfdgf@gmail.com", "qwer"},
                {"asddas", "ldkjdsnbf@gmail.com", "123"}
        };
    }

    @Test
    @DisplayName("Проверяем возможность авторизации через кнопку Войти")
    public void registerOnSignInButtonTest(){
        MainPage mainPage = new MainPage(driver);
        PersonalAreaPage personalAreaPage = new PersonalAreaPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        mainPage.singInPress();
        personalAreaPage.registerPress();
        registerPage.sendTheRegistrationForm(name, email, password);
        String result = registerPage.getTextAboutIncorrectPassword();
        MatcherAssert.assertThat("Вы зарегистрировались", result, is("Некорректный пароль"));
    }

    @Test
    @DisplayName("Проверяем возможность авторизации через кнопку Личный кабинет")
    public void registerOnPersonalAreaButtonTest(){
        MainPage mainPage = new MainPage(driver);
        PersonalAreaPage personalAreaPage = new PersonalAreaPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        mainPage.personalAreaPress();
        personalAreaPage.registerPress();
        registerPage.sendTheRegistrationForm(name, email, password);
        String result = registerPage.getTextAboutIncorrectPassword();
        MatcherAssert.assertThat("Вы зарегистрировались", result, is("Некорректный пароль"));
    }

    @After
    @Step("Закрываем браузе")
    public void turningOffTheBrowser(){
        driver.quit();
    }
}
