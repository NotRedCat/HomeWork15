package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class MainPage {
    private final static String TITLE_TEXT = "Актуальные предложения";



    public MainPage openMainPage() {
        open("https://www.dns-shop.ru");
        $(".homepage-actual-offers-main__title").shouldHave(text(TITLE_TEXT));
        executeJavaScript("$('.v-confirm-city').remove()");
        executeJavaScript("$('#fixedban').remove()");
        return this;
    }
    public MainPage clickWishlist() {
        $(".wishlist-link__lbl").click();
        return this;
}


}
