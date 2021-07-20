package pages;


import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SearchPage extends BasePage {

    public static final String URL = "https://www.booking.com/searchresults.en-gb.html";

    public static final By LOCATION_ID = By.id("ss");
    public static final By SEARCH_BUTTON = By.cssSelector(".sb-searchbox__button");

    public SearchPage openPage() {
        open(URL);
        return this;
    }

    public SearchPage enterLocation(String location) {
        $(LOCATION_ID).sendKeys(location);
        return this;
    }

    public SearchPage clickOnSearchButton() {
        Configuration.clickViaJs = true;
        $(SEARCH_BUTTON).click();
        Configuration.clickViaJs = false;
        return this;
    }


    public SearchPage isPageOpened() {
        isElementVisible(SEARCH_BUTTON);
        return this;
    }
}
