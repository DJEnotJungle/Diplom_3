package ru.yandex.practicum.stella.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(singInButton));
        driver.findElement(singInButton).click();
    }

    @Step("Заполняем и отправляем форму регистрации")
    public void sendTheRegistrationForm(String setName, String setEmail, String setPassword){
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(registerButton));
        driver.findElement(name).sendKeys(setName);
        driver.findElement(email).sendKeys(setEmail);
        driver.findElement(password).sendKeys(setPassword);
        driver.findElement(registerButton).click();
    }

    @Step("Проверяем что пароль не корректен")
    public String getTextAboutIncorrectPassword(){
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(incorrectPassword));
        return driver.findElement(incorrectPassword).getText();
    }
}
