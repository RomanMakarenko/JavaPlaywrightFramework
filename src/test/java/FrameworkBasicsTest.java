import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FrameworkBasicsTest extends BaseTest {


    @Test
    public void demoTest() {
        String newEventTitle = "Hello world";
        LoginPage loginPage = new LoginPage(page, baseURL);
        DashboardPage dashboardPage = loginPage.loginToApplication("romantest@test.com","qw123123_QW");

        dashboardPage.waitForEventsToLoad();
        //Create new event
        AdminEventPage adminEventPage = new AdminEventPage(page);
        adminEventPage.goTo();

        adminEventPage.createEvent(
                newEventTitle,
                "Funtime",
                "Festival",
                "Kyiv",
                "Odessca streat",
                "15",
                "300",
                "2026-08-30T11:11"
        );
        //Find new event
        EventsPage eventsPage = new EventsPage(page);
        eventsPage.goTo();
        Locator targetEventCard = eventsPage.findEventCard(newEventTitle);
        int seatsNumBeforeBooking = eventsPage.getSeatsCount(targetEventCard);

        System.out.println(seatsNumBeforeBooking);
        BookingFormPage bookingFormPage =  eventsPage.proceedToBookingCardEvent(targetEventCard);
        //Book
        bookingFormPage.fillAndConfirm("Roman", "romantest@test.com", "1111111111");
        //page.getByText("Confirm Booking").click();
        String bookRef = page.locator("span[class^=\"booking-ref\"]").innerText();
        System.out.println(bookRef);
        //Open my bookings
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("View My Bookings")).click();
        //Verify in booking system
        Locator bookingCards = page.getByTestId("booking-card");
        Locator targetBookingCard = bookingCards.filter(new Locator.FilterOptions().setHasText(bookRef));
        assertThat(targetBookingCard).isVisible();
        //
        page.locator("#nav-events").click();
        page.waitForTimeout(10000);
        Locator eventCardsAfterBooking = page.getByTestId("event-card");
        eventCardsAfterBooking.first().waitFor();
        System.out.println(eventCardsAfterBooking.count());
        Locator targetEventCardAfterBooking = eventCardsAfterBooking.filter(new Locator.FilterOptions().setHasText("Hello world"));
        assertThat(targetEventCardAfterBooking).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(10000));
        String seatsTextAfterBooking = targetEventCardAfterBooking.getByText("seats").innerText();
        System.out.println(seatsTextAfterBooking);
        int seatsNumAfterBooking = Integer.parseInt(seatsTextAfterBooking.split(" ")[0]);
        Assert.assertEquals(seatsNumBeforeBooking - seatsNumAfterBooking, 1);
    }
}
