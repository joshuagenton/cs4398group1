package atm.controller;

import atm.model.*;
import atm.view.*;

/**
 * This is the the standard interface Controller for the MVC format.  It provides
 * the basic functions for all the other controllers.
 * 
 * @author Stacie.Christensen
 * @since 2015-03-05
 */

public interface Controller {
	void setModel(Model model);
	void setView(View view);
	
	Model getModel();
	View getView();
	
}
