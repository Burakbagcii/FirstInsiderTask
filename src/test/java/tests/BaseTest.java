package tests;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.BeforeTest;
import utils.BrowserManager;
import utils.Operation;
import utils.helpers.PropertyManager;

public class BaseTest implements ITestListener {



    @Override
    public void onTestFailure(ITestResult result) {
        Operation operation = new Operation();
        Operation.takeScreenShot(PropertyManager.getInstance().getProperty("screenshotFolder"));
    }

    @BeforeTest
    public void beforeSuite() {
        BrowserManager.getInstance().initialize(PropertyManager.getInstance().getProperty("browser"));
    }
}
