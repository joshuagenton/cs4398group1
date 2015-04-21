package atm.view;

import atm.controller.*;
import atm.model.*;

/**
 * This is the standard interface View for the MVC setup.  It contains
 * the basics for the views.
 * 
 * @author Stacie.Christensen
 * @since 2015-03-05
 */
public interface View {
	Controller getController();
	public Model getModel();
	public void setController(Controller aController);
	public void setModel(Model aModel);
}

