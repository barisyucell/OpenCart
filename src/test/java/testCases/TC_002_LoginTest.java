package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass
{

    @Test (groups = {"Sanity", "Master"})
    public void test_login()
    {
        try
        {
            logger.info("*** TC_002_LoginTest is starting ***");

            HomePage homePage = new HomePage(driver);

            homePage.clickMyAccount();
            logger.info("MyAccount link has been clicked");

            homePage.clickLogin();
            logger.info("Login link has been clicked");

            LoginPage loginPage = new LoginPage(driver);

            logger.info("Login details are providing...");
            loginPage.setEmail(resourceBundle.getString("email"));
            loginPage.setPassword(resourceBundle.getString("password"));

            loginPage.clickLogin();
            logger.info("Login button has been clicked");

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean targetPage = myAccountPage.isMyAccountPageExist();

            Assert.assertEquals(targetPage, true, "Invalid login data!");
        }
        catch (Exception e)
        {
            Assert.fail();
        }

        logger.info("*** TC_002_LoginTest has finished! ***");

    }

}