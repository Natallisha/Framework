package com.google.cloud.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YopmailPostBoxPage {

    private WebDriver driver;

    public YopmailPostBoxPage(WebDriver driver) {
        this.driver = driver;
    }

    public YopmailPostBoxPage openMail() {

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .elementToBeClickable(By.id("refresh"))).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@name, 'ifinbox')]")));
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath("//div[@class = 'lms'][text() = 'Google Cloud Platform Price Estimate']/..")));
        driver.switchTo().window(driver.getWindowHandle());

        return this;
    }

    public String getTotalCostFromMail() {

        String totalCost = null;

        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@name = 'ifmail']")));
        String totalCostLine = new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath("//div[@id = 'mail']//h3[contains(text(), 'USD')]"))).getText();
        String[] arr = totalCostLine.split("\\s");

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("USD")) {
                totalCost = arr[i + 1];
            }
        }
        return totalCost;
    }
}
