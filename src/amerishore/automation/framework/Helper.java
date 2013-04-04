package amerishore.automation.framework;

import java.util.ArrayList;
import java.util.List;
import com.extentech.ExtenXLS.*;

public class Helper {
	
	public static void main(String[] args){
		ArrayList<TestCase> cases = parseTestCaseSpreadSheet("cases.xls");
		System.out.println(cases.size());
		if(cases.get(0) == null)
			System.out.println("null");
		for(TestCase c : cases)
			if(c != null)
				System.out.println(c.getID() + " , " + c.getTitle());
	}

	//This command means there is a preset command to run
    public final static String PRESET = "!&";

    //Seperates parts of commands and determines end of a single test case step
    public final static String SEPERATOR = "::";

    //Denotes a command
    public final static String COMMAND = "#";

    //Denotes an expectation / error message
    public final static String EXPECTATION = "%%";

    //Denotes the target of the error message
    public final static String TARGET = "->";

    /// <summary>
    /// This checks to see if a given string is a number or not
    /// </summary>
    /// <param name="s">The string being checked</param>
    /// <returns>True if a number, else false</returns>
    public static boolean isNumeric(String s)
    {
        for(char c : s.toCharArray())
        {
            if (!Character.isDigit(c))
            {
                return false;
            }
        }

        return true;
    }

    /// <summary>
    /// This checks to see if a string contains a digit
    /// </summary>
    /// <param name="s">String being checked</param>
    /// <returns>True if contains digit, else false</returns>
    public static boolean containsDigit(String s)
    {
        for(char c : s.toCharArray())
        {
            if (Character.isDigit(c))
            {
                return true;
            }
        }
        return false;
    }

    /// <summary>
    /// Adds a new element to a list of given elements
    /// </summary>
    /// <param name="elements">The list of PageElements being added to</param>
    /// <param name="selectorType">The type of the new element</param>
    /// <param name="selector">The selector of the new element</param>
    protected static void addElement(List<PageElement> elements, String selectorType, String selector)
    {
        PageElement ele = new PageElement(PageElement.CSS, selector);
//        switch (selectorType.toLowerCase())
//        {
//            case "css":
//                ele = new PageElement(PageElement.CSS, selector);
//                break;
//            case "linktext":
//                ele = new PageElement(PageElement.LINK_TEXT, selector);
//                break;
//            case "id":
//                ele = new PageElement(selector);
//                break;
//            case "xpath":
//                ele = new PageElement(PageElement.XPATH, selector);
//                break;
//            default:
//                break;
//
//        }
        elements.add(ele);
    }

    /// <summary>
    /// Adds a new action to a list of actions
    /// </summary>
    /// <param name="actions">The list being added to</param>
    /// <param name="action">The action getting added</param>
    protected static void addAction(List<PageAction> actions, String action)
    {
        PageAction act = null;
        String potKeys = null;

        //check if there is a -> pointer in there
        if (action.contains(TARGET))
        {
            potKeys = action.substring(action.indexOf(TARGET) + 2);
            action = action.substring(0, action.indexOf(TARGET));
        }

//        switch (action.toLowerCase())
//        {
//            case "click":
//                act = new PageAction(PageAction.CLICK);
//                break;
//            case "submit":
//                act = new PageAction(PageAction.SUBMIT);
//                break;
//            case "sendkeys":
//                act = new PageAction(potKeys, true);
//                break;
//            default:
//                break;
//        }
        //actions.add(act);
        actions.add(new PageAction("fdsa", false));
    }

    //////////////////////////////********************************************************************************************/////////////////////////////////////////////

	/*Parser Methods*/
    /// <summary>
    /// Creates an entire collection of test cases from a given excel spreadsheet
    /// Column 1) Test case id
    /// Column 2) Test case name
    /// Column 3) Test case steps
    /// </summary>
    /// <param name="filePath">Filepath of excel sheet</param>
    /// <param name="config">Chosen configuration</param>
    /// <returns>TestCaseCollection of test cases in Excel sheet</returns>
    public static ArrayList<TestCase> parseTestCaseSpreadSheet(String filePath)
    {
    	ArrayList<TestCase> cases = new ArrayList<TestCase>();
    	WorkBookHandle book = null;
    	try{
    		book = new WorkBookHandle(filePath);
    	}catch(Exception e){
    		System.out.println("Couldn't read the excel file");
    	}

    	try{
    		WorkSheetHandle wsh = null;
    		for(int i = 0; i < book.getNumWorkSheets(); i++){	
	    		wsh = book.getWorkSheet(i);
	    		if(wsh != null){
		    		RowHandle[] rows = wsh.getRows();
		    		for(int r = 1; r < rows.length; r++){
		    			CellHandle[] cells = rows[r].getCells();
		    			try{
		    				System.out.println(cells[0].getStringVal());
							String id = cells[0].getStringVal();
							String title = cells[1].getStringVal();
							String steps = cells[2].getStringVal();
							cases.add(parseTestCase(title, id, steps));
		    			}catch(Exception e){
		    				System.out.println("row " + r + " doesn't contain the appropriate fields");
		    			}
		    		}
	    		}
    		}
    	}catch(Exception e){ }

    	//TODO look into creating a testcasecollection class
    	return cases;
    }

    /// <summary>
    /// Creates a single test case from a set of strings
    /// </summary>
    /// <param name="title">String title of test case</param>
    /// <param name="id">String id of test case</param>
    /// <param name="steps">String steps of test case</param>
    /// <param name="config">Configuration file being used</param>
    /// <returns>A new TestCase if successful, null if unsuccessful</returns>
    public static TestCase parseTestCase(String title, String id, String steps)
    {
        try
        {
            //Create new test case
            TestCase testCase = new TestCase(title, id);

            //while we still have a SEPERATOR!!, init some variables
            List<PageElement> elements = new ArrayList<PageElement>();
            List<PageAction> actions = new ArrayList<PageAction>();
            Expectation exp = null;

            //While there is still more information in steps
            while (steps.contains(SEPERATOR))
            {
                /*
                * 1.get the description
                * 2.check the steps for predefined commands
                * 3.get the selector type
                * 4.get the selector
                * 5.get the action
                * 6.check/get expectation
                */
                String description = steps.substring(0, steps.indexOf(COMMAND));
                steps = steps.substring(steps.indexOf(COMMAND) + 1);

                StringBuilder builderSteps;
                builderSteps = new StringBuilder(steps);

                //if there are no presets in the COMMAND
                //if (!checkPSets(builderSteps, elements, actions, config))
               // {
                    String selectorType = steps.substring(0, steps.indexOf(SEPERATOR));
                    steps = steps.substring(steps.indexOf(SEPERATOR) + 2);

                    String selector = steps.substring(0, steps.indexOf(SEPERATOR));
                    steps = steps.substring(steps.indexOf(SEPERATOR) + 2);

                    String action = steps.substring(0, steps.indexOf(SEPERATOR));
                    steps = steps.substring(steps.indexOf(SEPERATOR) + 2);

                    addElement(elements, selectorType, selector);
                    addAction(actions, action);
               // }
               // else
               // {
                    //steps = builderSteps.toString().substring(builderSteps.toString().indexOf(SEPERATOR) + SEPERATOR.length());
               // }

                //Create expectation
                String expKey = null;
                String expTarget = null;
                if (!steps.contains(COMMAND) && steps.contains(EXPECTATION))
                {
                    if (steps.contains(TARGET))
                    {
                        expKey = steps.substring(steps.indexOf(EXPECTATION) + 2, steps.indexOf(TARGET) - 3);
                        expTarget = steps.substring(steps.indexOf(TARGET) + 2);
                    }
                    else
                    {
                        expKey = steps.substring(steps.indexOf(EXPECTATION) + 2);
                        expTarget = "";
                    }
                    steps = "";
                }
                exp = new Expectation(expKey, expTarget, true);
            }

            //Add actions, elements, and expectations to testcase
            testCase.addActions(actions);
            testCase.addElements(elements);
            testCase.addExpectation(exp);

            return testCase;
        }
        catch (Exception e)
        {
            System.out.println("something bad happened");
            return null;
        }
    }
}
