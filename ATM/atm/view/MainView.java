package atm.view;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import atm.controller.ATMController;
import atm.controller.Controller;
import atm.model.AbstractModel;
import atm.model.Model;
import atm.model.ModelEvent;
import atm.model.ATMCoreModel;
import atm.model.ModelListener;


/**
 * The MainView the user sees when using the application.
 * 
 * @author Chris Wells
 * @since 2015-03-25
 */
public class MainView extends JFrame implements View, ModelListener{

	private static final long serialVersionUID = 1L;
	private Model model;
	private Controller controller;
	LoginView login;
	private JPanel frame;
	
	/**
	 * Constructor for JFrameView
	 * @param model - model to be set to view.
	 * @param controller controller to be set to view.
	 */
	public MainView (Model model, Controller controller){
		super();

		initialize();
		this.setBounds(100, 100, 450, 300);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setModel(model);
		setController(controller);
		((ATMController)getController()).operation("Start");
	}
	
	JButton b1;
	JLabel l1;
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JPanel();
		frame.setBounds(100, 100, 450, 300);
	}
	
	PINScreen PIN;
	
	/**
	 * Takes care of the model is there is a change.
	 */
	public void modelChanged(ModelEvent me) {
		this.getContentPane().removeAll();

		this.revalidate();
		this.repaint();
	}
	
	/**
	 * registers the view with the model.
	 */
	public void registerWithModel(){
		((AbstractModel)model).addModelListener(this);
	}
	
	/**
	 * unregisters the view with the model.
	 */
	public void unregisterWithModel(){
		((AbstractModel)model).removeModelListener(this);
	}


	// Getters/Setters
	
	public Controller getController(){
		return controller;
	}
	
	public void setController(Controller controller){
		this.controller = controller;
	}

	public Model getModel(){
		return model;
	}
	
	public void setModel(Model model) {
		this.model = model;
		registerWithModel();
	}
	
	/**
	 * Main function - Launch the application.
	 * @param args for the main function
	 */
	public static void main(String[] args) {
		final ATMCoreModel account = new ATMCoreModel();
		final ATMController contr = new ATMController();
		contr.setModel(account);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView(account, contr);
					
					window.setVisible(true);
    		    	  contr.setView(window);
			    	  window.setVisible(true);
			    	  
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
