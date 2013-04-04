package amerishore.automation.framework;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestCase{

	public ArrayList<ArrayList<Result>> runResults = new ArrayList<ArrayList<Result>>();
    /// <summary>
    /// Holds a list of PageActions to be implemented by the test case
    /// </summary>
    private List<PageAction> actions;

    /// <summary>
    /// Holds a list of PageElements to be interacted with by the PageActions
    /// </summary>
    private List<PageElement> elements;

    /// <summary>
    /// startUrl - The first url of the test
    /// endUrl - The ending url of the test
    /// id - the test's unique id from tfs
    /// title - the test's title from tfs
    /// passOrFail - wether the test's error messages matched the expectation or not
    /// pfReason - why the test failed
    /// </summary>
    private String startUrl, endUrl;
    private String title, id;
    private Expectation expectation;

    /// <summary>
    /// Constructor
    /// </summary>
    /// <param name="title">Title of test case</param>
    /// <param name="id">ID number of test case</param>
    public TestCase(String title, String id)
    {
     //   super(title, id);
        this.title = "fdas";
        this.id = "fdas";
    }

    /// <summary>
    /// Executes the current test case with the chosen / configuration IWebDriver for Firefox
    /// 1) Gets the element output
    /// 2) COnsturct a WebElement(query) from a PageElement
    /// 3) Run an action on the WebElement
    /// 4) Add the temp string ot the string containing all output from the elements / actions
    /// 5) Write the output to a string and return the string
    /// </summary>
    /// <param name="driver">The Selenium driver chosen to run in the web browser</param>
    /// <returns>A string of all returned output from the elements / actions</returns>
    public String execute(WebDriver driver)
    {
        WebElement query = null;
        String temp = "";
        for (int i = 0; i < getElements().size(); i++)
        {
            query = elements.get(i).getElement(driver);
            temp += actions.get(i).runAction(query);
        }
        return temp;
    }

    /*PageAction methods*/
    public List<PageAction> getActions() { return this.actions; }
    public void addActions(List<PageAction> actions) { this.actions = actions; }
    public String getStringActions()
    {
        String s = "";
        for(PageAction pa : this.actions)
            s += (pa.getAction() + " ");
        return s;
    }

    /*PageElement methods*/
    public List<PageElement> getElements() { return this.elements; }
    public void addElements(List<PageElement> elements) { this.elements = elements; }
    public String getStringElements()
    {
        String s = "";
        for(PageElement pe : this.elements)
            s += (pe.getOutput() + " ");
        return s;
    }

//    /*configIdAndName methods*/
//    public void addConfig(IdAndName IDN) { configIdAndName.Add(IDN); }
//    public List<IdAndName> getConfigurations() { return configIdAndName; }

    /*Expectation methods*/
    public void addExpectation(Expectation exp) { this.expectation = exp; }
    public Expectation getExpectation() { return expectation; }

    /*Test case ID methods*/
    public String getID() { return id; }
    public void setID(String id) { this.id = id; }


    /*Get the test case title*/
    public String getTitle() { return title; }


    public void addResults(ArrayList<Result> results){
    	runResults.add(results);
    }

}
