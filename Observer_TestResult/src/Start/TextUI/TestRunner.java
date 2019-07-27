package Start.TextUI;

import Start.Test;
import Start.TestResult;
import Start.TestSuite;

// 命令行形式的 TestRunner
public class TestRunner {
	
	private TestResult fTestResult;
	private TestSuite testSuite;
	
	protected TextTestResult createTestResult() {
		return new TextTestResult(this);		// 硬编码至 TextTestResult
	}
	
	synchronized public void runSuite() {
		System.out.println("TextUI.TestRunner.runSuite() fired.");
		fTestResult = createTestResult();
		testSuite.run(fTestResult);
	}
	
	public void startTest(TestResult testResult, Test test) {
		System.out.println("TextUI.TestRunner.startTest() fired.");
	}

	public void addError(TestResult testResult, Test test, Throwable t) {
		System.out.println("TextUI.TestRunner.addError() fired.");
	}

	public void addFailure(TestResult testResult, Test test, Throwable t) {
		System.out.println("TextUI.TestRunner.addFailure() fired.");
	}	
}
