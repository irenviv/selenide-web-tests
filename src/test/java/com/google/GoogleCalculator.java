package com.google;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
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


    @Test
    public void additionValuesTest(){
        assertValuesResult("10+5", 15);
    }

    @Test
    public void subtractionValuesTest(){
        assertValuesResult("10-5", 5);
    }

    @Test
    public void multiplicationValuesTest(){
        assertValuesResult("10*5", 50);
    }

    @Test
    public void divisionValuesTest(){
        assertValuesResult("10/5", 2);
    }
}
