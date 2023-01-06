package com.rozetka.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartModalWindowPage {

    private final String shoppingCart_locator = "rz-shopping-cart";

    public void checkProductsQuantityInCart(int addedProducts){
        $(shoppingCart_locator).should(Condition.appear, Duration.ofSeconds(10));
            $$("ul.cart-list > li")
                    .shouldHave(CollectionCondition.size(addedProducts));
    }

    public String getAddedProductTitle(){
        $(shoppingCart_locator).should(Condition.appear, Duration.ofSeconds(10));
        return $("ul.cart-list > li a.cart-product__title").getAttribute("title");

    }


}
