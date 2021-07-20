package pages;

import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultsPage extends BasePage {

    public static final By HOTEL_NAME = By.cssSelector(".sr-hotel__name");
    public static final By AVAILABLE_PROPERTIES = By.cssSelector(".sr_header");

    public String ratingLocator = "//*[contains(text(),'%s')]/ancestor::*[contains(@class, 'sr_item_content')]" +
            "//*[@class='bui-review-score__badge']";

    public List<String> getHotelNames() {
        isPageOpened();
        return $$(HOTEL_NAME).texts();
    }

    public String getRating(String hotelName) {
        isPageOpened();
        return $(By.xpath(String.format(ratingLocator, hotelName))).getText().trim();
    }


    protected SearchResultsPage isPageOpened() {
        isElementVisible(AVAILABLE_PROPERTIES);
        return this;
    }
}
