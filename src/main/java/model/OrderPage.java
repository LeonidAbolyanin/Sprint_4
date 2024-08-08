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
    public static By inputFirstName = By.xpath(".//div[@class=\"Input_InputContainer__3NykH\"]/input[@placeholder=\"* Имя\"]");

    // локатор поля "Фамилия"
    public static By inputSecondName = By.xpath(".//div[@class=\"Input_InputContainer__3NykH\"]/input[@placeholder=\"* Фамилия\"]");

    // локатор поля "Адрес"
    public static By inputDeliveryAddress = By.xpath(".//div[@class=\"Input_InputContainer__3NykH\"]/input[@placeholder=\"* Адрес: куда привезти заказ\"]");

    // локатор поля "Метро"
    public static By inputMetroStation = By.xpath(".//div[@class=\"select-search__value\"]/input[@placeholder='* Станция метро']");

    // локатор поля "Телефон"
    public static By inputPhoneNumber = By.xpath(".//div[@class=\"Input_InputContainer__3NykH\"]/input[@placeholder=\"* Телефон: на него позвонит курьер\"]");

    // кнопка "Далее"
    public static By nextButton = By.xpath(".//div[@class=\"Order_NextButton__1_rCA\"]/button[text()=\"Далее\"]");

    // локатор поля "Когда привезти самокат"
    public static By dateDelivery = By.xpath(".//div[@class=\"react-datepicker__input-container\"]/input[@placeholder=\"* Когда привезти самокат\"]");

    // локатор поля "Срок аренды"
    public static By rentTime = By.xpath(".//div[@class=\"Dropdown-root\"]");

    // локатор поля "Комментарий курьеру"
    public static By inputCommentOrder = By.xpath(".//div[@class=\"Input_InputContainer__3NykH\"]/input[@placeholder=\"Комментарий для курьера\"]");

    // Кнопка "Заказать"
    public static By confirmOrderButton = By.xpath(".//div[@class=\"Order_Buttons__1xGrp\"]/button[text()=\"Заказать\"]");

    // Кнопка "Да"
    public static By yesButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[contains(@class, 'Button_Middle__1CSJM') and contains(text(), 'Да')]");

    // Локатор окна "Заказ оформлен"
    public static By orderIsProcessed = By.xpath(".//div[@class=\"Order_ModalHeader__3FDaJ\" and contains(text(), \"Заказ оформлен\")]");

    // Локатор кнопки "Посмотреть"
    public static By viewInfoAboutOrder = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button[contains(@class, 'Button_Middle__1CSJM')]");

    // Локатор поля "Номер заказа" на странице с инфо о заказе
    public static By inputNumberOrder = By.xpath(".//div[@class='Input_InputContainer__3NykH']/input[contains(@class, 'Input_Responsible__1jDKN')]");

    public OrderPage(WebDriver driver) {
        OrderPage.driver =driver;
    }
    // Метод для ввода данных в поле Имя
    public OrderPage enterFirstName(String firstName) {
        driver.findElement(inputFirstName).click();
        driver.findElement(inputFirstName).sendKeys(firstName);
        return this;
    }
    // Метод для ввода данных в поле "Имя"
    public OrderPage inputFirstName() {
        driver.findElement(inputFirstName).click();
        // driver.findElement(inputFirstName).sendKeys(fstName);
        return this;
    }
    // Метод для ввода данных в поле "Фамилия"
    public OrderPage enterSecondName(String secondName) {
        driver.findElement(inputSecondName).click();
        driver.findElement(inputSecondName).sendKeys(secondName);
        return this;
    }
    // Метод для ввода данных в поле "Адрес"
    public OrderPage enterDlvrAddress(String deliveryAddress) {
        driver.findElement(inputDeliveryAddress).click();
        driver.findElement(inputDeliveryAddress).sendKeys(deliveryAddress);
        return this;
    }
    // Метод, позволяющий выбирать станцию по введенному названию
    public OrderPage inputMetroStation(String metroStn) {
        driver.findElement(inputMetroStation).click();
        driver.findElement(inputMetroStation).sendKeys(metroStn);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='select-search__select']")));
        driver.findElement(By.xpath(".//div[@class='select-search__select']/ul/li[@data-index='0']")).click();
        return this;
    }
    // Метод для ввода данных в поле "Телефон"
    public OrderPage inputPhnNumb(String phoneNumber) {
        driver.findElement(inputPhoneNumber).click();
        driver.findElement(inputPhoneNumber).sendKeys(phoneNumber);
        return this;
    }
    // Кликер "Далее"
    public OrderPage clickNextButton(){
        driver.findElement(nextButton).click();
        return this;
    }
    // Выбор даты доставки. Метод по умолчанию выбирает завтрашний день
    public OrderPage choiceDeliveryNextDay(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateDelivery));
        driver.findElement(dateDelivery).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@class='react-datepicker']")));
        driver.findElement(By.xpath(".//div[contains(@tabindex,'0') and contains(@class,'react-datepicker__day')]/following::div[contains(@tabindex,'-1') and contains(@class,'react-datepicker__day')]")).click();
        return this;
    }
    // Метод для выбора даты с возможностью выбора. Не используется, т.к. требует доработки
    public OrderPage choiceDateDelivery(int day){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateDelivery));
        driver.findElement(dateDelivery).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@class='react-datepicker']")));
        driver.findElement(By.xpath(".//div[@class='react-datepicker__week']/div[contains(@class, 'react-datepicker__day') and contains(text(), '" + day + "')]")).click();
        return this;
    }
    // Выбор срока аренды
    public OrderPage choiceRentTime(String rentDay){
        driver.findElement(rentTime).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@class='Dropdown-menu']")));
        driver.findElement(By.xpath(".//div[@class='Dropdown-menu']/div[@class='Dropdown-option' and text()='" + rentDay + "']")).click();
        return this;
    }
    // Метод для выбора цвета самоката: на выбор доступны только "Grey" или "Black"
    public OrderPage choiceColourScooter(String colour) {
        driver.findElement(By.id(colour)).click();
        return this;
    }
    // Метод для ввода данных в поле комментарий
    public OrderPage inputCommentForCourier(String comment){
        driver.findElement(inputCommentOrder).click();
        driver.findElement(inputCommentOrder).sendKeys(comment);
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div[5]/div[1]/div")));
        String orderNumber = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[5]/div[1]/div")).getText();
        System.out.println(orderNumber);
        return this;
    }
    // Кликер кнопки "Посмотреть"
    public OrderPage clickViewInfoAboutOrderButton(){
        driver.findElement(viewInfoAboutOrder).click();
        return this;
    }
    // Проверка, что поле для ввода номер заказа заполнено
    public boolean checkNumberOrder(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement orderNumber =
                wait.until(ExpectedConditions.visibilityOfElementLocated(inputNumberOrder));
        return orderNumber.isDisplayed();
    }
    // Заполнение данных на первом экране страницы заказа, клик по кнопке "Далее"
    public OrderPage inputOrderValue(String firstName, String secondName, String deliveryAddress, String nameMetroStation, String phoneNumber){
        enterFirstName(firstName); // Заполняем поле "Имя"
        enterSecondName(secondName); // Заполняем поле "Фамилия"
        enterDlvrAddress(deliveryAddress); // Заполняем поле "Адрес"
        inputMetroStation(nameMetroStation); // Заполняем поле "Станция метро"
        inputPhnNumb(phoneNumber); // Ввод номера телефона
        clickNextButton(); // Клик по "Далее"
        return this;
    }
    // Заполнение данных на втором экране страницы заказа, клик по кнопке "Заказать" и "Да"
    public OrderPage inputOrderValueAfterNextButton(String rentDay) {
        choiceDeliveryNextDay(); // Выбор даты доставки
        choiceRentTime(rentDay); // Выбор срока аренды
        clickConfirmOrderButton(); // Клик по "Заказать"
        clickYesButton(); // Клик по "Да"
        return this;
    }






}

