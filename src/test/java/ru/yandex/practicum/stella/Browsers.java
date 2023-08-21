package ru.yandex.practicum.stella;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;
import static ru.yandex.practicum.stella.Constants.MASTER_URL;

public class Browsers {
    private WebDriver driver;
    private ChromeOptions options;
    private String browserName = "chrome";

    public WebDriver get() {
        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                driver.get(MASTER_URL);
                break;
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "/home/enot/WebDriver/bin/chromedriver");
                options = new ChromeOptions();
                options.setBinary("/usr/bin/yandex-browser");
                driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                driver.get(MASTER_URL);
                break;
            default:
                throw new RuntimeException("Не верное имя браузера");
        }
        return driver;
    }
}
