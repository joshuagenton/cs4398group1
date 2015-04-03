package atm.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import atm.controller.ATMController;
import atm.controller.Controller;
import atm.controller.MainController;
import atm.model.Model;
import atm.model.ModelEvent;

public class Misc extends JFrameView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Handler handler = new Handler();
	
	public Misc(Model model, Controller controller) {
		super(model, controller);
	}

	public void logout(){
		setBackground(new Color(0, 0, 255));
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
	public void waiting(){
		setBackground(new Color(0, 0, 255));
		setLayout(new BorderLayout(0, 0));
		
		Label label = new Label("Please Wait...");
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 24));
		label.setForeground(new Color(255, 0, 0));
		add(label, BorderLayout.CENTER);
		setVisible(true);
		repaint();
	}

	@Override
	public void modelChanged(ModelEvent me) {
		// TODO Auto-generated method stub
		
	}


	private class Handler implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			((MainController)getController()).operation(evt.getActionCommand());
			System.out.println(evt.getActionCommand());
		}
	}


}
