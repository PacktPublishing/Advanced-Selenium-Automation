package com.packt.base;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportListener implements IReporter {

	private static final String OUTPUT_FOLDER = "target/";
	private static final String FILE_NAME = "ExtentReport.html";

	private ExtentReports extent;


	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		init(xmlSuites);

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestNodes(context.getFailedTests(), Status.FAIL);
				buildTestNodes(context.getSkippedTests(), Status.SKIP);
				buildTestNodes(context.getPassedTests(), Status.PASS);

			}
		}

		for (String s : Reporter.getOutput()) {
			extent.setTestRunnerOutput(s);
		}

		extent.flush();
	}


	private void init(List<XmlSuite> xmlSuites) {
		String suiteName = xmlSuites.get(0).getName();
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER + FILE_NAME);
		htmlReporter.config().setDocumentTitle("ExtentReports: " + suiteName);
		htmlReporter.config().setReportName(suiteName);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setEncoding("utf-8");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setReportUsesManualConfiguration(true);
	}


	private void buildTestNodes(IResultMap tests, Status status) {
		// IResultMap is not sorted. To make it look nice in report first we need to sort tests (sort by start time)
		SortedSet<ITestResult> sortedSet = new TreeSet<>();
		for (ITestResult result : tests.getAllResults()) {
			sortedSet.add(result);
		}

		// Now we go through sorted results
		ExtentTest test;
		if (tests.size() > 0) {
			for (ITestResult result : sortedSet) {
				test = extent.createTest(result.getTestContext().getCurrentXmlTest().getName() + " - "
						+ result.getMethod().getMethodName());

				// Using Class short name instead of category
				test.assignCategory(result.getMethod().getRealClass().getSimpleName());

				Throwable throwable = result.getThrowable();

				if (throwable != null) {

					// Add parameters
					Object[] parameters = result.getParameters();
					if (parameters.length > 0) {
						try {
							// If parameters are String
							String params = "";
							for (Object object : parameters) {
								if (params.isEmpty()) {
									params = (String) object;
								} else {
									params = params + "," + (String) object;
								}
							}
							test.info(params);
						} catch (ClassCastException e) {
							// If parameters are HashMap
							@SuppressWarnings("unchecked")
							HashMap<String, String> table = (HashMap<String, String>) parameters[0];
							test.info(table.toString());
						}
					} else {
						test.info("This test doesn't have data");
					}

					// Finally, log throwable
					test.log(status, throwable);

				} else {
					test.log(status, "Test " + status.toString().toLowerCase() + "ed");
				}

				test.getModel().setStartTime(getTime(result.getStartMillis()));
				test.getModel().setEndTime(getTime(result.getEndMillis()));
			}
		}
	}


	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
}