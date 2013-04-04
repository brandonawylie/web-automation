package amerishore.automation.framework;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class ScreenRobot {

    /*Variables*/
    //Default user agent string, this should be changed to be loaded from the configuration file
    private String USER_AGENT = "Mozilla/5.0 (iPhone; CPU iPhone OS 5_0 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9A334 Safari/7534.48.3";

    //The start url and the userAgentString
    private String url, userAgentStr;

    //The TestCaseCollection to be run
    //private TestCaseCollection tcc;

    //The web driver to be used
    private WebDriver driver;

    RuleSet ruleSet;

    /*Constructors*/
    public ScreenRobot(RuleSet ruleSet){
    	this.ruleSet = ruleSet;
    }

    /// <summary>
    /// Constructor
    /// </summary>
    /// <param name="tcc">TestCaseCollection to be run</param>
    /// <param name="url">URL to be used</param>
//    public ScreenRobot(TestCaseCollection tcc, String url)
//    {
//        this.tcc = tcc;
//        userAgentStr = USER_AGENT;
//        this.url = url;
//    }

    /// <summary>
    /// Constructor
    /// </summary>
    /// <param name="tcc">TestCaseCollection to run</param>
    /// <param name="userAgent">User agent string to use</param>
    /// <param name="url">Starting url</param>
//    public ScreenRobot(TestCaseCollection tcc, String userAgent, String url)
//    {
//        this.tcc = tcc;
//        userAgentStr = userAgent;
//        this.url = url;
//    }


    //pre: testcases are all valid
    //post: adds results to the test cases
    public void run(ArrayList<TestCase> testCases)
    {

        /*method performs the actions in the testcasecollection on the elements in the testcasecollection
         * 1.makes a firefox profile with user agent settings (user agent will default to iOS5 for now)
         * 2.construct a WebDriver
         * 3.perform actions on webelements
         * 4.return output from the elements and actions
         */

    	//TODO add capability to change this
        driver = new FirefoxDriver();
        //TODO figure this URL thing out
        driver.get(url);

        for(int i = 0; i < testCases.size(); i++){
	        TestCase testCase = testCases.get(i);
        	try
	        {
	            testCase.execute(driver);
	            ruleSet.validate(driver, testCase);
	            //lol what?!
	            //driver.close();
	            driver.get(url);
	        }
	        catch (Exception e)
	        {

	        }
        }
    }

}
