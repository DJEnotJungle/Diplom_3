package ru.yandex.practicum.stella.personalarea;

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
import ru.yandex.practicum.stella.jsonclass.UserCreateJson;
import ru.yandex.practicum.stella.pages.MainPage;
import ru.yandex.practicum.stella.pages.PersonalAreaPage;
import static org.hamcrest.CoreMatchers.is;

@RunWith(Parameterized.class)
@DisplayName("Тест входа в личный кабинет")
public class PersonalAreaTest extends BeforeTest {
    private String token;
    UserSteps userSteps = new UserSteps();
    private final String name;
    private final String email;
    private final String password;

    public PersonalAreaTest(String name, String email, String password) {
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
    @DisplayName("Проверяем возможность входа в личный кабинет")
    public void personalAreaTest(){
        MainPage mainPage = new MainPage(driver);
        PersonalAreaPage personalAreaPage = new PersonalAreaPage(driver);
        UserCreateJson userCreate = new UserCreateJson(email, password, name);
        ValidatableResponse responseCreate = userSteps.userCreate(userCreate);
        token=responseCreate.extract().path("accessToken");
        mainPage.singInPress();
        personalAreaPage.sendTheLoginForm(email, password);
        mainPage.personalAreaPress();
        String result = personalAreaPage.getTextPersonalArea();
        MatcherAssert.assertThat("Вы не вошли в личный кабинет", result, is("В этом разделе вы можете изменить свои персональные данные"));
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
