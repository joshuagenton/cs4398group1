package atm.view;

import atm.controller.Controller;
import atm.model.Model;
import atm.model.ModelEvent;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.Font;

/**
 * The LogoutView is the view the user sees when logging out of the system.
 * 
 * @author Chris Wells
 * @since 2015-03-25
 */
public class LogoutView extends JFrameView {

	private static final long serialVersionUID = 1L;

	/**
	 * Controller.
	 * 
	 * @param model the ATMCoreModel
	 * @param controller the ATMController
	 */
	public LogoutView(Model model, Controller controller) {
		super(model, controller);
		setLayout(new BorderLayout(0, 0));
		
		Label label = new Label("You have been logged out");
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 24));
		label.setForeground(new Color(255, 0, 0));
		add(label, BorderLayout.CENTER);
	}

	/**
	 * modelChanged -- Do nothing
	 */
	@Override
	public void modelChanged(ModelEvent me) {}

}
