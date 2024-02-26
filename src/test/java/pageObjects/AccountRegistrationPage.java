package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
{
    public AccountRegistrationPage (WebDriver driver)
    {
        super(driver);
    }


    // ELEMENTS:
    @FindBy (name = "firstname")
    WebElement txtFirstname;

    @FindBy (name = "lastname")
    WebElement txtLastname;

    @FindBy (name = "email")
    WebElement txtEmail;

    @FindBy (name = "telephone")
    WebElement txtTelephone;

    @FindBy (name = "password")
    WebElement txtPassword;

    @FindBy (name = "confirm")
    WebElement txtConfirmPassword;

    @FindBy (name = "agree")
    WebElement chkdPolicy;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;
    /*
    -> This element exists on "My Account" page that opens after successful Login.
       I used it in "getConfirmationMessage()" method below to check whether the Login process is successful.
    */


    // ACTION METHODS:
    public void setFirstname (String firstname)
    {
        txtFirstname.sendKeys(firstname);
    }

    public void setLastname (String lastname)
    {
        txtLastname.sendKeys(lastname);
    }

    public void setEmail (String email)
    {
        txtEmail.sendKeys(email);
    }

    public void setTelephone (String telephone)
    {
        txtTelephone.sendKeys(telephone);
    }

    public void setPassword (String password)
    {
        txtPassword.sendKeys(password);
    }

    public void setConfirmPassword (String confirmPassword)
    {
        txtConfirmPassword.sendKeys(confirmPassword);
    }

    public void setPrivacyPolicy()
    {
        chkdPolicy.click();
    }

    public void clickContinue()
    {
        btnContinue.click();

        /*
        *** Other ways to click "Continue" button ***

        btnContinue.submit();

        Actions actions = new Actions(driver);
        actions.moveToElement(btnContinue).click().perform();

        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        jsx.executeScript("arguments[0].click();", btnContinue);

        btnContinue.sendKeys(Keys.RETURN);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
        */
    }

    public String getConfirmationMessage()
    {
        try
        {
            return (msgConfirmation.getText());
        }
        catch (Exception e)
        {
            return (e.getMessage());
        }
    }

}