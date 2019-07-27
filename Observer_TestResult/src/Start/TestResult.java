package Start;

public class TestResult {

	public void addFailure(Test test, Throwable t) {
		System.out.println("TestResult.addFailure() fired.");
	}

	public void addError(Test test, Throwable t) {
		System.out.println("TestResult.addError() fired.");
	}

	public void startTest(Test test) {
		System.out.println("TestResult.startTest() fired.");		
	}
	
	public void endTest(Test test) {
		System.out.println("TestResult.endTest() fired.");		
	}
	
}
