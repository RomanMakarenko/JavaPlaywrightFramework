package tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utils.DataProviderUtil;

import java.io.IOException;
import java.util.HashMap;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FrameworkBasicsDataProviderTest extends BaseTest {


    @DataProvider(name = "eventBookingData")
    public Object[][] eventBookingData() throws IOException {
        return DataProviderUtil.getJSONDataToMap("src/main/resources/eventBookingData.json");
    }

    @Test(dataProvider = "eventBookingData")
    public void demoTest(HashMap<String,String> data) {
        LoginPage loginPage = new LoginPage(page, baseURL);
        DashboardPage dashboardPage = loginPage.loginToApplication("romantest@test.com","qw123123_QW");

        dashboardPage.waitForEventsToLoad();
        //Create new event
        AdminEventPage adminEventPage = new AdminEventPage(page);
        adminEventPage.goTo();

        adminEventPage.createEvent(
                data.get("title"),
                data.get("description"),
                data.get("category"),
                data.get("city"),
                data.get("venue"),
                data.get("price"),
                data.get("seatsCount"),
                data.get("dateTime")
        );
        //Find new event
        EventsPage eventsPage = new EventsPage(page);
        eventsPage.goTo();
        Locator targetEventCard = eventsPage.findEventCard(data.get("title"));
        int seatsNumBeforeBooking = eventsPage.getSeatsCount(targetEventCard);

        System.out.println(seatsNumBeforeBooking);
        BookingFormPage bookingFormPage =  eventsPage.proceedToBookingCardEvent(targetEventCard);
        //Book
        bookingFormPage.fillAndConfirm(data.get("bookingName"), data.get("bookingEmail"), data.get("bookingPhone"));
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
        Locator targetEventCardAfterBooking = eventCardsAfterBooking.filter(new Locator.FilterOptions().setHasText(data.get("title")));
        assertThat(targetEventCardAfterBooking).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(10000));
        String seatsTextAfterBooking = targetEventCardAfterBooking.getByText("seats").innerText();
        System.out.println(seatsTextAfterBooking);
        page.pause();
        int seatsNumAfterBooking = Integer.parseInt(seatsTextAfterBooking.split(" ")[0]);
        Assert.assertEquals(seatsNumBeforeBooking - seatsNumAfterBooking, 1);
    }
}
