package orderscootertest;

import orderscootertest.Browser;
import header.Header;
import model.MainPage;
import model.OrderPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static model.OrderPage.*;
import static model.OrderPage.inputFirstName;
import static model.OrderPage.inputSecondName;

@RunWith(Parameterized.class)
public class OrderTest {

    private  WebDriver driver;
    private final String fstName;
    private final String scndName;
    private final String address;
    private final String metroStn;
    private final String phnNumber;

    public static final String BROWSER_NAME_ENV_VARIABLE = "BROWSER_NAME";

    public OrderTest (String fstName, String scndName, String address, String metroStn, String phnNumber) {
        this.fstName = fstName;
        this.scndName = scndName;
        this.address = address;
        this.metroStn = metroStn;
        this.phnNumber = phnNumber;
    }

    private void setCookie(Cookie cookie) {
        driver.manage().addCookie(cookie);
    }

    @Before
    public void setUp() throws Exception {
        String browserName = System.getenv(BROWSER_NAME_ENV_VARIABLE);
        driver = getWebDriver(Browser.valueOf(browserName));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();


    }
    @After
    public void after() {
        driver.quit();
    }

    WebDriver getWebDriver(Browser browser) {
        switch (browser) {
            case CHROME:
                return new ChromeDriver();
            case FIREFOX:
                return new FirefoxDriver();
            case SAFARI:
                return new SafariDriver();
            default:
                throw new RuntimeException("unable to create a web driver");
        }
    }

    @Parameterized.Parameters
    public static Object[][] input() {
        return new Object[][] {
                {"Иван", "Иванов", "Иваново", "Площадь революции", "+79999999999"},
                {"Сергей", "Сергеев", "Сергеево", "Театральная", "+79094442222"},
        };
    }

    @Test
    public void OrderTestHeaderButton () {

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        setCookie(new Cookie("Cartoshka", "true"));
        setCookie(new Cookie("Cartoshka-legacy", "true"));
        driver.navigate().refresh();
        Header header = new Header(driver);
        header.clickOrderButton();

        OrderPage orderPage = new OrderPage(driver);

        orderPage.inputFirstName();
        driver.findElement(inputFirstName).sendKeys(fstName);
        orderPage.inputSecondName();
        driver.findElement(inputSecondName).sendKeys(scndName);
        orderPage.inputDlvrAddress();
        driver.findElement(inputDeliveryAddress).sendKeys(address);
        orderPage.inputMetroStation();
        driver.findElement(inputMetroStation).sendKeys(metroStn);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element
                = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='select-search__select']")));
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div[4]/div/div[2]/ul/li[1]")).click();
        orderPage.inputPhnNumb();
        driver.findElement(inputPhoneNumber).sendKeys(phnNumber);
        orderPage.clickNextButton();
        orderPage.choiceDateDelivery();
        orderPage.choiceRentTime();
        orderPage.clickConfirmOrderButton();
        orderPage.clickYesButton();
        orderPage.isOrderIsProcessed();
        orderPage.printOrderNumber();
        Assert.assertNotEquals(null, orderPage.printOrderNumber());// Проверка, что заказ оформлен
    }
    @Test
    public void OrderTestButtonOnPage () {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        setCookie(new Cookie("Cartoshka", "true"));
        setCookie(new Cookie("Cartoshka-legacy", "true"));
        driver.navigate().refresh();
        mainPage.clickOrderButtonOnPage();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.inputFirstName();
        driver.findElement(inputFirstName).sendKeys(fstName);
        orderPage.inputSecondName();
        driver.findElement(inputSecondName).sendKeys(scndName);
        orderPage.inputDlvrAddress();
        driver.findElement(inputDeliveryAddress).sendKeys(address);
        orderPage.inputMetroStation();
        driver.findElement(inputMetroStation).sendKeys(metroStn);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element
                = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='select-search__select']")));
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div[4]/div/div[2]/ul/li[1]")).click();
        orderPage.inputPhnNumb();
        driver.findElement(inputPhoneNumber).sendKeys(phnNumber);
        orderPage.clickNextButton();
        orderPage.choiceDateDelivery();
        orderPage.choiceRentTime();
        orderPage.clickConfirmOrderButton();
        orderPage.clickYesButton();
        orderPage.isOrderIsProcessed();
        Assert.assertTrue(orderPage.isOrderIsProcessed());
        orderPage.printOrderNumber();


    }
}
