package utils;

import com.microsoft.playwright.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.awt.*;


public class PlaywrightSetup {
    protected Playwright playwright;
    public Browser browser;
    public Page page;
    public BrowserContext context;
    protected Boolean headlessMode = false;


    @BeforeClass
    public void asetUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setSlowMo(400)
                        .setArgs(java.util.List.of(
                                "--window-position=0,0",
                                "--incognito"
                        ))
        );
        context = browser.newContext();
        page = context.newPage();
        page.setViewportSize(1920, 1080);
    }

    @AfterClass
    public void atearDown() {
        if (page != null) {
            page.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }

    }

}
