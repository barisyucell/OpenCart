package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

import testBase.BaseClass;

import utilities.DataProviders;

public class TC_003_LoginDataDrivenTest extends BaseClass
{

    @Test (dataProvider = "LoginData", dataProviderClass = DataProviders.class)
    public void test_loginDataDrivenTest(String email, String password, String expectedResult)
    {
        try
        {
            logger.info("*** TC_003_LoginDataDrivenTest is starting ***");

            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccount();
            homePage.clickLogin();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail(email);
            loginPage.setPassword(password);
            loginPage.clickLogin();

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean targetPage = myAccountPage.isMyAccountPageExist();

            /*
            *** We need to check whether the test data are valid.
                Because sometimes the test result may be "failed" even though the valid test data are used.
                Or the test result may be "passed" with invalid test data.
            **  Checking-1:
            ->  If the "res (expected result)" value in "OpenCart_LoginData" Excel file is "Valid" and Login process is successful
                for the specified user (it means "targetPage = true"), then our test is passed.
            ->  If the "res (expected result)" value in "OpenCart_LoginData" Excel file is "Valid" but Login process is failed
                for the specified user (it means "targetPage = false"), then our test is failed.
            */
            if (expectedResult.equals("Valid"))
            {
                if (targetPage == true)
                {
                    myAccountPage.clickLogout();
                    Assert.assertTrue(true);
                }
                else
                {
                    Assert.assertTrue(false);
                }
            }

            /*
            ** Checking-2:
            -> If the "res (expected result)" value in "OpenCart_LoginData" Excel file is "Invalid" but Login process is successful
               for the specified user (it means "targetPage = true"), then our test is failed.
            -> If the "res (expected result)" value in "OpenCart_LoginData" Excel file is "Invalid" and Login process is failed
               for the specified user (it means "targetPage = false"), then our test is passed.
            */
            if (expectedResult.equals("Invalid"))
            {
                if (targetPage == true)
                {
                    myAccountPage.clickLogout();
                    Assert.assertTrue(false);
                }
                else
                {
                    Assert.assertTrue(true);
                }
            }
        }
        catch (Exception e)
        {
            Assert.fail();
        }

        logger.info("*** TC_003_LoginDataDrivenTest has finished! ***");

    }

}