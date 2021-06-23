package steps;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SearchPage;
import pages.SearchResultsPage;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class BookingSteps {

    private String location;
    private String hotelName;

    private SearchPage searchPage;
    private SearchResultsPage searchResultsPage;

    @Before
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 7000;
        Configuration.headless = true;
        Configuration.holdBrowserOpen = true;
        Configuration.clickViaJs = false;
        searchPage = new SearchPage();
        searchResultsPage = new SearchResultsPage();
    }

    @After
    public void closeBrowser() {
        getWebDriver().quit();
    }

    @Given("User is looking for hotels like {string}")
    public void userIsLookingForHotelsLike(String location) {
        this.location = location;
    }

    @When("User does search")
    public void userDoesSearch() {
        searchPage
                .openPage()
                .isPageOpened()
                .enterLocation(location)
                .clickOnSearchButton();
    }

    @Then("Hotel {string} should be on the first page")
    public void hotelShouldBeOnTheFirstPage(String expectedHotelName) {
        hotelName = expectedHotelName;
        assertThat(searchResultsPage.getHotelNames(), hasItem(expectedHotelName));
    }

    @And("Rating of the hotel is {string}")
    public void ratingOfTheHotelIs(String expectedRating) {
        String rating = searchResultsPage.getRating(hotelName);
        assertThat(rating, is(expectedRating));
    }
}
