package atm.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import atm.controller.*;
import atm.model.*;

/**
 * This is the standard abstract JFrameView class for the MVC setup. 
 * This contains the basics for the views.
 * 
 * @author Stacie.Christensen
 * @since 2015-03-05
 */
public abstract class JFrameView extends JPanel implements View, ModelListener {

	private static final long serialVersionUID = 1L;
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
	

	public void setController(Controller controller){
		this.controller = controller;
	}

	public Model getModel() {
		return model;
	}
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	@Override
	  protected void paintComponent(Graphics g) {
		Image background = Toolkit.getDefaultToolkit().createImage("atm/view/ATM.jpg");
	    super.paintComponent(g);
	        g.drawImage(background, 0, 0, null);
	}

}
