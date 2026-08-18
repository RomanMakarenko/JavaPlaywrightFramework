package tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.LocatorAssertions;
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

    @Test(groups = {"regression"}, dataProvider = "eventBookingData")
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
        BookingConfirmPage bookingDetailsPage = bookingFormPage.fillAndConfirm(data.get("bookingName"), data.get("bookingEmail"), data.get("bookingPhone"));
        String bookRef = bookingDetailsPage.getBookingReference();
        System.out.println(bookRef);
        //Open my bookings
        MyBookingsPage myBookingsPage = bookingDetailsPage.openMyBookings();
        //Verify in booking system
        myBookingsPage.checkLastBookingVisible(bookRef);
        eventsPage.goTo();
        page.waitForTimeout(10000);
        Locator targetEventCardAfterBooking = eventsPage.findEventCard(data.get("title"));
        int seatsNumAfterBooking = eventsPage.getSeatsCount(targetEventCardAfterBooking);
        System.out.println(seatsNumAfterBooking);
        Assert.assertEquals(seatsNumBeforeBooking - seatsNumAfterBooking, 1);
    }
}
