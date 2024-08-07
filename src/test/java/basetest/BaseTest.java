package basetest;
import java.time.Duration;
import model.MainPage;
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

public class BaseTest {

    public static final String BROWSER_NAME_ENV_VARIABLE = "BROWSER_NAME";

    protected WebDriver driver;

    public void setCookie(Cookie cookie) {
        driver.manage().addCookie(cookie);
    }

    @Before
    public void setUp() throws Exception {
        String browserName = System.getenv(BROWSER_NAME_ENV_VARIABLE);
        driver = getWebDriver(Browser.valueOf(browserName));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

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
}

enum Browser {
    CHROME,
    FIREFOX,
    SAFARI;
}