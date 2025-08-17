package steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.screenshot.ComparisonOptions;
import com.screenshot.ComparisonResult;
import com.screenshot.PlaywrightScreenshotCompare;
import org.testng.Assert;
import pages.MainPage;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

public class MainPageSteps {
    MainPage mainPage;
    Page page;

    public MainPageSteps(Page page) {
        this.page = page;
        this.mainPage = new MainPage(page);
    }

    public MainPageSteps openTbcSite() {
        page.navigate("https://tbcbank.ge/ka");
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        page.waitForTimeout(6000);
        return this;
    }

    public MainPageSteps acceptCookie() {
        mainPage.cookieButton.click(new Locator.ClickOptions().setDelay(5000));
        return this;
    }

    public MainPageSteps nextCarousel() {
        mainPage.nextCarouselButton.click();
        page.waitForTimeout(4500);
        return this;
    }

    public void checkCardUsingVisualTestingPass() throws IOException {
        String actualPath = "images/mainPageImagesActual/cta-section-screenshot_pass.png";
        String expectedPath = "images/mainPageImagesExpected/cta-section-screenshot_pass.png";
        mainPage.firstOfferCard.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(actualPath)));
        ComparisonResult comparisonResult = PlaywrightScreenshotCompare.compare(actualPath, expectedPath);
        System.out.println(comparisonResult);
        Assert.assertTrue(comparisonResult.isMatches());
    }

    public void checkCardUsingVisualTestingFail() throws IOException {
        String actualPath = "images/mainPageImagesActual/cta-section-screenshot_fail.png";
        String expectedPath = "images/mainPageImagesExpected/cta-section-screenshot_fail.png";
        mainPage.firstOfferCard.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(actualPath)));
        ComparisonResult comparisonResult = PlaywrightScreenshotCompare.compareIgnoringDimensions(actualPath, expectedPath);
        System.out.println(comparisonResult);
        Assert.assertTrue(comparisonResult.isMatches());
    }


    public void checkCardUsingVisualTestingRegionIgnore() throws IOException {
        String actualPath = "images/mainPageImagesActual/cta-section-screenshot_ignore.png";
        String expectedPath = "images/mainPageImagesExpected/cta-section-screenshot_ignore.png";
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(actualPath)));
        ComparisonResult comparisonResult = PlaywrightScreenshotCompare.compareIgnoringTransparency(actualPath, expectedPath);
        System.out.println(comparisonResult);
        Assert.assertTrue(comparisonResult.isMatches());
    }
}
