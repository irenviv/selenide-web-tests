package com.google.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import static com.codeborne.selenide.Selenide.clearBrowserCookies;

public abstract class BaseTest {

     private static final String BASE_URL = "https://google.com";
     public static final String SEARCHED_VALUE = "calculator";

    @BeforeAll
    public static void setUp() {
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
        Configuration.headless = false;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        clearBrowserCookies();
        Selenide.open(BASE_URL);
    }

    @AfterAll
    public static void tearDown(){
        Selenide.closeWebDriver();
    }

}
