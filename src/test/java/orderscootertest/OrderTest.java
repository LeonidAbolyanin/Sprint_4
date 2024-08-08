package orderscootertest;

import basetest.BaseTest;
import model.MainPage;
import model.OrderPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {

    private final By orderButton; // Локатор кнопки "Заказать"
    private final String fstName; // "Имя"
    private final String scndName; // "Фамилия"
    private final String delAddress; // "Адрес"
    private final String subwayStation; // "Название станции"
    private final String telNumber; // "Номер телефона"
    private final String rentDay; // Можно использовать следующие выражения: "сутки", "двое суток", "трое суток", "четверо суток", "пятеро суток", "шестеро суток"

    public OrderTest (By orderButton, String fstName, String scndName, String delAddress, String subwayStation, String telNumber, String rentDay) {
        this.orderButton = orderButton;
        this.fstName = fstName;
        this.scndName = scndName;
        this.delAddress = delAddress;
        this.subwayStation = subwayStation;
        this.telNumber = telNumber;
        this.rentDay = rentDay;

    }
    @Parameterized.Parameters
    public static Object[][] input() {
        return new Object[][]{
                {By.xpath(".//div/button[@class='Button_Button__ra12g']"), "Тор", "Одинссон", "Асгард", "Парк победы", "+79094443333", "сутки"},
                {By.xpath(".//div/button[@class='Button_Button__ra12g']"), "Локи", "Одинссон", "Йотунхейм", "Чертаново", "89993334444", "трое суток"},
                {By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[contains(@class, 'Button_Middle__1CSJM')]"), "Тор", "Одинссон", "Асгард", "Парк победы", "+79094443333", "сутки"},
                {By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[contains(@class, 'Button_Middle__1CSJM')]"), "Локи", "Одинссон", "Йотунхейм", "Чертаново", "89993334444", "трое суток"},
        };
    }

    @Test
    public void OrderTestVersionTwo (){
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        setCookie(new Cookie("Cartoshka", "true"));
        setCookie(new Cookie("Cartoshka-legacy", "true"));
        driver.navigate().refresh();
        driver.findElement(orderButton).click();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.inputOrderValue(fstName, scndName, delAddress, subwayStation, telNumber);
        orderPage.inputOrderValueAfterNextButton(rentDay);
        orderPage.isOrderIsProcessed();
        orderPage.printOrderNumber();
    }
}
