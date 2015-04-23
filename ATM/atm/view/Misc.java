package atm.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import atm.controller.ATMController;
import atm.controller.Controller;
import atm.model.Model;
import atm.model.ModelEvent;

/**
 * The Misc class contains all the misc. actions the user sees on the
 * screen. 
 * 
 * @author Chris Wells
 * @since 2015-03-25
 */
public class Misc extends JFrameView {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor.
	 * 
	 * @param model the ATMCoreModel
	 * @param controller the ATMController
	 */
	public Misc(Model model, Controller controller) {
		super(model, controller);
	}

	/**
	 * The logout function logs the user out of their session.
	 */
	public void logout(){
		setLayout(new BorderLayout(0, 0));
		
		Label label = new Label("You have been logged out");
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 24));
		label.setForeground(new Color(255, 0, 0));
		add(label, BorderLayout.CENTER);
		setVisible(true);
		repaint();
		((ATMController)getController()).operation("Logout");
		
	}

	/**
	 * The system is waiting for an action from the user.
	 */
	public void waiting(){
		setLayout(new BorderLayout(0, 0));
		
		Label label = new Label("Please Wait...");
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 24));
		label.setForeground(new Color(255, 0, 0));
		add(label, BorderLayout.CENTER);
		setVisible(true);
		repaint();
	}

	/**
	 * An error that the user entered an invalid PIN for the card they swiped.
	 */
	public void invalidPIN(){
		setLayout(new BorderLayout(0, 0));
		
		Label label = new Label("Invalid PIN");
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 24));
		label.setForeground(new Color(255, 0, 0));
		add(label, BorderLayout.CENTER);
		setVisible(true);
		repaint();
	}

	/**
	 * An error that the user does not have sufficient funds for the action they
	 * are trying to perform.
	 */
	public void insufFunds(){
		setLayout(new BorderLayout(0, 0));
		
		Label label = new Label("Insufficient Funds");
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 24));
		label.setForeground(new Color(255, 0, 0));
		add(label, BorderLayout.CENTER);
		setVisible(true);
		repaint();
	}

	/**
	 * The user can cancel out of an action at any time which will log them
	 * out of the system.
	 */
	public void cancelled(){
		setLayout(new BorderLayout(0, 0));
		
		Label label = new Label("Your Transaction has\nbeen cancelled");
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 24));
		label.setForeground(new Color(255, 0, 0));
		add(label, BorderLayout.CENTER);
		setVisible(true);
		repaint();
	}
	
	public void databaseCommError(){
		setLayout(new BorderLayout(0, 0));
		
		Label label = new Label("Network Communication Error.\nPaul probably tripped over the power\nccable or something.");
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 24));
		label.setForeground(new Color(255, 0, 0));
		add(label, BorderLayout.CENTER);
		setVisible(true);
		repaint();
	}
	/**
	 * The transComplete is a successful transfer from one account to another.
	 */
	public void transComplete(){
		setLayout(new BorderLayout(0, 0));
		
		Label label = new Label("Thank you for using Texas State Bank ATM");
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 24));
		label.setForeground(new Color(255, 0, 0));
		add(label, BorderLayout.CENTER);
		setVisible(true);
		repaint();
	}
	
	/**
	 * ModelChanged -- do nothing
	 */
	@Override
	public void modelChanged(ModelEvent me) {}
}
