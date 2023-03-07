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

    public void enterValuesInCalculationFieldUsingKeyboard(String exp){
        actions().moveToElement(calculation_field).click()
                .sendKeys(Keys.CLEAR)
                .sendKeys(exp)
                .build().perform();
    }

    public void clickEnterButtonOnKeyboard(){
        actions().sendKeys(Keys.ENTER).build().perform();
    }

    public void calculateArithmeticExpressionUsingKeyboard(String exp){
        enterValuesInCalculationFieldUsingKeyboard(exp);
        clickEnterButtonOnKeyboard();
    }

    public void clickButtonOnWebCalculator(String buttonValue){
        if(!buttonValue.equals("")){
            $x("//div[text()=\""+buttonValue+"\"]").click();
        }
    }

    public void calculateArithmeticExpressionUsingWebCalculator(String arithmeticExpression){
        char[] calcValues = arithmeticExpression.toCharArray();
            for(char s: calcValues){
                clickButtonOnWebCalculator(String.valueOf(s));
        }
        clickButtonOnWebCalculator("=");
    }

    public void calculateTrigonometricFunctionUsingWebCalculator(String function, String number){
        clickButtonOnWebCalculator(function);
        calculateArithmeticExpressionUsingWebCalculator(number);
    }

    public void clickCalculationHistoryButton(){
        calculation_history_button.should(exist);
        calculation_history_button.click();
    }

    public String getLastArithmeticExampleFromHistory(){
        List<String> calculationValues = lastCalculationInHistory.texts();
        String value = String.join("", calculationValues);
        System.out.println("Calculation expression from history; " + value);
        return value;
    }

    public void clickOnLastArithmeticExampleInHistoryWindow(){
        lastCalculationInHistory.get(0).click();
        calculation_history_window.should(disappear);
    }

    public String getPreviousCalculationOnMainWindow(){
        return previous_calculations.getText();
    }
}
