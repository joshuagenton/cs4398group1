package atm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import atm.controller.ATMController;
import atm.controller.Controller;
import atm.controller.MainController;
import atm.model.Model;
import atm.model.ModelEvent;

public class BalanceView extends JFrameView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BalanceView(Model model, Controller controller) {
		super(model, controller);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void modelChanged(ModelEvent me) {
		// TODO Auto-generated method stub
		
	}

	private class Handler implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			((ATMController)getController()).operation(evt.getActionCommand());
		}
	}

}
