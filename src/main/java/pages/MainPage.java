package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class MainPage {

    public Locator firstOfferCard, cookieButton, nextCarouselButton;

    public MainPage(Page page) {
        firstOfferCard =  page.locator("div.components-group > app-cta-section").first();
        cookieButton = page.locator(".tbcx-pw-cookie-consent__actions button").first();
        nextCarouselButton = page.locator(".tbcx-pw-hero-slider-section__button").first();

    }
}
