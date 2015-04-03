package atm.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Map.Entry;

import atm.controller.ATMController;
import atm.controller.Controller;
import atm.controller.MainController;
import atm.model.ATMCoreModel;
import atm.model.Model;
import atm.model.ModelEvent;

import javax.swing.JLabel;

public class BalanceView extends JFrameView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BalanceView(Model model, Controller controller) {
		super(model, controller);
		setLayout(null);
		
		//this.setExtendedState(MAXIMIZED_BOTH);
		//JPanel login = new JPanel();
		setBackground(new Color(122, 58, 255));
		setSize(new Dimension(527, 265));
		

		
		//this.setTitle("Welcome");
		setVisible(true);
		
		Entry<String, Double> account = ((ATMCoreModel)getModel()).getFromAccount();
		DecimalFormat dec = new DecimalFormat("'$'0.00");
		JLabel lblAccount = new JLabel("Account: " + account.getKey());
		lblAccount.setBounds(500, 81, 400, 50);
		add(lblAccount);
		
		JLabel lblBalance = new JLabel("Balance: " + dec.format(account.getValue()));
		lblBalance.setBounds(500, 133, 400, 50);
		add(lblBalance);
		start();
	}
	
	public void start(){

		
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
