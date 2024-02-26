package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage
{
    public MyAccountPage (WebDriver driver)
    {
        super(driver);
    }


    // ELEMENTS:
    @FindBy (xpath = "//h2[text()='My Account']")
    WebElement msgHeading;

    @FindBy (xpath = "//a[@class='list-group-item' and text()='Logout']")
    WebElement lnkLogout;


    // ACTION METHODS:
    public boolean isMyAccountPageExist()
    {
        try
        {
            return msgHeading.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public void clickLogout()
    {
        lnkLogout.click();
    }

}