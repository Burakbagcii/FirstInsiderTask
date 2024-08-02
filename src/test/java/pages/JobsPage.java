package pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Operation;

public class JobsPage {
    public static By jobTitleTexts = By.xpath("//p[@class = 'position-title font-weight-bold']");
    public static By jobLocationTexts = By.xpath("//div[@class = 'position-location text-large']");
    public static By jobDepartmentTexts = By.xpath("//span[@class = 'position-department text-large font-weight-600 text-primary']");
    public static By jobCards = By.xpath("//div[@id = 'jobs-list']/div");
    public static By jobApplyButtons = By.xpath("//*[@class = 'btn btn-navy rounded pt-2 pr-5 pb-2 pl-5']");
    public static By jobNextPageButton = By.xpath("//*[@class = 'btn btn-yellow rounded has-icon page-button next pr-4']");
    public static By footer = By.id("footer");
    public static By locationFilterList = By.id("select2-filter-by-location-container");
    public static By locationFilterOption = By.xpath("//*[@class = 'select2-results']//li");
    public static By departmantFilterList =  By.id("select2-filter-by-department-container");
    public static By departmantFilterOption =  By.xpath("//*[@class = 'select2-results']//li");
    public static By browseOpenPositionsText =  By.xpath("//*[@class = 'col-12 d-flex flex-column flex-lg-row" +
            " justify-content-lg-between align-items-lg-end']");

    public void selectLocationFilter(String location){
        Operation.wait(5);
        Operation.clickElement(locationFilterList);
        Operation.wait(2);
        Operation.findElementWithLocatorAndTextContains(locationFilterOption,location).click();
    }

    public void selectDepartmantFilter(String departmant){
        Operation.clickElement(departmantFilterList);
        Operation.findElementWithLocatorAndTextContains(departmantFilterOption,departmant).click();
    }

    public boolean clickNextJobPageIfItIsExisted(){
        WebElement nextPageButton = Operation.findElement(jobNextPageButton);
        WebElement footerArea = Operation.findElement(footer);
        Operation.hover(footerArea);
        if (nextPageButton.isEnabled()){
            Operation.scrollAndClick(nextPageButton);
            return true;
        }
        return false;
    }

    public void scrollToBrowseOpenPositionsText(){
        Operation.scroll(Operation.findElement(browseOpenPositionsText));
        Operation.wait(2);
    }

    public List<WebElement> getJobCards(){
        return Operation.findElements(jobCards);
    }

    public List<String> getJobTitleTexts(){
        return Operation.findElements(jobTitleTexts).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getJobLocationTexts(){
        return Operation.findElements(jobLocationTexts).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getJobDepartmentTexts(){
        return Operation.findElements(jobDepartmentTexts).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<WebElement> getApplyButtons(){
        return Operation.findElements(jobApplyButtons);
    }

    public void hoverOnJobCard(WebElement element){
        Operation.hover(element);
        Operation.wait(2);
    }

    public void clickApplyButtonWithIndex(int index) {
        Operation.hover(getJobCards().get(index));
        Operation.wait(1);
        getApplyButtons().get(index).click();
    }
}
