package amerishore.automation.framework;
import java.util.*;

import org.openqa.selenium.WebDriver;
public class RuleSet{
	Map<String, Rule> rules = new HashMap<String, Rule>();

	public RuleSet(ArrayList<Rule> rules){
		for(Rule r : rules){
			this.rules.put(r.getName(), r);
		}
	}

	public void validate(WebDriver driver, TestCase testcase){
		ArrayList<Result> results = new ArrayList<Result>();
		for(Rule r : rules.values()){
			Result tempRes = r.validate(driver, testcase.getExpectation());
			/* There are two cases where we want to store the results
			 * 1. If the test case failed a rule, whether it was expected to or not
			 * 2. If the test case passed & it was the rule the test case was written for
			 */
			if(!tempRes.getResult()){
				results.add(tempRes);
			}else if(tempRes.getResult() && r.getName().equals(testcase.getExpectation().getRuleName())){
				results.add(tempRes);
			}
		}

		testcase.addResults(results);
	}
}