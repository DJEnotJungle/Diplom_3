package ru.yandex.practicum.stella.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
        driver.findElement(buns).click();
        return driver.findElement(fluorescentBun).getText();
    }
    @Step("Проверяем переход в раздел соусы")
    public String saucesCheck(){
        driver.findElement(sauces).click();
        return driver.findElement(spicyClassic).getText();
    }

    @Step("Нажимаем на Начинки")
    public void toppingsClick(){
        driver.findElement(toppings).click();
    }

    @Step("Проверяем переход в раздел начинки")
    public String toppingsCheck(){
        toppingsClick();
        return driver.findElement(beef).getText();
    }

    @Step("Переходим на форму авторизации по кнопке Войти")
    public void singInPress(){
        driver.findElement(signIn).click();
    }

    @Step("Проверяем что логотип ведёт на главную страницу")
    public String clickOnLogo(){
        driver.findElement(logo).click();
        return checkMakeOrderButton();
    }

    @Step("Проверяем что кнопка конструктор ведёт на главную страницу")
    public String clickOnConstructor(){
        driver.findElement(constructorButton).click();
        return checkMakeOrderButton();
    }

    @Step("Провеяем успешность авторизации")
    public String checkMakeOrderButton(){
        return driver.findElement(makeOrderButton).getText();
    }

    @Step("Переходим на форму авторизации по кнопке Личный кабинет")
    public void personalAreaPress(){
        driver.findElement(personalArea).click();
    }

}