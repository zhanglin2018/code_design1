package Finish;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestResult {

	protected TestListener fRunner;

/*	public TestResult(TestListener testRunner) {
		// TODO Auto-generated constructor stub
	}*/
	
	private List observers = new ArrayList();
	
	public void addObserver(TestListener testListener) {
	    observers.add(testListener);
	}


	public synchronized void startTest(Test test) {
		System.out.println("TestResult.startTest() fired.");
		
		System.out.println("TextUI.TextTestResult.startTest() fired.");
		//super.startTest(test);
		//fRunner.startTest(this, test);			// ֪ͨ  TextUI.TestRunner
		
		System.out.println("UI.UITestResult.startTest() fired.");
		
		for (Iterator i = observers.iterator();i.hasNext();) {
		      TestListener observer = (TestListener)i.next();
		      observer.startTest(this, test);
		    }
	}

	public synchronized void addError(Test test, Throwable t) {
		System.out.println("TestResult.addError() fired.");
		
		System.out.println("TextUI.TextTestResult.addError() fired.");
		//super.addError(test, t);
		//fRunner.addError(this, test, t);		// ֪ͨ  TextUI.TestRunner
		
		System.out.println("UI.UITestResult.addError() fired.");
		
		for (Iterator i = observers.iterator();i.hasNext();) {
		      TestListener observer = (TestListener)i.next();
		      observer.addError(this, test, t);
		    }
	}

	public synchronized void addFailure(Test test, Throwable t) {
		System.out.println("TestResult.addFailure() fired.");
		
		System.out.println("TextUI.TextTestResult.addFailure() fired.");
		//super.addFailure(test, t);
		//fRunner.addFailure(this, test, t);		// ֪ͨ  TextUI.TestRunner
		
		System.out.println("UI.UITestResult.addFailure() fired.");
		
		for (Iterator i = observers.iterator();i.hasNext();) {
		      TestListener observer = (TestListener)i.next();
		      observer.addFailure(this, test, t);
		    }
	}

	public synchronized void endTest(Test test) {
		System.out.println("TestResult.endTest() fired.");		
		
		System.out.println("UI.UITestResult.endTest() fired.");
		//super.endTest(test);
		//fRunner.endTest(this, test);			// ֪ͨ  UI.TestRunner
		
		for (Iterator i = observers.iterator();i.hasNext();) {
		      TestListener observer = (TestListener)i.next();
		      observer.endTest(this, test);
		    }
	}
}
