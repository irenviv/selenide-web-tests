package com.rozetka.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

    public final static String BASE_URL = "https://rozetka.com.ua/";

    @BeforeAll
    public static void setUp() {
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
//        Configuration.browserVersion = ("76");
        Configuration.headless = false;
//        SelenideLogger.addListener("AllureSelenide",
//                new AllureSelenide().screenshots(true).savePageSource(true));
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterAll
    public static void tearDown(){
        Selenide.closeWebDriver();
    }

}
