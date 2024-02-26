package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass
{
    @Test (groups = {"Regression", "Master"})
    void test_account_Registration()
    {
        logger.debug("Application logs...");

        logger.info("*** TC_001_AccountRegistrationTest is starting ***");

        try
        {
            HomePage homePage = new HomePage(driver);

            homePage.clickMyAccount();
            logger.info("MyAccount link has been clicked");

            homePage.clickRegister();
            logger.info("Register link has been clicked");


            AccountRegistrationPage accountRegistrationPage = new AccountRegistrationPage(driver);

            logger.info("Customer data are providing...");
            accountRegistrationPage.setFirstname(randomString().toUpperCase());
            accountRegistrationPage.setLastname(randomString().toUpperCase());
            accountRegistrationPage.setEmail(randomString() + "@gmail.com");
            accountRegistrationPage.setTelephone(randomNumber());

            String password = randomAlphaNumeric();
            accountRegistrationPage.setPassword(password);
            accountRegistrationPage.setConfirmPassword(password);

            accountRegistrationPage.setPrivacyPolicy();

            accountRegistrationPage.clickContinue();
            logger.info("Continue button has been clicked");

            String confirmationMessage = accountRegistrationPage.getConfirmationMessage();
            logger.info("Expected message is validating...");
            Assert.assertEquals(confirmationMessage, "Your Account Has Been Created!", "Test has failed!");
        }
        catch (Exception e)
        {
            logger.error("Test has failed!");
            Assert.fail();
        }

        logger.info("*** TC_001_AccountRegistrationTest has finished! ***");

    }

}