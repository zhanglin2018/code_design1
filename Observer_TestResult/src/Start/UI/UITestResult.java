package Start.UI;

import Start.Test;
import Start.TestResult;

class UITestResult extends TestResult {
	
	private TestRunner fRunner; 				// 硬编码到  UI.TestRunner

	UITestResult(TestRunner runner) {
		fRunner = runner;
	}
	
	public synchronized void startTest(Test test) {
		System.out.println("UI.UITestResult.startTest() fired.");
		super.startTest(test);
		fRunner.startTest(this, test);			// 通知  UI.TestRunner
	}	

	public synchronized void addError(Test test, Throwable t) {
		System.out.println("UI.UITestResult.addError() fired.");
		super.addError(test, t);
		fRunner.addError(this, test, t); 		// 通知  UI.TestRunner
	}
	
	public synchronized void addFailure(Test test, Throwable t) {
		System.out.println("UI.UITestResult.addFailure() fired.");
		super.addFailure(test, t);
		fRunner.addFailure(this, test, t); 		// 通知  UI.TestRunner
	}
	
	public synchronized void endTest(Test test) {
		System.out.println("UI.UITestResult.endTest() fired.");
		super.endTest(test);
		fRunner.endTest(this, test);			// 通知  UI.TestRunner
	}
}