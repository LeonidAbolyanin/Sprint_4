package checkwrongordertest;
import basetest.BaseTest;
import model.MainPage;
import java.time.Duration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebTest extends BaseTest {

    private static final By ORDER_STATUS_BUTTON = By.xpath("//*[@class='Header_Link__1TAG7']");
    private static final By ORDER_NUMBER_INPUT_FIELD =
            By.xpath("//input[@class='Input_Input__1iN_Z Header_Input__xIoUq']");
    private static final By GO_BUTTON =
            By.cssSelector("button.Button_Button__ra12g.Header_Button__28dPO");
    public static final By NOTFOUND_IMAGE = By.cssSelector(".Track_NotFound__6oaoY > img");
    public static final String ORDER_NUMBER = "824001";
    public static final String BROWSER_NAME_ENV_VARIABLE = "BROWSER_NAME";



    @Test
    public void checkOrderStatus_notFound_imageDisplayed() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        setCookie(new Cookie("Cartoshka", "true"));
        setCookie(new Cookie("Cartoshka-legacy", "true"));
        driver.navigate().refresh();
        driver.findElement(ORDER_STATUS_BUTTON).click();
        driver.findElement(ORDER_NUMBER_INPUT_FIELD).sendKeys(ORDER_NUMBER);
        driver.findElement(GO_BUTTON).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement imageNotFound =
                wait.until(ExpectedConditions.visibilityOfElementLocated(NOTFOUND_IMAGE));
        Assert.assertTrue(imageNotFound.isDisplayed());
    }

    @Test
    public void checkOrderStatus_notFound_dom_imageDisplayed() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        setCookie(new Cookie("Cartoshka", "true"));
        setCookie(new Cookie("Cartoshka-legacy", "true"));
        driver.navigate().refresh();
        mainPage.clickOrderStatusButton();
        mainPage.enterNonExistingOrderNumber(ORDER_NUMBER);
        mainPage.clickGoButton();
        Assert.assertTrue(mainPage.isImageNotFoundDisplayed());
    }

    @Test
    public void checkOrderStatus_notFound_fluentApi_imageDisplayed() {
        boolean imageNotFoundDisplayed =
                new MainPage(driver)
                        .open()
                        .clickOrderStatusButton()
                        .enterNonExistingOrderNumber(ORDER_NUMBER)
                        .clickGoButton()
                        .isImageNotFoundDisplayed();
        Assert.assertTrue(imageNotFoundDisplayed);
    }
}
