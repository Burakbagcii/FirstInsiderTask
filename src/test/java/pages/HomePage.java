package pages;

import org.openqa.selenium.By;
import utils.Operation;


public class HomePage{

    public final By acceptAllCookiesBtnByXpath = By.xpath("//a[@id='wt-cli-accept-all-btn']");
    public final By companyBtnByXpath = By.xpath("//a[contains(.,'Company')]");
    public final By careersBtnByXpath = By.xpath("//a[.='Careers']");

    public void clickacceptAllCookiesBtn(){
        Operation.clickElement(acceptAllCookiesBtnByXpath);
    }

    public void clickcompanyBtn(){
        Operation.clickElement(companyBtnByXpath);
    }

    public void clickcareersBtn(){
        Operation.clickElement(careersBtnByXpath);
    }

}
