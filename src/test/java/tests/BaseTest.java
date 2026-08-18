package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    Page page;
    Browser browser;
    Playwright playwright;
    String baseURL;

    @BeforeMethod
    public void setUp() {
        Properties properties = new Properties();
        playwright = Playwright.create();
        try {
            FileInputStream propertiesFile = new FileInputStream("src/main/resources/config.properties");
            properties.load(propertiesFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String browserName = properties.getProperty("browser");
        if ("firefox".equalsIgnoreCase(browserName)) {
            browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
        }  else if ("safari".equalsIgnoreCase(browserName)) {
            browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
        } else {
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        }

        //Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        page = browser.newPage();
        page.setDefaultTimeout(8000);
        baseURL = properties.getProperty("dev.baseURL");
    }
}
