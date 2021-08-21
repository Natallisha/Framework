package com.google.cloud.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchResultPage {

    private WebDriver driver;
    private String searchTerm;
    private List<WebElement> searchResultsList;

    public SearchResultPage(WebDriver driver, String searchTerm) {
        this.driver = driver;
        this.searchTerm = searchTerm;
        PageFactory.initElements(driver, this);
    }

    public PlatformPricingCalculatorPage chooseTheResultWeNeed() {
        new WebDriverWait(driver, 100)
                .until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.xpath("//a[@class = 'gs-title']")));
        searchResultsList = driver.findElements(By.xpath("//a[@class='gs-title']"));
        for (WebElement search : searchResultsList) {
            if (search.getText().equals(searchTerm)) {
                search.click();
                break;
            }
        }
        return new PlatformPricingCalculatorPage(driver);
    }

}
