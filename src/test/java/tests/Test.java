package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import pages.CareerPage;
import pages.HomePage;
import pages.JobsPage;
import pages.LeverPage;
import utils.BrowserManager;

import java.util.List;

@Listeners(BaseTest.class)
public class Test extends BaseTest {

    private static final String JOB_LOCATION = "Istanbul, Turkey";
    private static final String JOB_LOCATION_CONTAINS = "Istanbul, Turkey";
    private static final String JOB_TITLE_CONTAINS = "Quality Assurance";
    private static final String JOB_DEPARTMANT = "Quality Assurance";
    private static final String JOB_DEPARTMANT_CONTAINS = "Quality Assurance";



    @org.testng.annotations.Test
    public void test1(){

        System.out.println("Navigating careers page!");
        HomePage homePage = new HomePage();
        homePage.clickacceptAllCookiesBtn();
        homePage.clickcompanyBtn();
        homePage.clickcareersBtn();
        System.out.println("Navigating careers page is done successfully!");

        System.out.println("Starting verification of Careers Page sections.");
        CareerPage careerPage = new CareerPage();
        Assert.assertTrue(careerPage.isTeamBlockAndFirstImageOfItExisted(), "Team section is not visible.");
        Assert.assertTrue(careerPage.isLocationBlockAndFirstImageOfItExisted(), "Location section is not visible.");
        Assert.assertTrue(careerPage.isLifeAtInsiderImageSliderBlockExisted(), "Life At Insider section is not visible.");
        System.out.println("Verification of Careers Page sections completed.");

        System.out.println("Starting navigation to the QA Jobs section on the Careers page.");
        careerPage.navigateToQAJobs();
        careerPage.clickSeeAllJobs();
        System.out.println("Verification of Careers Page sections completed.");

        System.out.println("Applying location filter with values");
        JobsPage jobsPage = new JobsPage();
        jobsPage.selectLocationFilter(JOB_LOCATION);
        jobsPage.selectDepartmantFilter(JOB_DEPARTMANT );
        System.out.println("Successfully applied location and department filters");

        System.out.println("Starting the validation of job listings and applying for a position on the JobsPage.");
        jobsPage.scrollToBrowseOpenPositionsText();
        do{
            List<WebElement> jobCards = jobsPage.getJobCards();
            List<WebElement> applyButtons = jobsPage.getApplyButtons();
            List<String> jobTitleTextList = jobsPage.getJobTitleTexts();
            List<String> jobLocationTextList = jobsPage.getJobLocationTexts();
            List<String> jobDepartmentTextList = jobsPage.getJobDepartmentTexts();
            for (int i = 0; i < jobCards.size(); i++) {
                jobsPage.hoverOnJobCard(jobCards.get(i));
                Assert.assertTrue(applyButtons.get(i).isDisplayed(), "The Apply Now button was not displayed");
                Assert.assertTrue(jobTitleTextList.get(i).contains(JOB_TITLE_CONTAINS),"The job title did not appear as expected");
                Assert.assertTrue(jobLocationTextList.get(i).contains(JOB_LOCATION_CONTAINS) ,"The job location did not appear as expected");
                Assert.assertTrue(jobDepartmentTextList.get(i).contains(JOB_DEPARTMANT_CONTAINS),"The job department did not appear as anticipated");
            }
        }while (jobsPage.clickNextJobPageIfItIsExisted());
        jobsPage.clickApplyButtonWithIndex(0);
        LeverPage leverPage = new LeverPage();
        Assert.assertTrue(leverPage.isApplyButtonExisted(), "The apply button on the leverage page was not displayed");
        Assert.assertTrue(leverPage.isHeaderPositionTextExisted(), "Lever page header position was not visible");
        System.out.println("Finished validating job listings and applied for the first position.");
    }

    @AfterTest
    public void tearDown(){
        BrowserManager.getInstance().quitWebdriver();
    }

}
