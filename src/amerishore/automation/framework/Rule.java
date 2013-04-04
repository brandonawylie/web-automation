package amerishore.automation.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Rule{
	private int type;
	//types of Rules, only Page is currently implemented
	private static final int PAGE = 0;
	private static final int BROWSER_URL = 1;
	private static final int BROWSER_META = 2;

	//the name of the rule
	private String name;
	//what you expect to be contained in the element referred to by selector
	private String expectation;
	//selector to grab the element
	private String selector;


	public Rule(String name, String selector, String expectation){
		this.name = name;
		this.expectation = expectation;
		this.selector = selector;
		type = 0;
	}

	/**
	 *
	 *
	 * @param name			Name of the rule
	 * @param type			The type, not yet implemented (page, url, meta etc)
	 * @param Selector      Selector for the element
	 * @param Expectation   What you expect at the selector
	 */
	public Rule(String name, int type, String Selector, String Expectation){
		this.name = name;
		this.type = type;
		this.selector = selector;
		this.expectation = expectation;
	}

	/**
	 * Validation for test cases, called from RuleSet.
	 *
	 * @param driver        The selenium object to check against
	 * @param exp 			The expected text at selector coming from the test case
	 * @return				Result, whether it passed/failed and why
	 */
	public Result validate(WebDriver driver, Expectation exp){
		boolean pass = false;
		String reason;
		try{
			WebElement e = driver.findElement(By.cssSelector(selector));
			String text = e.getText();
			if(text.contains(expectation)){
				pass = true;
				reason = selector + " had " + expectation;
			}else
				reason = selector + " didn't have " + expectation;
		}catch(Exception e){
			reason = "couldn't find " + selector + " on the page";
		}
		return new Result(name, pass, reason);
	}

	//post: returns the name of the rule
	public String getName(){
		return name;
	}
}