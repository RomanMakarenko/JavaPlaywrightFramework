package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MyBookingsPage {
    Page page;
    public static final String BOOKING_CARDS = "booking-card";

    public MyBookingsPage(Page page) {
        this.page = page;
    }

    public void checkLastBookingVisible(String lastBookRef) {
        Locator bookingCards = page.getByTestId(BOOKING_CARDS);
        Locator targetBookingCard = bookingCards.filter(new Locator.FilterOptions().setHasText(lastBookRef));
        assertThat(targetBookingCard).isVisible();
    }
}
