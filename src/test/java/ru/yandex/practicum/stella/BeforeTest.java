package ru.yandex.practicum.stella;

import io.restassured.RestAssured;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import static ru.yandex.practicum.stella.Constants.MASTER_URL;

public class BeforeTest {
    public WebDriver driver;
    private Browsers browsers = new Browsers();

    @Before
    public void setURL(){
        RestAssured.baseURI=MASTER_URL;
        driver= browsers.get();
    }
}

