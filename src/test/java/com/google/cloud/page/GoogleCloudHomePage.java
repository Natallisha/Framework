package com.google.cloud.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudHomePage extends AbstractPage {

    private final static String HOME_PAGE = "https://cloud.google.com";

    @FindBy(xpath = "//input[@class = 'devsite-search-field devsite-search-query']")
    private WebElement searchArea;

    public GoogleCloudHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public GoogleCloudHomePage openPage() {
        driver.get(HOME_PAGE);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
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
