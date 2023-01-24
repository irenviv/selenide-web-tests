package com.google;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static com.codeborne.selenide.Selenide.*;

@Tag("UI")
public class GoogleCalculator {

    private static final String BASE_URL = "https://google.com";

    @BeforeAll
    public static void beforeSuite(){
        Configuration.headless = false;
    }

    @BeforeEach
    public void beforeTest(){
        clearBrowserCookies();
        Selenide.open(BASE_URL);
    }

    @AfterAll
    public static void tearDown(){
        Selenide.closeWebDriver();
    }

    private void assertValuesResult(String calcValues, int result){
        $x("//input[@name='q']").setValue(calcValues).pressEnter();
        String actualResult = $("span#cwos").getText();
        Assertions.assertEquals(result, Integer.parseInt(actualResult));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "10+5, 15",
            "10-5, 5",
            "10*5, 50",
            "10/5, 2"
    })
    public void calculateValuesTest(String calcValues, int result){
        assertValuesResult(calcValues, result);
    }
}
