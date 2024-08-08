package model;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final By ORDER_STATUS_BUTTON = By.xpath("//*[@class='Header_Link__1TAG7']");
    private static final By ORDER_NUMBER_INPUT_FIELD =
            By.xpath("//input[@class='Input_Input__1iN_Z Header_Input__xIoUq']");
    private static final By GO_BUTTON =
            By.cssSelector("button.Button_Button__ra12g.Header_Button__28dPO");
    private static final By NOTFOUND_IMAGE = By.cssSelector(".Track_NotFound__6oaoY > img");

    //переменная для нумерации вопросов и ответов
    public static int questionIntNumber;

    //универсальный локатор для вопросов
    public static By questionNumber = By.id("accordion__heading-" + questionIntNumber);

    //универсальный локатор для ответов
    public static By answerNumber = By.xpath(".//div/div[@id='accordion__panel-" + questionIntNumber + "']/p");

    //Кнопка "Заказать" на странице
    private static final By orderHeaderButtonOnPage = By.xpath(".//div[@class=\"Home_FinishButton__1_cWm\"]/button[text()=\"Заказать\"]");

    protected static WebDriver driver;

    public MainPage(WebDriver driver) {
        MainPage.driver = driver;
    }
    // Метод для открытия главной страницы
    public MainPage open() {
        driver.get(PAGE_URL);
        return this;
    }
    // Метод для клика по кнопке "Статус заказа"
    public MainPage clickOrderStatusButton() {
        driver.findElement(ORDER_STATUS_BUTTON).click();
        return this;
    }
    // Метод для ввода номера заказа в поле "Введите номер заказа"
    public MainPage enterNonExistingOrderNumber(String orderNumber) {
        driver.findElement(ORDER_NUMBER_INPUT_FIELD).sendKeys(orderNumber);
        return this;
    }
    // Кликер кнопки "Го!" в шапке
    public MainPage clickGoButton() {
        driver.findElement(GO_BUTTON).click();
        return this;
    }
    // Метод для проверки появления картинки "Неверный номер заказа"
    public boolean isImageNotFoundDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement imageNotFound =
                wait.until(ExpectedConditions.visibilityOfElementLocated(NOTFOUND_IMAGE));
        return imageNotFound.isDisplayed();
    }
    // Кликатель по кнопке заказать на странице
    public MainPage clickOrderButtonOnPage() {
        driver.findElement(orderHeaderButtonOnPage).click();
        return this;
    }
    // Метод для клика на вопрос из раздела "Вопросы о важном
    public MainPage clickOnTheQuestion(int questionFaq){
    WebElement element = driver.findElement(By.xpath(".//div[contains(@id,'accordion__heading-" + questionFaq + "')]/parent::div"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
        return this;
    }
    // Метод для поиска ответа на вопрос из раздела "Вопросы о важном"
    public String findAnswer(int answerFaq){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement answerElement =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[contains(@id,'accordion__panel-" + answerFaq + "')]/p")));
        driver.findElement(By.xpath(".//div[contains(@id,'accordion__panel-" + answerFaq + "')]/p")).click();
        return answerElement.getText();
    }
}