package com.google.cloud.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudHomePage {

    private final static String HOME_PAGE = "https://cloud.google.com";
    private WebDriver driver;

    @FindBy(xpath = "//input[@class = 'devsite-search-field devsite-search-query']")
    private WebElement searchArea;

    public GoogleCloudHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GoogleCloudHomePage openPage() {
        driver.get(HOME_PAGE);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(searchArea));
        return this;
    }

    public SearchResultPage searchForTerms(String term) {

        searchArea.click();
        searchArea.sendKeys(term);
        searchArea.sendKeys(Keys.ENTER);

        return new SearchResultPage(driver, term);

    }

}
