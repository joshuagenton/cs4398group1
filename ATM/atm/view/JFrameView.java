package atm.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import atm.controller.*;
import atm.model.*;

public abstract class JFrameView extends JPanel implements View, ModelListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Model model;
	private Controller controller;
	/**
	 * Constructor for JFrameView
	 * @param model - model to be set to view.
	 * @param controller controller to be set to view.
	 */
	public JFrameView (Model model, Controller controller){
		setModel(model);
		setController(controller);
	}
	/**
	 * registers the view with the model.
	 */
	public void registerWithModel(){
		((AbstractModel)model).addModelListener(this);
	}
	public void unregisterWithModel(){
		((AbstractModel)model).removeModelListener(this);
	}
	/**
	 * getter for the view's controller.
	 */
	public Controller getController(){return controller;}
	
	/**
	 * setter for the view's controller.
	 */
	public void setController(Controller controller){
		this.controller = controller;
	}
	/**
	 * getter for the view's model.
	 */
	public Model getModel(){return model;}
	
	/**
	 * setter for the view's model.
	 */
	public void setModel(Model model) {
		this.model = model;
		//registerWithModel();
	}
	
	@Override
	  protected void paintComponent(Graphics g) {
		Image background = Toolkit.getDefaultToolkit().createImage("atm/view/ATM.jpg");
	    super.paintComponent(g);
	        g.drawImage(background, 0, 0, null);
	}

}
