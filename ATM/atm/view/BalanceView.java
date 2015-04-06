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
import atm.controller.Results;
import atm.model.ATMCoreModel;
import atm.model.Model;
import atm.model.ModelEvent;

import javax.swing.JLabel;
import javax.swing.JButton;

public class BalanceView extends JFrameView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Handler handler = new Handler();
	public BalanceView(Model model, Controller controller) {
		super(model, controller);
		setLayout(null);
		
		//this.setExtendedState(MAXIMIZED_BOTH);
		//JPanel login = new JPanel();
		//setBackground(new Color(122, 58, 255));
		setSize(new Dimension(894, 441));
		

		
		//this.setTitle("Welcome");
		setVisible(true);
		
		Results account = ((ATMCoreModel)getModel()).getFromAccount();
		DecimalFormat dec = new DecimalFormat("'$'0.00");
		JLabel lblAccount = new JLabel("Account: " + account.getName());
		lblAccount.setBounds(500, 81, 400, 50);
		add(lblAccount);
		
		JLabel lblBalance = new JLabel("Balance: " + dec.format(account.getBalance()));
		lblBalance.setBounds(500, 133, 400, 50);
		add(lblBalance);
		
		JButton btnOk = new JButton("YES");
		btnOk.setActionCommand("newTransaction");
		btnOk.addActionListener(handler);
		btnOk.setBounds(500, 227, 89, 23);
		add(btnOk);
		
		JLabel lblWouldYouLike = new JLabel("Would you like to peform another transaction?");
		lblWouldYouLike.setBounds(210, 231, 268, 14);
		
		add(lblWouldYouLike);
		
		JButton btnNo = new JButton("No");
		btnNo.setActionCommand("Logout");
		btnNo.addActionListener(handler);
		btnNo.setBounds(618, 227, 89, 23);
		add(btnNo);
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
