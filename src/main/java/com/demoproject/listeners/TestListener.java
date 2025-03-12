package com.demoproject.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.demoproject.base.BaseClass;
import com.demoproject.utilities.ExtentManager;

public class TestListener implements ITestListener{
	
	// Triggered when test started
	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		
		// Start logging in extent report
		ExtentManager.startTest(testName);
		ExtentManager.logStep("Test started: " + testName);
	}
	
	// Triggered when test succeeds
	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		ExtentManager.logStepWithScreenshot(BaseClass.getDriver(), "Test Passed Successfully!", "Test End: " +testName + "- ✔ Test Passed");
	}

	// Triggered when test Failed
	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		String failureMessage = result.getThrowable().getMessage();
		ExtentManager.logStep(failureMessage);
		ExtentManager.logFailure(BaseClass.getDriver(), "Test Failed!", "Test End: " +testName + " - ❌ Test Failed");
	}

	// Triggered with test Skiped
	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		ExtentManager.logSkip("Test Skipped " +testName);
	}

	// Triggered when suite start
	@Override
	public void onStart(ITestContext context) {
		// Initialize the extent reports
		ExtentManager.getReporter();
	}
	
	// Triggered when the suite ends
	@Override
	public void onFinish(ITestContext context) {
		// Flush the extent report
		ExtentManager.endTest();
	}
	
}
