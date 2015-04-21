package atm.controller;

import atm.model.*;
import atm.view.*;

/**
 * This is the the standard AbstractController for the MVC format.  It provides
 * the basic functions for all the other controllers.
 * 
 * @author Stacie.Christensen
 * @since 2015-03-05
 */
public abstract class AbstractController implements Controller {

	private View view;
	private Model model;
	
	public void setModel(Model model){
		this.model = model;
	}
	
	public Model getModel(){
		return model;
	}
	
	public View getView(){
		return view;
	}
	
	public void setView(View view){
		this.view = view;
	}
}
