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

    public static By answerNumberZero = By.xpath("//*[@id='accordion__panel-0']");
    public static By answerNumberOne = By.xpath("//*[@id='accordion__panel-1']");

    //Кнопка "Заказать" на странице
    private static final By orderHeaderButtonOnPage = By.xpath("//*[@id='root']/div/div/div[4]/div[2]/div[5]/button");

    protected static WebDriver driver;

    public MainPage(WebDriver driver) {
        MainPage.driver = driver;
    }

    public MainPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    public MainPage clickOrderStatusButton() {
        driver.findElement(ORDER_STATUS_BUTTON).click();
        return this;
    }

    public MainPage enterNonExistingOrderNumber(String orderNumber) {
        driver.findElement(ORDER_NUMBER_INPUT_FIELD).sendKeys(orderNumber);
        return this;
    }

    public MainPage clickGoButton() {
        driver.findElement(GO_BUTTON).click();
        return this;
    }

    public boolean isImageNotFoundDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement imageNotFound =
                wait.until(ExpectedConditions.visibilityOfElementLocated(NOTFOUND_IMAGE));
        return imageNotFound.isDisplayed();
    }

    // Кликатель по вопросу
    public void clickQue(int questionIntNumber) {

        WebElement element = driver.findElement(questionNumber);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    // Метод для получения текста из ответа
    public static void getAnswerText(int questionIntNumber) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String answer = driver.findElement(answerNumber).getText();
        //element.click();
        //driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }

    // Кликатель по кнопке заказать на страанице
    public MainPage clickOrderButtonOnPage() {
        //WebElement element = driver.findElement(orderHeaderButtonOnPage);
        //((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(orderHeaderButtonOnPage).click();
        return this;
    }

    // метод ожидания прогрузки
    public void waitForLoadAnswer(int questionIntNumber) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(answerNumber).getText() != null
                && !driver.findElement(answerNumber).getText().isEmpty()
        ));
    }

    public static Object getAnswer(int questionIntNumber){
        String text = driver.findElement(answerNumber).getText();
        return text;
    }
}







