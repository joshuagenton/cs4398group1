package atm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import atm.controller.Controller;
import atm.controller.MainController;
import atm.model.Model;
import atm.model.ModelEvent;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.Font;

public class LogoutView extends JFrameView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public LogoutView(Model model, Controller controller) {
		super(model, controller);
		//setBackground(new Color(0, 0, 255));
		setLayout(new BorderLayout(0, 0));
		
		Label label = new Label("You have been logged out");
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 24));
		label.setForeground(new Color(255, 0, 0));
		add(label, BorderLayout.CENTER);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void modelChanged(ModelEvent me) {
		// TODO Auto-generated method stub
		
	}


	private class Handler implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			((MainController)getController()).operation(evt.getActionCommand());
		}
	}
}
