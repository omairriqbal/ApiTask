package helper.java.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import helper.java.utils.TestUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class TestListeners implements ITestListener {
    TestUtils utils;
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;

    public TestListeners() {
        utils = new TestUtils();
    }

    public ExtentReports setupExtentReporter() {
        extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") +
                File.separator + "html-report/ExecutionReport_" + utils.getDateTime() + ".html");
        extentReports.attachReporter(sparkReporter);

        sparkReporter.config().setDocumentTitle("Document Title");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Regression Test Results");
        return extentReports;
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Before the start of tests
        System.out.println("TEst started");
        extentTest = extentReports.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, "Test Case: " + result.getMethod().getMethodName() + " Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.FAIL, "Test Case: " + result.getMethod().getMethodName() + " Failed");
        extentTest.log(Status.FAIL, result.getThrowable());
        result.getThrowable().printStackTrace();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Handle skipped tests if necessary
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        // Handle timeouts if necessary
    }

    @Override
    public void onStart(ITestContext context) {
        // Setup the extent report here
        extentReports = setupExtentReporter();
    }

    @Override
    public void onFinish(ITestContext context) {
        // Closing the report
        extentReports.flush();
    }

    public void loggerPass(String testCaseName, String message) {
        if (extentTest != null) {
            System.out.println("Start logging");

            Markup m = MarkupHelper.createLabel(testCaseName, ExtentColor.GREEN);
            extentTest.log(Status.PASS, m);
            extentTest.log(Status.PASS, MarkupHelper.createCodeBlock(message, CodeLanguage.JSON));
        } else {
            System.out.println("ExtentTest is not initialized.");
        }
    }

    public void loggerLog(String message) {
        if (extentTest != null) {
            Markup m = MarkupHelper.createLabel(message, ExtentColor.INDIGO);
            extentTest.log(Status.INFO, m);
        } else {
            System.out.println("ExtentTest is not initialized.");
        }
    }
}

