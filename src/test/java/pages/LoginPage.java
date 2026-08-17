package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {
    Page page;
    String baseURL;
    private static final String EMAIL_LABEL = "Email";
    private static final String PASSWORD_LABEL = "Password";

    public LoginPage(Page page, String baseURL) {
        this.page = page;
        this.baseURL = baseURL;
    }

    public DashboardPage loginToApplication(String email, String password) {
        page.navigate(baseURL);
        PlaywrightAssertions.setDefaultAssertionTimeout(7000);
        System.out.println(page.title());
        //Log in
        assertThat(page).hasTitle("EventHub — Discover & Book Events");
        Assert.assertEquals("EventHub — Discover & Book Events", page.title());
        page.getByLabel(EMAIL_LABEL).fill(email);
        page.getByLabel(PASSWORD_LABEL).fill(password);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();
        return new DashboardPage(page);
    }
}
