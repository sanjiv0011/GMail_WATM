package projectUtility;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;


public class Generic_Method_ToSelect_Boostrape_Dropdown 
{	
	//VARIABLE AND CONSTRUCTER DECLARATIONS
	public static final Logger logger = LogManager.getLogger(TimePicker.class);

		
    public static void selectOptionFromDropdown(List<WebElement> options, String value)
    {   logger.info("Enter inside method selectOptionFromDropdown");
    	boolean flag = false;
    	for(WebElement element : options)
    	{	
    		if(element.getText().equals(value))
    		{  	logger.info("matched list value"+element.getText());	
    			element.click();
    			flag = true;
    			break;
    		}
    		
    	}
    
    	if(flag == false) {
    		logger.info("Given value not selected from the dropdown: "+value);
    	}
    	
    }
}

