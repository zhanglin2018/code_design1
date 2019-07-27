package Start.TextUI;

import Start.Test;
import Start.TestResult;

public class TextTestResult extends TestResult {
	
	private TestRunner fRunner;					// Ӳ���뵽  TextUI.TestRunner
	
	TextTestResult(TestRunner runner) {
		fRunner = runner;
	}
	
	public synchronized void startTest(Test test) {
		System.out.println("TextUI.TextTestResult.startTest() fired.");
		super.startTest(test);
		fRunner.startTest(this, test);			// ֪ͨ  TextUI.TestRunner
	}

	public synchronized void addError(Test test, Throwable t) {
		System.out.println("TextUI.TextTestResult.addError() fired.");
		super.addError(test, t);
		fRunner.addError(this, test, t);		// ֪ͨ  TextUI.TestRunner
	}

	public synchronized void addFailure(Test test, Throwable t) {
		System.out.println("TextUI.TextTestResult.addFailure() fired.");
		super.addFailure(test, t);
		fRunner.addFailure(this, test, t);		// ֪ͨ  TextUI.TestRunner
	}
}
