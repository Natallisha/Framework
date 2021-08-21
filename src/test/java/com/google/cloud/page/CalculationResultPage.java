package com.google.cloud.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class CalculationResultPage {

    private WebDriver driver;

    private String address;

    public CalculationResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getValueOfItem(String itemName) {

        String value = null;
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("resultBlock")));
        List<WebElement> listOfResultItem = driver.findElements(By.xpath("//md-card-content[@id = 'resultBlock']//md-list-item/div"));
        for (WebElement item : listOfResultItem) {
            if (item.getText().contains(itemName)) {
                value = item.getText();
            }
        }
        return value;
    }

    public String getTotalCost() {

        String totalCost = null;

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("resultBlock")));
        String resultCostLine = driver
                .findElement(By.xpath("//md-card-content[@id = 'resultBlock']//md-card-content/div/div/div/h2/b[@class = 'ng-binding']")).getText();

        String[] arr = resultCostLine.split("\\s");

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("USD")) {
                totalCost = arr[i + 1];
            }
        }
        return totalCost;
    }

    public CalculationResultPage pressEmailEstimateButton() {

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id = 'email_quote']"))).click();

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
        r.delay(7000);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get("https://yopmail.com/");

        return new YopmailHomePage(driver);

    }

    public CalculationResultPage fillEmailAndSendMail(){

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@name, 'goog_')]")));
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("myFrame")));
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@ng-model = 'emailQuote.user.email']")))
                .sendKeys( Keys.CONTROL + "v");
        address = driver.findElement(By.xpath("//input[@ng-model = 'emailQuote.user.email']")).getText();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label = 'Send Email']")))
                .click();

        return this;

    }

    public YopmailPostBoxPage switchToPostBox(){

        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick = 'egengo();']")))
                .click();

        return new YopmailPostBoxPage(driver);
    }

}



