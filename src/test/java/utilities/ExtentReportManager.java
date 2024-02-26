package utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter extentSparkReporter;
	public ExtentReports extentReports;
	public ExtentTest extentTest;
	String reportName;


	public void onStart (ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		reportName = "Test-Report-" + timeStamp + ".html";

		extentSparkReporter = new ExtentSparkReporter(".\\reports\\" + reportName);
		// specifying location for reports.

		extentSparkReporter.config().setDocumentTitle("OpenCart Automation Report");
		extentSparkReporter.config().setReportName("OpenCart Functional Testing");
		extentSparkReporter.config().setTheme(Theme.DARK);

		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);

		// Providing some info for report:
		extentReports.setSystemInfo("Application", "OpenCart");
		extentReports.setSystemInfo("Module", "Admin");
		extentReports.setSystemInfo("Sub Module", "Customers");
		extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReports.setSystemInfo("Environment", "QA");
	}

	public void onTestSuccess (ITestResult result)
	{
		extentTest = extentReports.createTest(result.getName());
		extentTest.log(Status.PASS, "Test PASSED!");
	}

	public void onTestFailure (ITestResult result)
	{
		extentTest = extentReports.createTest(result.getName());
		extentTest.log(Status.FAIL, "Test FAILED!");
		extentTest.log(Status.FAIL, result.getThrowable().getMessage());

		try
		{
			String imgPath = new BaseClass().captureScreen(result.getName());
			extentTest.addScreenCaptureFromPath(imgPath);
			// Screenshots are taken for failed test cases and attach to the test report.
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}

	public void onTestSkipped (ITestResult result)
	{
		extentTest = extentReports.createTest(result.getName());
		extentTest.log(Status.SKIP, "Test SKIPPED!");
		extentTest.log(Status.SKIP, result.getThrowable().getMessage());
	}

	public void onFinish (ITestContext testContext)
	{
		extentReports.flush();

		/*

		*** If we want to send the report by e-mail, we need to use the following code block: ***

		try
		{
		URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);

		ImageHtmlEmail email = new ImageHtmlEmail();

		email.setDataSourceResolver(new DataSourceUrlResolver(url));
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("abcd@gmail.com","password"));
		email.setSSLOnConnect(true);
		email.setFrom("abcd@gmail.com");  // sender of the e-mail.
		email.setSubject("Test Results");
		email.setMsg("Please find the attached report...");
		email.addTo("xyz@gmail.com");  // recipient of the e-mail.
		email.attach(url, "extent report", "please check the report...");
		email.send();
		}
		catch (Exception e)
		{
		e.printStackTrace();
		}

		*/
	}

}