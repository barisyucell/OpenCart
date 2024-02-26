package testBase;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

public class BaseClass
{
    public static WebDriver driver;
    public Logger logger;
    public ResourceBundle resourceBundle;


    @BeforeClass (groups = {"Master", "Sanity", "Regression"})
    @Parameters ("browser")
    public void setup (String br)
    {
        resourceBundle = ResourceBundle.getBundle("config");
        logger = LogManager.getLogger(this.getClass());

        if (br.equals("chrome"))
        {
            driver = new ChromeDriver();
        }
        else if (br.equals("edge"))
        {
            driver = new EdgeDriver();
        }
        else
        {
            driver = new FirefoxDriver();
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(resourceBundle.getString("appURL"));
        driver.manage().window().maximize();
    }

    @AfterClass (groups = {"Master", "Sanity", "Regression"})
    public void tearDown()
    {
        driver.quit();
    }

    public String randomString()
    {
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }
    /*
    -> This method generates a random String, and we use the generated String to fill in the "First Name", "Last Name" and "E-Mail" fields
       for registration (in "TC_001_AccountRegistrationTest").
    */

    public String randomNumber()
    {
        String generatedString2 = RandomStringUtils.randomNumeric(10);
        return generatedString2;
    }
    /*
    -> This method generates a random number, and we use the generated number to fill in the "Telephone" field for registration
       (in "TC_001_AccountRegistrationTest").
    */

    public String randomAlphaNumeric()
    {
        String str = RandomStringUtils.randomAlphabetic(4);
        String num = RandomStringUtils.randomNumeric(3);

        return (str + "@" + num);
    }
    /*
    -> This method generates a random "String@Number" value, and we use it to fill in the "Password" field for registration
       (in "TC_001_AccountRegistrationTest").
    */


    // For failed test cases, this method runs and takes screenshot.
    public String captureScreen(String tname) throws IOException
    {
        // timestamp for screenshots:
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

        try
        {
            FileUtils.copyFile(source, new File(destination));
        }
        catch (Exception e)
        {
            e.getMessage();
        }

        return destination;
    }

}