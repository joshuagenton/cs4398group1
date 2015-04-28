package simpleAccount.view;
import javax.swing.*;
import simpleAccount.model.Model;
import simpleAccount.model.AbstractModel;
import simpleAccount.model.ModelListener;
import simpleAccount.controller.Controller;


/**
 * This is the standard abstract JFrameView class for the MVC setup. 
 * This contains the basics for the views.
 * 
 * @author Paul Bryson
 * @since 2015-04-05
 */
abstract public class JFrameView extends JFrame implements View, ModelListener {
	private Model model;
	private Controller controller;
	
	public JFrameView (Model model, Controller controller){
		setModel(model);
		setController(controller);
	}
	
	public void registerWithModel(){
		((AbstractModel)model).addModelListener(this);
	}
	
	public void unregisterWithModel(){
		((AbstractModel)model).removeModelListener(this);
	}
	
	public Controller getController(){return controller;}
	
	public void setController(Controller controller){this.controller = controller;}
	
	public Model getModel(){return model;}
	
	public void setModel(Model model) {
		this.model = model;
		registerWithModel();
	}
	
}
