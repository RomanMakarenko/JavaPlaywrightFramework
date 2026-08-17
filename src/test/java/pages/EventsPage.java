package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class EventsPage {
    Page page;

    public EventsPage(Page page) {
        this.page = page;
    }

    public void goTo() {
        page.locator("#nav-events").click(new Locator.ClickOptions().setTimeout(5000));
    }

    public Locator waitForEventToLoad() {
        Locator eventCards = page.getByTestId("event-card");
        eventCards.first().waitFor();
        return eventCards;
    }

    public Locator findEventCard(String cardTitle) {
        Locator eventCards = waitForEventToLoad();
        Locator targetEventCard = eventCards.filter(new Locator.FilterOptions().setHasText(cardTitle));
        assertThat(targetEventCard).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(10000));
        return targetEventCard;
    }

    public int getSeatsCount(Locator targetEventCard) {
        String seatsText = targetEventCard.getByText("seats").innerText();
        return Integer.parseInt(seatsText.split(" ")[0]);
    }

    public BookingFormPage proceedToBookingCardEvent(Locator targetEventCard) {
        targetEventCard.getByTestId("book-now-btn").click();
        return new BookingFormPage(page);
    }
}
