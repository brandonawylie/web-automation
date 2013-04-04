package amerishore.automation.framework;
/*
	author: brandon dalesnadro
	class to encapsulate a result of an action performed by the automator
	intended to be used for test case runs & config changes/runs

	TODO:
	1. Investigate pros/cons for subclassing this into testcase result and config result
*/

public class Result{

	private boolean elementNotFound = false;
	private boolean pass;
	private String reason;
	private String ruleName;

	public Result(String ruleName, boolean pass, String reason){
		this.pass = pass;
		this.reason = reason;
		this.ruleName = ruleName;
	}

	public boolean getResult(){
		return pass;

	}

	public String getReason(){
		return reason;
	}

	public String getRuleName(){
		return ruleName;
	}
}