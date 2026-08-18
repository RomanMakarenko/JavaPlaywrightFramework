package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class BookingConfirmPage {
    Page page;

    public static final String BOOKING_REFERENCE_SELECTOR = "span[class^=\"booking-ref\"]";

    public BookingConfirmPage(Page page) {
        this.page = page;
    }

    public String getBookingReference() {
        return page.locator(BOOKING_REFERENCE_SELECTOR).innerText();
    }

    public MyBookingsPage openMyBookings() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("View My Bookings")).click();
        return new MyBookingsPage(page);
    }
}
