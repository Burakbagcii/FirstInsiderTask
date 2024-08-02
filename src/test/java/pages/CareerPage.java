package pages;

import org.openqa.selenium.By;
import utils.Operation;
import utils.helpers.PropertyManager;

public class CareerPage {

    public static final By seeAllTeamsButton =By.xpath( "//section[@id='career-find-our-calling']//a[text()='See all teams']");
    public static final By categoryText = By.xpath("//div[@class='job-item col-12 col-lg-4 mt-5']//h3");
    public static final By seeAllJobsButton =By.xpath( "//section[@id = 'page-head']//a");
    public static final By locationsBlock = By.id("career-our-location");
    public static final By firstImageOfLocations = By.xpath("(//*[@class = 'location-figure text-center'])[1]");
    public static final By teamBlock = By.id("career-find-our-calling");
    public static final By firstImageOfTeamBlock = By.xpath("(//*[@class = 'job-image text-center'])[1]");
    public static final By lifeAtInsiderImageSliderBlock = By.xpath("//*[@class = 'elementor-main-swiper " +
            "swiper-container swiper-container-initialized swiper-container-horizontal']");


    public void openPositions(String category){
        Operation.scrollAndClick(Operation.findElement(seeAllTeamsButton));
        Operation.wait(1);
        Operation.scrollAndClick(Operation.findElementWithLocatorAndTextContains(categoryText, category));
    }

    public boolean isLocationBlockAndFirstImageOfItExisted() {
        Operation.scroll(Operation.findElement(locationsBlock));
        return Operation.findElement(locationsBlock).isDisplayed() && Operation.findElement(firstImageOfLocations).isDisplayed();
    }

    public boolean isTeamBlockAndFirstImageOfItExisted() {
        Operation.scroll(Operation.findElement(teamBlock));
        return Operation.findElement(teamBlock).isDisplayed() && Operation.findElement(firstImageOfTeamBlock).isDisplayed();
    }

    public boolean isLifeAtInsiderImageSliderBlockExisted() {
        Operation.scroll(Operation.findElement(lifeAtInsiderImageSliderBlock));
        return Operation.findElement(lifeAtInsiderImageSliderBlock).isDisplayed();
    }

    public void clickSeeAllJobs(){
        Operation.wait(2);
        Operation.clickElement(seeAllJobsButton);
    }

    public void navigateToQAJobs(){
        Operation.navigateToUrl(PropertyManager.getInstance().getProperty("url") + "/careers/quality-assurance/");
    }

}
