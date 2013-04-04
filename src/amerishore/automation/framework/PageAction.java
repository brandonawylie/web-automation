package amerishore.automation.framework;

import org.openqa.selenium.WebElement;

public class PageAction {
        //integer keys that correspond to actions
        public final static int SENDKEYS = 0;
        public final static int CLICK = 1;
        public final static int SUBMIT = 2;
        public final static int KEYPRESS = 3;

        //the integer representation of what action to take
        private int action;

        //The string that will be sent (if any)
        private String strSend;

        //The keypress that will be sent (if any)
        private String keySend;

        //create an action from string
        //boolean should be true if this is a keypress, false if this is a SendKeys
        /// <summary>
        /// Creates an action from a given string
        /// Boolean should be true if this is a keypress, false if this is a sendKeys
        /// </summary>
        /// <param name="strSend">The string being sent to the selected element</param>
        /// <param name="keyPress">True if a keypress, false if a sendkeys</param>
        public PageAction(String strSend, boolean keyPress)
        {
            if (!keyPress)
            {
                this.strSend = strSend;
                action = SENDKEYS;
            }
            else
            {
                this.keySend = strSend;
                action = KEYPRESS;
            }
        }

        /// <summary>
        /// Constructor that creates an action from an int
        /// </summary>
        /// <param name="action">The integer representation of the action to take</param>
        public PageAction(int action)
        {
            this.action = action;
            strSend = "";
        }

        /// <summary>
        /// Runs the given action in the IWebElement
        /// </summary>
        /// <param name="query"></param>
        /// <returns></returns>
        public String runAction(WebElement query)
        {
            String errorString = "";

            //execute the action according to the action integer.
            switch (action)
            {
                case SENDKEYS:
                    query.sendKeys(strSend);
                    errorString = "sending keys: " + strSend + "\n";
                    break;
                case CLICK:
                    query.click();
                    errorString = "clicking element\n";
                    break;
                case SUBMIT:
                    query.submit();
                    errorString = "submitting\n";
                    break;
                case KEYPRESS:
                    query.sendKeys(keySend);
                    break;
                default:
                    errorString = action + " not recognized\n";
                    break;
            }
            return errorString;
        }

        /// <summary>
        /// Gets a string representation of the action
        /// </summary>
        /// <returns>A string reperesentation of what action to take</returns>
        public String getAction()
        {
            String errorString = "";
            //execute the action according to the action integer.
            switch (action)
            {
                case SENDKEYS:
                    return "sendKeys";
                case CLICK:
                    return "click";
                case SUBMIT:
                    return "submit";
                case KEYPRESS:
                    return "keyPress";
                default:
                    return action + " not recognized";
	            }
	        }
}
