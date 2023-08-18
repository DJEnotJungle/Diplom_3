package ru.yandex.practicum.stella.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAreaPage {

    private WebDriver driver;
    public PersonalAreaPage(WebDriver driver){
        this.driver=driver;
    }
    private final By register = By.xpath(".//p[@class='undefined text text_type_main-default text_color_inactive mb-4']/a");
    private final By singInMasterButton = By.xpath("//*[@id='root']/div/main/div/form/button");
    private final By email = By.xpath(".//fieldset[1]/div/div/input");
    private final By password = By.xpath(".//fieldset[2]/div/div/input");
    private final By exitButton = By.xpath(".//button[text()='Выход']");
    private final By passwordRecovery = By.xpath(".//p[@class='undefined text text_type_main-default text_color_inactive']/a");
    private final By singInButton = By.xpath(".//p/a");
    private final By checkPersonalArea = By.xpath(".//nav/p");

    @Step("Проверяем что личный кабинет открылся успешно")
    public String getTextPersonalArea(){
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(exitButton));
        return driver.findElement(checkPersonalArea).getText();
    }

    @Step("Переходим в окно востановления пароля")
    public void loginInPasswordRecoveryClick(){
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(passwordRecovery));
        driver.findElement(passwordRecovery).click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(singInButton));
        driver.findElement(singInButton).click();
    }

    @Step("Заполняем и отправляем форму авторизации через главную кнопку Войти")
    public void sendTheLoginForm(String setEmail, String setPassword){
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(singInMasterButton));
        driver.findElement(email).sendKeys(setEmail);
        driver.findElement(password).sendKeys(setPassword);
        driver.findElement(singInMasterButton).click();
    }

    @Step("Выход из аккаунта")
    public void exitButtonClick(){
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(exitButton));
        driver.findElement(exitButton).click();
    }

    @Step("Нажимаем на Зарегистрироваться")
    public void registerPress(){
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(register));
        driver.findElement(register).click();
    }

    @Step("Проверяем что после успешной регистрации мы попадаем на форму авторизации")
    public String checkSuccess(){
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(singInMasterButton));
        return driver.findElement(singInMasterButton).getText();
    }

}
