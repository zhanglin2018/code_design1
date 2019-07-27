package Finish.TextUI;

import Finish.Test;
import Finish.TestListener;
import Finish.TestResult;
import Finish.TestSuite;

// 命令行形式的 TestRunner
public class TestRunner implements TestListener {
	
	private TestResult fTestResult;
	private TestSuite testSuite;
	
	protected TestResult createTestResult() {
		//return new TestResult(this);		// 硬编码至 TextTestResult
		TestResult testResult = new TestResult();
	    testResult.addObserver(this);
	    return testResult;
	}
	
	synchronized public void runSuite() {
		System.out.println("TextUI.TestRunner.runSuite() fired.");
		fTestResult = createTestResult();
		testSuite.run(fTestResult);
	}
	
	/* (non-Javadoc)
	 * @see Finish.TextUI.TestListener#startTest(Finish.TestResult, Finish.Test)
	 */
	@Override
	public void startTest(TestResult testResult, Test test) {
		System.out.println("TextUI.TestRunner.startTest() fired.");
	}

	/* (non-Javadoc)
	 * @see Finish.TextUI.TestListener#addError(Finish.TestResult, Finish.Test, java.lang.Throwable)
	 */
	@Override
	public void addError(TestResult testResult, Test test, Throwable t) {
		System.out.println("TextUI.TestRunner.addError() fired.");
	}

	/* (non-Javadoc)
	 * @see Finish.TextUI.TestListener#addFailure(Finish.TestResult, Finish.Test, java.lang.Throwable)
	 */
	@Override
	public void addFailure(TestResult testResult, Test test, Throwable t) {
		System.out.println("TextUI.TestRunner.addFailure() fired.");
	}

	@Override
	public void endTest(TestResult testResult, Test test) {
		// TODO Auto-generated method stub
		
	}	
}
