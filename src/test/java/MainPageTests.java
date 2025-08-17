import com.microsoft.playwright.Locator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.MainPageSteps;
import utils.PlaywrightSetup;

import java.nio.file.Paths;

public class MainPageTests extends PlaywrightSetup {
    MainPageSteps mainPageSteps;

    @BeforeClass
    void before() {
        mainPageSteps = new MainPageSteps(page);
    }


    @Test
    void checkTbcCard1() {
        page.locator("div.components-group > app-cta-section")
                .screenshot(new Locator.ScreenshotOptions()
                        .setPath(Paths.get("src/main/resources/cta-section-screenshot.png")));
    }
}
