package atm.view;

import java.awt.Dimension;
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
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double width = screenSize.getWidth();
	double height = screenSize.getHeight();
	public FixLabelSize fixLabel = new FixLabelSize();
	
	public JFrameView (Model model, Controller controller){
		setModel(model);
		setController(controller);
		setOpaque(false);
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


}
