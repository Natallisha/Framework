package com.google.cloud.page;

import org.openqa.selenium.WebDriver;

public class AbstractPage {

    protected WebDriver driver;

    protected final int WAIT_TIMEOUT_SECONDS = 20;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

}
