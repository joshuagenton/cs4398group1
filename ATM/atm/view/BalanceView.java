package atm.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import atm.controller.ATMController;
import atm.controller.Controller;
import atm.controller.Results;
import atm.model.ATMCoreModel;
import atm.model.Model;
import atm.model.ModelEvent;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class BalanceView extends JFrameView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Handler handler = new Handler();
	public BalanceView(Model model, Controller controller) {
		super(model, controller);
		initGUI();
	}
	private void initGUI() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		//this.setExtendedState(MAXIMIZED_BOTH);
		//JPanel login = new JPanel();
		//setBackground(new Color(122, 58, 255));
		setSize(new Dimension(894, 441));
		
		Results account = ((ATMCoreModel)getModel()).getFromAccount();
		DecimalFormat dec = new DecimalFormat("'$'0.00");
		JTextField lblAccount = new JTextField("Account: " + account.getName());
		lblAccount.setEditable(false);
		lblAccount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAccount.setBorder(null);
		lblAccount.setForeground(new Color(0, 128, 0));
		lblAccount.setBackground(new Color(255, 255, 255));
		lblAccount.setBounds(500, 81, 400, 50);
		add(lblAccount);
		
		JLabel lblBalance = new JLabel("Balance: " + dec.format(account.getBalance()));
		lblBalance.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBalance.setBounds(500, 133, 400, 50);
		add(lblBalance);
		
		JButton btnOk = new JButton("YES");
		btnOk.setBackground(new Color(30, 144, 255));
		btnOk.setActionCommand("newTransaction");
		btnOk.addActionListener(handler);
		btnOk.setBounds(500, 227, 100, 100);
		add(btnOk);
		
		JLabel lblWouldYouLike = new JLabel("Would you like to peform another transaction?");
		lblWouldYouLike.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblWouldYouLike.setBounds(90, 253, 400, 48);
		
		add(lblWouldYouLike);
		
		JButton btnNo = new JButton("No");
		btnNo.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), new Color(0, 0, 0), null, null));
		btnNo.setForeground(Color.BLACK);
		btnNo.setBackground(Color.RED);
		btnNo.setActionCommand("Logout");
		btnNo.addActionListener(handler);
		btnNo.setBounds(602, 227, 100, 100);
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
