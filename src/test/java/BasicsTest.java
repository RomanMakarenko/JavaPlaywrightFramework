import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BasicsTest {
    Page page;
    Browser browser;
    Playwright playwright;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        //Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        page = browser.newPage();
        page.setDefaultTimeout(8000);
        page.navigate("https://eventhub.rahulshettyacademy.com/login");
        PlaywrightAssertions.setDefaultAssertionTimeout(7000);
    }

    @Test
    public void demoTest() {
        System.out.println(page.title());
        //Log in
        assertThat(page).hasTitle("EventHub — Discover & Book Events");
        Assert.assertEquals("EventHub — Discover & Book Events", page.title());
        page.getByLabel("Email").fill("romantest@test.com");
        page.getByLabel("Password").fill("qw123123_QW");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();
        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Browse Events →"))).isVisible();
        //Create new event
        page.navigate("https://eventhub.rahulshettyacademy.com/admin/events");
        page.locator("input[id=\"event-title-input\"]").fill("Hello world");
        page.getByPlaceholder("Describe the event…").fill("Funtime");
        page.getByLabel("Category").selectOption("Festival");
        page.getByLabel("City").fill("Kyiv");
        page.getByLabel("Venue").fill("Odessca streat");
        page.getByLabel("Price ($)").fill("15");
        page.getByLabel("Total Seats").fill("300");
        page.getByLabel("Event Date & Time").fill("2026-08-14T11:11");
        page.getByText("+ Add Event").click();
        assertThat(page.getByText("Event created!")).isVisible();
        //Find new event
        page.locator("#nav-events").click(new Locator.ClickOptions().setTimeout(5000));
        Locator eventCards = page.getByTestId("event-card");
        eventCards.first().waitFor();
        System.out.println(eventCards.count());
        Locator targetEventCard = eventCards.filter(new Locator.FilterOptions().setHasText("Hello world"));
        assertThat(targetEventCard).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(10000));
        String seatsText = targetEventCard.getByText("seats").innerText();
        int seatsNumBeforeBooking = Integer.parseInt(seatsText.split(" ")[0]);
        System.out.println(seatsText);
        targetEventCard.getByTestId("book-now-btn").click();
        //Book
        page.getByPlaceholder("Your full name").fill("Roman");
        page.getByPlaceholder("you@email.com").fill("romantest@test.com");
        page.getByPlaceholder("+91 98765 43210").fill("1111111111");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Confirm Booking")).click();
        //page.getByText("Confirm Booking").click();
        assertThat(page.getByText("Your tickets are reserved.")).isVisible();
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
