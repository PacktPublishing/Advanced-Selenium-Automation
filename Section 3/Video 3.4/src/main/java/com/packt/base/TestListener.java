package com.packt.base;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("- - - Test is starting - - -");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("- - - Test Passed - - -");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("- - - Test Failed - - -");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("- - - START - - -");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("- - - FINISH - - -");
	}

}
