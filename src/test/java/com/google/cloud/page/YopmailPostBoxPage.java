package com.google.cloud.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YopmailPostBoxPage extends AbstractPage {

    @FindBy(id = "refresh")
    private WebElement refreshButton;

    @FindBy(xpath = "//iframe[contains(@name, 'ifinbox')]")
    private WebElement iframeForPostbox;

    @FindBy(xpath = "//div[@class = 'lms'][text() = 'Google Cloud Platform Price Estimate']/..")
    private WebElement letterFromGoogleCloud;

    @FindBy(xpath = "//iframe[@name = 'ifmail']")
    private WebElement iframeForMail;

    @FindBy(xpath = "//div[@id = 'mail']//h3[contains(text(), 'USD')]")
    private WebElement totalCostText;

    public YopmailPostBoxPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public YopmailPostBoxPage openMail() {

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions
                        .elementToBeClickable(refreshButton)).click();
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeForPostbox));
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions
                        .visibilityOf(letterFromGoogleCloud));
        driver.switchTo().window(driver.getWindowHandle());

        return this;
    }

    public String getTotalCostFromMail() {

        String totalCost = null;

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeForMail));
        String totalCostLine = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions
                        .visibilityOf(totalCostText)).getText();
        String[] arr = totalCostLine.split("\\s");

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("USD")) {
                totalCost = arr[i + 1];
            }
        }
        return totalCost;
    }
}
