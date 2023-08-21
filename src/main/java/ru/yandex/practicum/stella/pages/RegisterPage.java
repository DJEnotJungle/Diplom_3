package ru.yandex.practicum.stella.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private WebDriver driver;
    public RegisterPage(WebDriver driver){
        this.driver=driver;
    }
    private final By name = By.xpath(".//fieldset[1]//div/input");
    private final By email = By.xpath(".//fieldset[2]//div/input");
    private final By password = By.xpath(".//div/input[@name = 'Пароль']");
    private final By registerButton = By.xpath(".//button[text() = 'Зарегистрироваться']");
    private final By incorrectPassword = By.xpath(".//fieldset[3]/div/p");
    private final By singInButton = By.xpath(".//p/a");

    @Step("Наживаем на кнопку Войти в форме регистрации")
    public void singInButtonClick(){
        driver.findElement(singInButton).click();
    }

    @Step("Заполняем и отправляем форму регистрации")
    public void sendTheRegistrationForm(String setName, String setEmail, String setPassword){
        driver.findElement(name).sendKeys(setName);
        driver.findElement(email).sendKeys(setEmail);
        driver.findElement(password).sendKeys(setPassword);
        driver.findElement(registerButton).click();
    }

    @Step("Проверяем что пароль не корректен")
    public String getTextAboutIncorrectPassword(){
        return driver.findElement(incorrectPassword).getText();
    }
}
