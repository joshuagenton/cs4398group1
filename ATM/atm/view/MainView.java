package atm.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import atm.controller.ATMController;
import atm.controller.Controller;
import atm.model.AbstractModel;
import atm.model.AgentStatus;
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
		setModel(model);
		setController(controller);
		//add(new Background());
		((ATMController)getController()).operation("Start");
	}

	JButton b1;
	JLabel l1;
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//this.setBounds(100, 100, 450, 300);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image img = new ImageIcon(this.getClass().getResource("/atm/view/ATM.jpg")).getImage();
		BackgroundPanel panel = new BackgroundPanel(img);
		setContentPane(panel);
	}
	
	PINScreen PIN;
	
	/**
	 * Takes care of the model is there is a change.
	 */
	public void modelChanged(ModelEvent me) {
		this.getContentPane().removeAll();
		System.out.println("CHANGE TO VIEW: " + me.getAgStatus());
		if (me.getAgStatus() == AgentStatus.Start){
			login = new LoginView(getModel(), getController());
			getContentPane().add(login);		
		}
		else if (me.getAgStatus() == AgentStatus.NeedPIN){
			PIN = new PINScreen(getModel(), getController());
			getContentPane().add(PIN);
		}
		else if (me.getAgStatus() == AgentStatus.Cancel){
			Misc cancel = new Misc(getModel(), getController());
			cancel.logout();
			getContentPane().add(cancel);
		}
		else if (me.getAgStatus() == AgentStatus.Wait){
			Misc wait = new Misc(getModel(), getController());
			wait.waiting();
			getContentPane().add(wait);
		}
		else if (me.getAgStatus() == AgentStatus.InvalidPIN){
			Misc misc = new Misc(getModel(), getController());
			misc.invalidPIN();
			getContentPane().add(misc);
			this.revalidate();
			this.repaint();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			((ATMController)getController()).operation("newTransaction");
		}
		else if (me.getAgStatus() == AgentStatus.Verified){
			getContentPane().add(new AccountSelectView(getModel(), getController()));
		}
		else if (me.getAgStatus() == AgentStatus.SelectFromAccount){
			AccountSelectView account = new AccountSelectView(getModel(), getController());
			account.SelectFrom();
			getContentPane().add(account);
		}
		else if (me.getAgStatus() == AgentStatus.SelectToAccount){
			AccountSelectView account = new AccountSelectView(getModel(), getController());
			account.SelectTo();
			getContentPane().add(account);
		}
		else if (me.getAgStatus() == AgentStatus.CheckBalance){
			BalanceView balance = new BalanceView(getModel(), getController());
			getContentPane().add(balance);
		}
		else if (me.getAgStatus() == AgentStatus.Withdraw) {
			getContentPane().add(new AmountView(getModel(), getController()));
		}
		else if (me.getAgStatus() == AgentStatus.WithdrawComplete){
			getContentPane().add (new WithdrawView(getModel(), getController()));
		}
		else if (me.getAgStatus() == AgentStatus.Transfer){
			getContentPane().add (new AmountView(getModel(), getController()));
		}
		else if (me.getAgStatus() == AgentStatus.InsufFunds){
			Misc misc = new Misc(getModel(), getController());
			misc.insufFunds();
			getContentPane().add(misc);

			((ATMController)getController()).operation("logout");
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
