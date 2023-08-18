package ru.yandex.practicum.stella;

import io.restassured.RestAssured;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import static ru.yandex.practicum.stella.Constants.MASTER_URL;

public class BeforeTest {
    public WebDriver driver;
    @Before
    public void setURL(){
        RestAssured.baseURI=MASTER_URL;

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        /*Драйвер Yandex
            System.setProperty("webdriver.chrome.driver","path/to/yandex/browser");
            ChromeDriverService service = new ChromeDriverService.Builder().build();
            this.driver = new ChromeDriver(service);*/

        driver.manage().window().maximize();
        driver.get(MASTER_URL);
    }
}

