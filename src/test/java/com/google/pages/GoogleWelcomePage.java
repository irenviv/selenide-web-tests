package com.google.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class GoogleWelcomePage {

    private final SelenideElement input_field = $x("//input[@name='q']");

    public GoogleResultsPage enterSearchedValue(String searchValue){
        input_field.shouldBe(visible);
        input_field.setValue(searchValue).pressEnter();
        return new GoogleResultsPage();
    }

}
