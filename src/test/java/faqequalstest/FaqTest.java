package faqequalstest;
import faqequalstest.Browser;
import model.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FaqTest {
    private WebDriver driver;
    public static final String BROWSER_NAME_ENV_VARIABLE = "BROWSER_NAME";

    private int question;
    private String expectedAnswer;

    public FaqTest(int question, String expectedAnswer){
        this.question = question;
        this.expectedAnswer = expectedAnswer;
    }
    private void setCookie(Cookie cookie) {
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
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, "Cутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        });

    }
    @Test
    public void FaqSecondTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        // Добаление куки
        setCookie(new Cookie("Cartoshka", "true"));
        setCookie(new Cookie("Cartoshka-legacy", "true"));
        driver.navigate().refresh();

        // Найдем элемент вопроса по его тексту и кликнем на него
        WebElement element = driver.findElement(By.xpath(".//div[contains(@id,'accordion__heading-" + question + "')]/parent::div"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();

        // Найдем соответствующий ответ
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement answerElement =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[contains(@id,'accordion__panel-" + question + "')]/p")));
        driver.findElement(By.xpath(".//div[contains(@id,'accordion__panel-" + question + "')]/p")).click();
        String actualAnswer = answerElement.getText();

        //System.out.println(actualAnswer);

        // Проверим, что ответ соответствует ожидаемому
        assertEquals(expectedAnswer, actualAnswer);
    }
}



