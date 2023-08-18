package ru.yandex.practicum.stella.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private WebDriver driver;
    public MainPage(WebDriver driver){
        this.driver=driver;
    }
    private final By signIn = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By personalArea = By.xpath(".//nav/a/p");
    private final By makeOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By constructorButton = By.xpath(".//ul/li[1]/a[@class='AppHeader_header__link__3D_hX']");
    private final By logo = By.xpath(".//div/a");
    private final By toppings = By.xpath(".//span[text()='Начинки']");
    private final By sauces = By.xpath(".//span[text()='Соусы']");
    private final By buns = By.xpath(".//span[text()='Булки']");
    private final By beef = By.xpath(".//a[@class='BurgerIngredient_ingredient__1TVf6 ml-4 mr-4 mb-8']/p[text()='Говяжий метеорит (отбивная)']");
    private final By spicyClassic = By.xpath(".//a/p[text()='Соус традиционный галактический']");
    private final By fluorescentBun = By.xpath(".//a[@class='BurgerIngredient_ingredient__1TVf6 ml-4 mr-4 mb-8']/p[text()='Флюоресцентная булка R2-D3']");

    @Step("Проверяем переход в раздел Булок")
    public String bunsCheck(){
        toppingsClick();
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(buns));
        driver.findElement(buns).click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(fluorescentBun));
        return driver.findElement(fluorescentBun).getText();
    }

    @Step("Проверяем переход в раздел соусы")
    public String saucesCheck(){
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(sauces));
        driver.findElement(sauces).click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(spicyClassic));
        return driver.findElement(spicyClassic).getText();
    }

    @Step("Нажимаем на Начинки")
    public void toppingsClick(){
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(toppings));
        driver.findElement(toppings).click();
    }

    @Step("Проверяем переход в раздел начинки")
    public String toppingsCheck(){
        toppingsClick();
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(beef));
        return driver.findElement(beef).getText();
    }

    @Step("Переходим на форму авторизации по кнопке Войти")
    public void singInPress(){
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(signIn));
        driver.findElement(signIn).click();
    }

    @Step("Проверяем что логотип ведёт на главную страницу")
    public String clickOnLogo(){
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(logo));
        driver.findElement(logo).click();
        return checkMakeOrderButton();
    }

    @Step("Проверяем что кнопка конструктор ведёт на главную страницу")
    public String clickOnConstructor(){
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(constructorButton));
        driver.findElement(constructorButton).click();
        return checkMakeOrderButton();
    }

    @Step("Провеяем успешность авторизации")
    public String checkMakeOrderButton(){
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(makeOrderButton));
        return driver.findElement(makeOrderButton).getText();
    }

    @Step("Переходим на форму авторизации по кнопке Личный кабинет")
    public void personalAreaPress(){
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(personalArea));
        driver.findElement(personalArea).click();
    }

}