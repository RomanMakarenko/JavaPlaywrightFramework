import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MockWebTest {
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

    @Test(description = "sendbox banner is shown when 6 or more events")
    public void demoTest() {
        System.out.println(page.title());
        //Log in
        assertThat(page).hasTitle("EventHub — Discover & Book Events");
        Assert.assertEquals("EventHub — Discover & Book Events", page.title());
        page.getByLabel("Email").fill("romantest@test.com");
        page.getByLabel("Password").fill("qw123123_QW");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();
        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Browse Events →"))).isVisible();
        page.route("**api/events**", route -> route.fulfill(
                new Route.FulfillOptions().setPath(Paths.get("src/main/resources/events6.json"))));
        page.navigate("https://eventhub.rahulshettyacademy.com/events");
        Locator moreThan5EventsNotification = page.getByText("Your sandbox holds up to ");
        Locator eventCards = page.getByTestId("event-card");
        eventCards.first().waitFor();
        Assert.assertTrue(moreThan5EventsNotification.isVisible());
        page.route("**api/events**", route -> route.fulfill(
                new Route.FulfillOptions().setPath(Paths.get("src/main/resources/events4.json"))));
        page.navigate("https://eventhub.rahulshettyacademy.com/events");
        eventCards.first().waitFor();
        Assert.assertFalse(moreThan5EventsNotification.isVisible());

        page.getByTestId("nav-bookings").click();

        page.route("**api/bookings**", route -> route.resume(
                new Route.ResumeOptions().setUrl("https://api.eventhub.rahulshettyacademy.com/api/bookings/118941")
        ));
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("View Details")).first().click();
        assertThat(page.getByText("Access Denied"));
        page.pause();
    }
}
