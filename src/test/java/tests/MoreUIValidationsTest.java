package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class MoreUIValidationsTest {
    Browser browser;
    Playwright playwright;
    Page pageA;
    BrowserContext context;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        pageA = context.newPage();
        pageA.navigate("https://rahulshettyacademy.com/loginpagePractise/");
    }

    @AfterMethod
    public void tearDown() {
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));
    }

    @Test
    public void childWindowHandle() {
        Locator blinkingText = pageA.locator(".blinkingText");
        Page newPage = context.waitForPage(() -> blinkingText.first().click());
        newPage.waitForLoadState();
        String newPageText = newPage.locator(".red").textContent();
        String email = newPageText.split(" ")[4];
        System.out.println(email);
        pageA.getByLabel("Username:").fill(email);
//        pageA.getByLabel("Password:").fill("Learning@830$3mK2");
 //       pageA.pause();
    }

    @Test(groups = {"smoke", "regression"})
    public void uiControls() {
        Locator userBtn = pageA.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("User"));
        userBtn.click();
        pageA.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Okay")).click();
        Assert.assertTrue(userBtn.isChecked());
        Locator checkbox = pageA.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("I Agree to the terms and conditions"));
        checkbox.check();
        Assert.assertTrue(checkbox.isChecked());
        pageA.getByRole(AriaRole.COMBOBOX).selectOption("Teacher");
        pageA.pause();
    }
}
