package amerishore.automation.framework;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageElement {
    //Find page element by ID
    public final static int ID = 1;

    //Find page element by CSS command
    public final static int CSS = 2;

    //Find page element by LINK_TEXT
    public final static int LINK_TEXT = 3;

    //Find page elmenet by XPATH command
    public final static int XPATH = 4;

    //A list of all elements that will be interacted with
    private List<String> element;

    //The action to commit to this page
    private int action;

    /// <summary>
    /// Constructor based off of element
    /// </summary>
    /// <param name="element">The element to base instance off of</param>
    public PageElement(String element)
    {
        this.element = new ArrayList<String>();
        this.element.add(element);
        action = ID;
    }

    /// <summary>
    /// Constructor based off of type of selector, and the name
    /// </summary>
    /// <param name="selectorType">The type of page element to look for</param>
    /// <param name="selector">What you will put into that page element</param>
    public PageElement(int selectorType, String selector)
    {
        this.element = new ArrayList<String>();
        this.element.add(selector);
        this.action = selectorType;
    }

    /// <summary>
    /// A constructor based off of a list of elements
    /// </summary>
    /// <param name="element">A list of names of elements to interact with</param>
    public PageElement(List<String> element)
    {
        this.element = element;
        action = XPATH;
    }

    /// <summary>
    /// Finds and grabs a web element in the IWebDriver based off of what is
    /// given for the action
    /// </summary>
    /// <param name="driver"></param>
    /// <returns></returns>
    public WebElement getElement(WebDriver driver)
    {
        try
        {
            WebElement query = null;

            //find the element with the method corresponding to the action integer
            switch (action)
            {
                case ID:
                    query = driver.findElement(By.id(element.get(0)));
                    break;
                case CSS:
                    query = driver.findElement(By.cssSelector(element.get(0)));
                    break;
                case LINK_TEXT:
                    query = driver.findElement(By.linkText(element.get(0)));
                    break;
                case XPATH:
                    query = driver.findElement(By.xpath(element.get(0)));
                    break;
                default:
                    break;

            }
            return query;
        }
        catch (Exception e)
        {
        	return null;
        }
    }

    /// <summary>
    /// Gets the XPath path for the current element
    /// </summary>
    /// <returns>A string path</returns>
    public String getXPath()
    {
        String temp = "//";

        for (int i = 0; i < element.size(); i++)
        {
            switch (i)
            {
                case 0:
                    temp += element.get(i) + "[";
                    break;
                case 1:
                    temp += element.get(i) + "='";
                    break;
                case 2:
                    temp += element.get(i) + "'";
                    break;
                default:
                    temp += "and " + element.get(i) + "='" + element.get(i) + "'";
                    i++;
                    break;
            }
        }
        temp += "]";
        return temp;
    }

    /// <summary>
    /// Gets the textual output of the elements
    /// </summary>
    /// <returns>A string representing the elements being interacted with</returns>
    public String getOutput()
    {
        String str = "";

        switch (action)
        {
            case ID:
                str = "id=" + element.get(0);
                break;
            case LINK_TEXT:
                str = "linkText=" + element.get(0);
                break;
            case XPATH:
                str = "xPath=" + getXPath();
                break;
            default:
                str = "Unknown PageElement";
                break;
        }
        return str + ": ";
    }
}
