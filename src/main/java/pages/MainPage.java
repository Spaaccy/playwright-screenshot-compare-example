package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class MainPage {

    public Locator firstOfferCard;

    public MainPage(Page page) {
        firstOfferCard = page.locator("div.components-group > app-cta-section");
    }
}
