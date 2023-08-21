package ru.yandex.practicum.stella.ingredients;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.practicum.stella.pages.MainPage;
import static org.hamcrest.CoreMatchers.is;
import static ru.yandex.practicum.stella.Constants.MASTER_URL;

@DisplayName("Тесты переходов между вкладками Булочки Соусы Начинки")
public class IngredientTest {

    public WebDriver driver;

    @Before
    public void setURL(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get(MASTER_URL);
    }

    @Test
    @DisplayName("Проверяем возможность перехода в Начинки")
    public void toppingsCheck(){
        MainPage mainPage = new MainPage(driver);
        String result = mainPage.toppingsCheck();
        MatcherAssert.assertThat("Я нехотел булочку с соусом", result, is("Говяжий метеорит (отбивная)"));
    }

    @Test
    @DisplayName("Проверяем возможность перехода в соусы")
    public void saucesCheck(){
        MainPage mainPage = new MainPage(driver);
        String result = mainPage.saucesCheck();
        MatcherAssert.assertThat("Я нехотел булочку с соусом", result, is("Соус традиционный галактический"));
    }

    @Test
    @DisplayName("Проверяем возможность перехода в булочки")
    public void bunsCheck(){
        MainPage mainPage = new MainPage(driver);
        String result = mainPage.bunsCheck();
        MatcherAssert.assertThat("Я нехотел булочку с соусом", result, is("Флюоресцентная булка R2-D3"));
    }

    @After
    @Step("Закрываем браузер")
    public void deleteUserTurningOffTheBrowser(){
        driver.quit();
    }

}
