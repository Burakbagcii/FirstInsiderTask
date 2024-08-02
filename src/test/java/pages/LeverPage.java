package pages;

import org.openqa.selenium.By;
import utils.Operation;

public class LeverPage {

    private static final By headerPositionText = By.xpath("//*[@class = 'posting-headline']/h2");
    private static final By applyButton = By.xpath("//*[@class = 'postings-btn-wrapper']");

    public LeverPage() {
        Operation.switchToNewTab();
    }
    public boolean isHeaderPositionTextExisted() {
        return Operation.findElement(headerPositionText).isDisplayed();
    }

    public boolean isApplyButtonExisted() {
        return Operation.findElement(applyButton).isDisplayed();
    }
}
