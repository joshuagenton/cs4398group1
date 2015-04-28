package simpleAccount.view;
import simpleAccount.model.Model;
import simpleAccount.controller.Controller;


/**
 * This is the standard interface View for the MVC setup.  It contains
 * the basics for the views.
 * 
 * @author Paul Bryson
 * @since 2015-04-05
 */
public interface View {
	Controller getController();
	void setController(Controller controller);
	Model getModel();
	void setModel(Model model);
}
