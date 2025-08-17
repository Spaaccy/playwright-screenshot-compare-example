import com.microsoft.playwright.Locator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.MainPageSteps;
import utils.PlaywrightSetup;

import java.io.IOException;
import java.nio.file.Paths;

public class MainPageTests extends PlaywrightSetup {
    MainPageSteps mainPageSteps;

    @BeforeClass
    void before() {
        mainPageSteps = new MainPageSteps(page);
    }


    @Test(priority = 1)
    void checkTbcCard1Pass() throws IOException {
        mainPageSteps
                .openTbcSite()
                .checkCardUsingVisualTestingPass();
    }

    @Test(priority = 2)
    void checkTbcCard2Fail() throws IOException {
        mainPageSteps
                .openTbcSite()
                .checkCardUsingVisualTestingFail();
    }

    @Test(priority = 2)
    void checkTbcCard3IgnoreRegion() throws IOException {
        mainPageSteps
                .openTbcSite()
                .nextCarousel()
                .checkCardUsingVisualTestingRegionIgnore();
    }
}
