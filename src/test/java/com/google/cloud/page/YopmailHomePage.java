package com.google.cloud.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class YopmailHomePage extends AbstractPage {

    @FindBy(xpath = "//a[@href = 'email-generator']")
    private WebElement generateRandomMailButton;

    @FindBy(id = "egen")
    private WebElement newRandomMail;

    @FindBy(id = "cprnd")
    private WebElement copyNewRandomMailButton;


    public YopmailHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CalculationResultPage getNewRandomMailCopyAndTurnBackToCalculating() {

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(generateRandomMailButton));
        generateRandomMailButton.click();
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(copyNewRandomMailButton));
        copyNewRandomMailButton.click();
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));

        return new CalculationResultPage(driver);
    }
}
