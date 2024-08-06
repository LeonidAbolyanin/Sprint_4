package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class OrderPage {
    protected static WebDriver driver;

    // локатор поля "Имя"
    public static By inputFirstName = By.xpath("/html/body/div/div/div[2]/div[2]/div[1]/input");

    // локатор поля "Фамилия"
    public static By inputSecondName = By.xpath("/html/body/div/div/div[2]/div[2]/div[2]/input");

    // локатор поля "Адрес"
    public static By inputDeliveryAddress = By.xpath("/html/body/div/div/div[2]/div[2]/div[3]/input");

    // локатор поля "Метро"
    public static By inputMetroStation = By.xpath("/html/body/div/div/div[2]/div[2]/div[4]/div/div/input");

    // локатор поля "Телефон"
    public static By inputPhoneNumber = By.xpath("/html/body/div/div/div[2]/div[2]/div[5]/input");

    // кнопка "Далее"
    public static By nextButton = By.xpath("/html/body/div/div/div[2]/div[3]/button");

    // локатор поля "Когда привезти самокат"
    public static By dateDelivery = By.xpath("/html/body/div/div/div[2]/div[2]/div[1]"); //By.className("react-datepicker__input-container");

    // локатор поля "Срок аренды"
    public static By rentTime = By.xpath("/html/body/div/div/div[2]/div[2]/div[2]");

    // локаторы чек-боксов "Цвет самоката черный" и "Цвет самоката серый"
    public static By scooterBlackColour = By.id("black");
    public static By scooterGreyColour = By.id("grey");

    // локатор поля "Комментарий курьеру"
    public static By inputCommentOrder = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[4]");

    // Кнопка "Заказать"
    public static By confirmOrderButton = By.xpath("/html/body/div/div/div[2]/div[3]/button[2]");

    // Кнопка "Да"
    public static By yesButton = By.xpath("/html/body/div/div/div[2]/div[5]/div[2]/button[2]");

    // Локатор окна "Заказ оформлен"
    public static By orderIsProcessed = By.xpath("/html/body/div/div/div[2]/div[5]/div[2]/button");

    public OrderPage(WebDriver driver) {
        OrderPage.driver =driver;
    }

    // Метод для ввода данных в поле Имя
    public OrderPage enterFirstName(String firstName) {
        driver.findElement(inputFirstName).click();
        driver.findElement(inputFirstName).sendKeys(firstName);
        return this;
    }



    public OrderPage inputFirstName() {
        driver.findElement(inputFirstName).click();
        // driver.findElement(inputFirstName).sendKeys(fstName);
        return this;
    }
    public OrderPage inputSecondName() {
        driver.findElement(inputSecondName).click();
        //  driver.findElement(inputSecondName).sendKeys(scndName);
        return this;
    }
    public OrderPage inputDlvrAddress() {
        driver.findElement(inputDeliveryAddress).click();
        // driver.findElement(inputDeliveryAddress).sendKeys(address);
        return this;
    }
    public OrderPage inputMetroStation() {
        driver.findElement(inputMetroStation).click();
        // driver.findElement(inputMetroStation).sendKeys(metroStn);
        //  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // WebElement element
        //         = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='select-search__select']")));
        //  driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div[4]/div/div[2]/ul/li[1]")).click();
        return this;
    }

    public OrderPage inputPhnNumb() {
        driver.findElement(inputPhoneNumber).click();
        //driver.findElement(inputPhoneNumber).sendKeys(phnNumber);
        return this;
    }







    // Кликер "Далее"
    public OrderPage clickNextButton(){
        driver.findElement(nextButton).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return this;
    }

    // Выбор даты доставки. Метод по умолчанию выбирает завтрашний день
    public OrderPage choiceDateDelivery(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement elementOne
                = wait.until(ExpectedConditions.visibilityOfElementLocated(dateDelivery));
        driver.findElement(dateDelivery).click();
        WebElement elementTwo
                = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div[2]/div[1]/div[2]/div[2]/div/div/div[2]")));
        driver.findElement(By.xpath(".//div[contains(@tabindex,'0') and contains(@class,'react-datepicker__day')]/following::div[contains(@tabindex,'-1') and contains(@class,'react-datepicker__day')]")).click();
        return this;
    }

    // Выбор срока аренды
    public OrderPage choiceRentTime(){
        driver.findElement(rentTime).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element
                = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div[2]/div[2]/div[2]")));
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div[2]/div[2]/div[1]")).click();
        return this;
    }

    // Кликер кнопки "Заказать"
    public OrderPage clickConfirmOrderButton() {
        driver.findElement(confirmOrderButton).click();
        return this;
    }
    // Кликер кнопки "Да"
    public OrderPage clickYesButton() {
        driver.findElement(yesButton).click();
        return this;
    }
    // Метод для проверки, что заказ принят
    public boolean isOrderIsProcessed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement orderWindowProcessed =
                wait.until(ExpectedConditions.visibilityOfElementLocated(orderIsProcessed));
        return orderWindowProcessed.isDisplayed();
    }
    // Вывод номера заказа в консоль
    public OrderPage printOrderNumber(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div[5]/div[1]/div")));
        String orderNumber = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[5]/div[1]/div")).getText();
        System.out.println(orderNumber);
        return this;
    }
// Заполнение данных на первом листе заказа, кроме станции метро
    //public OrderPage inputOrderValue(String firstName, String secondName, String deliveryAddress, String phoneNumber, String nameMetroStation){
    //driver.findElement(inputFirstName).click();
    // driver.findElement(inputFirstName).sendKeys(firstName);
    // driver.findElement(inputSecondName).click();
    // driver.findElement(inputSecondName).sendKeys(secondName);
    // driver.findElement(inputDeliveryAddress).click();
    // driver.findElement(inputDeliveryAddress).sendKeys(deliveryAddress);

    // driver.findElement(inputMetroStation).click();
    //driver.findElement(inputMetroStation).sendKeys(nameMetroStation);
    // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    // WebElement element
    //        = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='select-search__select']")));
    // driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div[4]/div/div[2]/ul/li[1]")).click();
    //driver.findElement(inputPhoneNumber).click();
    //driver.findElement(inputPhoneNumber).sendKeys(phoneNumber);
    //driver.findElement(nextButton).click();
    //return this;
    //}




}

