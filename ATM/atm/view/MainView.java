package atm.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import atm.controller.ATMController;
import atm.controller.Controller;
import atm.model.AbstractModel;
import atm.model.AgentStatus;
import atm.model.Model;
import atm.model.ModelEvent;
import atm.model.ATMCoreModel;
import atm.model.ModelListener;

public class MainView extends JFrame implements View, ModelListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Model model;
	private Controller controller;
	LoginView login;
	/**
	 * Constructor for JFrameView
	 * @param model - model to be set to view.
	 * @param controller controller to be set to view.
	 */
	private JPanel frame;
	public MainView (Model model, Controller controller){
		super();
		initialize();
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setModel(model);
		setController(controller);
		((ATMController)getController()).operation("Start");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JPanel();
		frame.setBounds(100, 100, 450, 300);
	}
	PINScreen PIN;
	public void modelChanged(ModelEvent me) {
		this.getContentPane().removeAll();
		System.out.println("CHANGE TO VIEW: " + me.getAgStatus());
		if (me.getAgStatus() == AgentStatus.Start){
			login = new LoginView(getModel(), getController());
			add(login);		
		}
		else if (me.getAgStatus() == AgentStatus.NeedPIN){
			PIN = new PINScreen(getModel(), getController());
			add(PIN);
		}
		else if (me.getAgStatus() == AgentStatus.Cancel){
			Misc cancel = new Misc(getModel(), getController());
			cancel.logout();
			add(cancel);
		}
		else if (me.getAgStatus() == AgentStatus.Wait){
			Misc wait = new Misc(getModel(), getController());
			wait.waiting();
			add(wait);
			System.out.println("Waiting");
		}
		else if (me.getAgStatus() == AgentStatus.SelectFromAccount){
			add(new AccountSelectView(getModel(), getController()));
		}
		this.revalidate();
		this.repaint();
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
		registerWithModel();
	}
	/**
	 * Launch the application.
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