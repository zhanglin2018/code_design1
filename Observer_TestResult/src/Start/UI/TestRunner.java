package Start.UI;

import java.awt.Frame;

import Start.Test;
import Start.TestResult;
import Start.TestSuite;

//图形界面形式的 TestRunner
public class TestRunner extends Frame {
	
	private TestResult fTestResult;
	private TestSuite testSuite;
	
	protected TestResult createTestResult() {
		return new UITestResult(this); 			// 硬编码至  UITestResult
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
