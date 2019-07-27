package Finish.UI;

import java.awt.Frame;

import Finish.Test;
import Finish.TestListener;
import Finish.TestResult;
import Finish.TestSuite;

//图形界面形式的 TestRunner
public class TestRunner extends Frame implements TestListener {
	
	private TestResult fTestResult;
	private TestSuite testSuite;
	
	protected TestResult createTestResult() {
		//return new TestResult(this); 			// 硬编码至  UITestResult
		TestResult testResult = new TestResult();
	    testResult.addObserver(this);
	    return testResult;
	}

	synchronized public void runSuite() {
		System.out.println("UI.TestRunner.runSuite() fired.");
		fTestResult = createTestResult();
		testSuite.run(fTestResult);
	}
	
	public void startTest(TestResult testResult, Test test) {
		System.out.println("UI.TestRunner.startTest() fired.");		
	}

	public void addFailure(TestResult result, Test test, Throwable t) {
		System.out.println("UI.TestRunner.addFailure() fired.");
	}
	
	public void addError(TestResult testResult, Test test, Throwable t) {
		System.out.println("UI.TestRunner.addError() fired.");		
	}

	public void endTest(TestResult testResult, Test test) {
		System.out.println("UI.TestRunner.endTest() fired.");
	}	
}
