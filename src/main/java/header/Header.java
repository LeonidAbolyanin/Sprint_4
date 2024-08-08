package header;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {

    protected static WebDriver driver;

    public Header (WebDriver driver) {
        this.driver = driver;
    }

    // Кнопка "Статус заказа"
    private static final By orderStatusButton = By.className("Header_Link__1TAG7");
    // Поле ввода номера заказа
    private static final By orderNumberInput = By.xpath("//input[@class='Input_Input__1iN_Z Header_Input__xIoUq']");
    // Кнопка "Go"
    private static final By searchOrderButton = By.cssSelector("button.Button_Button__ra12g.Header_Button__28dPO");;
    //Кнопка "Заказать" в шапке
    private static final By orderHeaderButton = By.className("Button_Button__ra12g");


    // Кликатель по кнопке заказать в шапке
    public Header clickOrderButton() {
        driver.findElement(orderHeaderButton).click();
        return this;
    }


}