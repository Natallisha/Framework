package com.google.cloud.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class CalculationResultPage extends AbstractPage {

    private String address;

    @FindBy(id = "resultBlock")
    private WebElement resultBlock;

    @FindBy(xpath = "//button[@id = 'email_quote']")
    private WebElement emailEstimateButtonLocator;

    @FindBy(xpath = "//iframe[contains(@name, 'goog_')]")
    private WebElement firstLevelIframe;

    @FindBy(id = "myFrame")
    private WebElement secondLevelIframe;

    @FindBy(xpath = "//button[@aria-label = 'Send Email']")
    private WebElement sendMailButton;

    @FindBy(xpath = "//button[@onclick = 'egengo();']")
    private WebElement generateEmailButton;

    private final By resultCostLineLocator = By.xpath("//md-card-content[@id = 'resultBlock']//md-card-content/div/div/div/h2/b[@class = 'ng-binding']");
    private final By emailFieldLocator = By.xpath("//input[@ng-model = 'emailQuote.user.email']");

    public CalculationResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public String getTotalCost() {

        String totalCost = null;

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(resultBlock));
        String resultCostLine = driver
                .findElement(resultCostLineLocator).getText();

        String[] arr = resultCostLine.split("\\s");

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("USD")) {
                totalCost = arr[i + 1];
            }
        }
        return totalCost;
    }

    public CalculationResultPage pressEmailEstimateButton() {

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(emailEstimateButtonLocator)).click();

        return this;
    }

    public YopmailHomePage openNewTabAndSwitchToYopmail() {
        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_T);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_T);
        r.delay(2000);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get("https://yopmail.com/");

        return new YopmailHomePage(driver);

    }

    public CalculationResultPage fillEmailAndSendMail() {

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(firstLevelIframe));
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(secondLevelIframe));
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(emailFieldLocator))
                .sendKeys(Keys.CONTROL + "v");
        address = driver.findElement(emailFieldLocator).getText();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(sendMailButton))
                .click();

        return this;

    }

    public YopmailPostBoxPage switchToPostBox() {

        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(generateEmailButton))
                .click();

        return new YopmailPostBoxPage(driver);
    }

}



