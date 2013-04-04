package amerishore.automation.framework;

public class Expectation {
	//name of rule we are expecting to break
	private String ruleName;
	//the text that will appear if there is an error
	private String target;
	private boolean expectedOutcome;

	public Expectation(String ruleName, String target, boolean expectedOutcome){
		this.ruleName = ruleName;
		this.target = target;
		this.expectedOutcome = expectedOutcome;
	}

	public String getRuleName(){
		return ruleName;
	}
}
