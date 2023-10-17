package com.trello.reports;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.trello.utils.PropertiesInfo;
import org.testng.*;

import java.io.File;
import java.util.Arrays;
import java.util.Date;

public class ExtentReportsListener implements ITestListener, ISuiteListener {
    private static Date date = new Date();
    private static String reportDirectory =  System.getProperty("user.dir") + File.separator + "reports";
    public static String reportFileName = "Extent_" + PropertiesInfo.getInstance().getEnv() + "_" + date.toString().replace(":", "_").replace(" ", "_") + ".html";
    public static ExtentReports extentReports = ExtentReportManager.createInstance(reportDirectory.concat(File.separator).concat(reportFileName));
    public ThreadLocal<ExtentTest> currentTest = new ThreadLocal<ExtentTest>();

    static {
        System.out.println(reportDirectory);
        File directory = new File(reportDirectory);
        if (!directory.exists()){
            directory.mkdir();
            System.out.println("Created report directory: ".concat(reportDirectory));
        }
        extentReports = ExtentReportManager.createInstance(reportDirectory.concat(File.separator).concat(reportFileName));
    }
    @Override
    public void onStart(ISuite suite) {
        ISuiteListener.super.onStart(suite);
    }

    @Override
    public void onFinish(ISuite suite) {
        ISuiteListener.super.onFinish(suite);
    }

    @Override
    public void onTestStart(ITestResult result) {
//        ITestListener.super.onTestStart(result);
        currentTest.set(extentReports.createTest("TestCase: ".concat(result.getName())));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
//        ITestListener.super.onTestSuccess(result);
        String methodName = result.getName();
        String logText = String.format("<b>TEST CASE: %%s PASSED</b>", methodName.toUpperCase());
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        currentTest.get().pass(m);
    }

    @Override
    public void onTestFailure(ITestResult result) {
//        ITestListener.super.onTestFailure(result);

        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        currentTest.get().fail(String.format("<details> <summary> <b> <font color=red> Exception: Click to see</font></b></summary>%s</details>\n", exceptionMessage.replace(",", "<br>")));

        String failureLog = " Test Case Failed";
        Markup m = MarkupHelper.createLabel(failureLog, ExtentColor.RED);
        currentTest.get().log(Status.FAIL, m);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
//        ITestListener.super.onTestSkipped(result);
        String methodName = result.getMethod().getMethodName();
        String logText = String.format("<b> Test Case: %s Skipped </b>", methodName);
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        currentTest.get().skip(m);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
//        ITestListener.super.onFinish(context);
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}
