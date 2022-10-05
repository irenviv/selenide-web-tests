package com.rozetka;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartModalWindowPage {

    public void checkProductsQuantityInCart(int addedProducts){
        $("rz-shopping-cart").should(Condition.appear, Duration.ofSeconds(10));
            $$("ul.cart-list > li")
                    .shouldHave(CollectionCondition.size(addedProducts));
    }

    public String getAddedProductTitle(){
        $("rz-shopping-cart").should(Condition.appear, Duration.ofSeconds(10));
        return $("ul.cart-list > li a.cart-product__title").getAttribute("title");

    }


}
