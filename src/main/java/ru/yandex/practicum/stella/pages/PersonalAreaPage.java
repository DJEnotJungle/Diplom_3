package ru.yandex.practicum.stella.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAreaPage {

    private WebDriver driver;
    public PersonalAreaPage(WebDriver driver){
        this.driver=driver;
    }
    private final By register = By.xpath(".//p[@class='undefined text text_type_main-default text_color_inactive mb-4']/a");
    private final By singInMasterButton = By.xpath("//*[@id='root']/div/main/div/form/button[text()='Войти']");
    private final By email = By.xpath(".//fieldset[1]/div/div/input");
    private final By password = By.xpath(".//fieldset[2]/div/div/input");
    private final By exitButton = By.xpath(".//button[text()='Выход']");
    private final By passwordRecovery = By.xpath(".//p[@class='undefined text text_type_main-default text_color_inactive']/a");
    private final By singInButton = By.xpath(".//p/a");
    private final By checkPersonalArea = By.xpath(".//nav/p");

    @Step("Проверяем что личный кабинет открылся успешно")
    public String getTextPersonalArea(){
        return driver.findElement(checkPersonalArea).getText();
    }

    @Step("Переходим в окно востановления пароля")
    public void loginInPasswordRecoveryClick(){
        driver.findElement(passwordRecovery).click();
        driver.findElement(singInButton).click();
    }

    @Step("Заполняем и отправляем форму авторизации через главную кнопку Войти")
    public void sendTheLoginForm(String setEmail, String setPassword){
        driver.findElement(email).sendKeys(setEmail);
        driver.findElement(password).sendKeys(setPassword);
        driver.findElement(singInMasterButton).click();
    }

    @Step("Выход из аккаунта")
    public void exitButtonClick(){
        driver.findElement(exitButton).click();
    }

    @Step("Нажимаем на Зарегистрироваться")
    public void registerPress(){
        driver.findElement(register).click();
    }

    @Step("Проверяем что после успешной регистрации мы попадаем на форму авторизации")
    public String checkSuccess(){
        return driver.findElement(singInMasterButton).getText();
    }

}
