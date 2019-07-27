package Finish;

public interface TestListener {

	void startTest(TestResult testResult, Test test);

	void addError(TestResult testResult, Test test, Throwable t);

	void addFailure(TestResult testResult, Test test, Throwable t);
	
	void endTest(TestResult testResult, Test test);
}