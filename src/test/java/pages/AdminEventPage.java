package pages;

import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AdminEventPage {
    Page page;
    private static final String TITLE_LOCATOR = "input[id=\"event-title-input\"]";
    private static final String DESCRIPTION_PLACEHOLDER = "Describe the event…";
    private static final String CATEGORY_LABEL = "Category";
    private static final String CITY_LABEL = "City";
    private static final String VENU_LABEL = "Venue";
    private static final String PRICE_LABEL = "Price ($)";
    private static final String SEATS_COUNT_LABEL = "Total Seats";
    private static final String DATE_TIME_LABEL ="Event Date & Time";
    private static final String ADD_EVENT_BTN_TEXT = "+ Add Event";
    private static final String SUCCESS_MESSAGE = "Event created!";

    public AdminEventPage(Page page) {
        this.page = page;
    }

    public void goTo() {
        page.navigate("https://eventhub.rahulshettyacademy.com/admin/events");
    }

    public void createEvent(
            String title,
            String description,
            String category,
            String city,
            String venue,
            String price,
            String seatsCount,
            String dateTime) {
        page.locator(TITLE_LOCATOR).fill(title);
        page.getByPlaceholder(DESCRIPTION_PLACEHOLDER).fill(description);
        page.getByLabel(CATEGORY_LABEL).selectOption(category);
        page.getByLabel(CITY_LABEL).fill(city);
        page.getByLabel(VENU_LABEL).fill(venue);
        page.getByLabel(PRICE_LABEL).fill(price);
        page.getByLabel(SEATS_COUNT_LABEL).fill(seatsCount);
        page.getByLabel(DATE_TIME_LABEL).fill(dateTime);
        page.getByText(ADD_EVENT_BTN_TEXT).click();
        assertThat(page.getByText(SUCCESS_MESSAGE)).isVisible();
    }
}
