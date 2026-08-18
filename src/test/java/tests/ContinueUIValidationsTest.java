package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class ContinueUIValidationsTest {
    Browser browser;
    Playwright playwright;
    Page page;
    BrowserContext context;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        page.navigate("https://rahulshettyacademy.com/AutomationPractice/");
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void hideTest() {
        Locator hideExampleRow = page.getByPlaceholder("Hide/Show Example");
        Assert.assertTrue(hideExampleRow.isVisible());
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Hide")).click();
        Assert.assertTrue(hideExampleRow.isHidden());
    }

    @Test
    public void alertTest() {
        //add alert/dialog listener
        page.onDialog(Dialog::accept);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Alert")).click();
        page.pause();
    }

    @Test
    public void mouseHover() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Mouse Hover")).hover();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Top")).click();
        page.pause();
    }

    @Test
    public void iFrame() {
        FrameLocator framePage = page.frameLocator("#courses-iframe");
        framePage.getByRole(AriaRole.LINK, new FrameLocator.GetByRoleOptions().setName("Learning paths")).click();
        page.pause();
    }

    @Test
    public void screenShootTest() {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screen1.png")));
        Locator hideExampleRow = page.getByPlaceholder("Hide/Show Example");
        hideExampleRow.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("screen2.png")));
    }
}
