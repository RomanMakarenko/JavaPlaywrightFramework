package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BookingFormPage {
    Page page;

    private static final String NAME_PLACEHOLDER = "Your full name";
    private static final String EMAIL_PLACEHOLDER = "you@email.com";
    private static final String PHONE_PLACEHOLDER = "+91 98765 43210";

    public BookingFormPage(Page page) {
        this.page = page;
    }

    public BookingConfirmPage fillAndConfirm(
            String name,
            String email,
            String phoneNumber
    ) {
        page.getByPlaceholder(NAME_PLACEHOLDER).fill(name);
        page.getByPlaceholder(EMAIL_PLACEHOLDER).fill(email);
        page.getByPlaceholder(PHONE_PLACEHOLDER).fill(phoneNumber);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Confirm Booking")).click();
        assertThat(page.getByText("Your tickets are reserved.")).isVisible();
        return new BookingConfirmPage(page);
    }
}
