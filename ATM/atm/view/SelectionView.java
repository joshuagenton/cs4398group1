package atm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import atm.controller.ATMController;
import atm.controller.Controller;
import atm.model.Model;
import atm.model.ModelEvent;

public class SelectionView extends JFrameView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String Transfer = "Transfer";
	public final static String Balance = "Balance";
	public final static String Withdraw = "Withdraw";
	public final static String Cancel = "Cancel";
	public final static String Enter = "Enter";
	public static final String Start = "Start";
	
	
	public SelectionView(Model model, Controller controller) {
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
