package simpleAccount.controller;
import simpleAccount.model.Model;
import simpleAccount.view.View;

/**
 * This is the the standard interface Controller for the MVC format.  It provides
 * the basic functions for all the other controllers.
 * 
 * @author Paul Bryson
 * @since 2015-04-05
 */
public interface Controller {
	void setModel(Model model);
	Model getModel();
	View getView();
	void setView(View view);
}
