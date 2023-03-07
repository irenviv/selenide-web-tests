package com.google.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class GoogleResultsPage {

    private final SelenideElement calculation_field = $("#cwos");
    private final SelenideElement calculation_result_undefined_value = $(".card-section .dDoNo");
    private final SelenideElement calculation_history_button = $x("//span[contains(@class, 'XDTKBd')]");
    private final SelenideElement calculation_history_window = $("div.tR3EBc");
    private final ElementsCollection lastCalculationInHistory = $$x("//div[@class='CpV0xd']/table[last()]/td");
    private final SelenideElement previous_calculations = $("span.vUGUtc");

    public String getCalculationResult(){
        calculation_field.should(exist);
        return calculation_field.getText();
    }

    public String getCalculationResultZeroDivision(){
        return calculation_result_undefined_value.getText();
    }

    public void enterArithmeticExpressionUsingKeyboardButtons(String exp){
        actions().moveToElement(calculation_field).click()
                .sendKeys(Keys.CLEAR)
                .sendKeys(exp)
                .build().perform();
    }

    public void clickEnterOnKeyboard(){
        actions().sendKeys(Keys.ENTER).build().perform();
    }

    public void calculateArithmeticExpressionUsingKeyboard(String exp){
        enterArithmeticExpressionUsingKeyboardButtons(exp);
        clickEnterOnKeyboard();
    }

    public void clickButtonOnWebCalculator(String buttonValue){
        if(!buttonValue.equals("")){
            $x("//div[text()=\""+buttonValue+"\"]").click();
        }
    }

    public void calculateValuesUsingWebCalculator(String arithmeticExpression){
        char[] calcValues = arithmeticExpression.toCharArray();
            for(char s: calcValues){
                clickButtonOnWebCalculator(String.valueOf(s));
        }
        clickButtonOnWebCalculator("=");
    }

    public void calculateTrigonometricFunctionUsingWebCalculator(String function, String number){
        clickButtonOnWebCalculator(function);
        calculateValuesUsingWebCalculator(number);
    }

    public void clickCalculationHistoryButton(){
        calculation_history_button.should(exist);
        calculation_history_button.click();
    }

    public String getLastArithmeticExampleFromHistory(){
        List<String> values = lastCalculationInHistory.texts();
        String value = String.join("", values);
        System.out.println("Calculation from history; " + value);
        return value;
    }

    public void clickOnLastArithmeticExample(){
        lastCalculationInHistory.get(0).click();
        calculation_history_window.should(disappear);
    }

    public String getPreviousCalculationOnMainWindow(){
        String value = previous_calculations.getText();
        return value;
    }
}
