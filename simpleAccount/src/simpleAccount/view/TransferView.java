package simpleAccount.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.SortedMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import simpleAccount.controller.AccountController;
import simpleAccount.model.AccountModel;
import simpleAccount.model.ModelEvent;
import simpleAccount.view.AccountView.Handler;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;


/**
 * This is the TransferView.  This is the secondary window that pops up
 * when the user selects a currency they would like to use to interact
 * with the account data.
 * 
 * @author Paul Bryson
 * @since 2015-04-05
 */
public class TransferView extends JFrameView {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField txtAvailableFunds;
	private String id = "", currency = "";
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("");
		frame.setBounds(100, 100, 463, 171);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Handler handler = new Handler();
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(handler);
		btnDeposit.setBounds(10, 99, 123, 23);
		frame.getContentPane().add(btnDeposit);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(handler);
		btnWithdraw.setBounds(143, 99, 123, 23);
		frame.getContentPane().add(btnWithdraw);
		
		JButton btnDismiss = new JButton("Dismiss");
		btnDismiss.addActionListener(handler);
		btnDismiss.setBounds(277, 99, 123, 23);
		frame.getContentPane().add(btnDismiss);
		
		txtAvailableFunds = new JTextField();
		txtAvailableFunds.setEditable(false);
		txtAvailableFunds.setText("");
		txtAvailableFunds.setBounds(143, 11, 123, 20);
		frame.getContentPane().add(txtAvailableFunds);
		txtAvailableFunds.setColumns(10);
		
		JLabel lblAvailableFunds = new JLabel("Available Funds:");
		lblAvailableFunds.setBounds(10, 14, 123, 14);
		frame.getContentPane().add(lblAvailableFunds);
		
		JLabel lblEnterAmountIn = new JLabel("Enter amount in");
		lblEnterAmountIn.setBounds(10, 45, 123, 14);
		frame.getContentPane().add(lblEnterAmountIn);
		
		NumberFormat amountFormat = NumberFormat.getNumberInstance();
		JFormattedTextField frmtdtxtfldTransferamount = new JFormattedTextField(amountFormat);
		frmtdtxtfldTransferamount.setText("0.0");
		frmtdtxtfldTransferamount.setBounds(143, 42, 123, 20);
		frame.getContentPane().add(frmtdtxtfldTransferamount);
	}

	@Override
	public void modelChanged(ModelEvent event) {
		HashMap<String, Object> account = event.getAccounts().get(Integer.parseInt(id));
		frame.setTitle(account.get("name") + " " + account.get("id") + "; Operations in " + currency);
		
		Double amount = (Double) account.get("amount");
		if (currency.equals("Euros"))
			amount = amount * 0.92;
		else if (currency.equals("Yuan"))
			amount = amount * 6.23;
		
		((JTextField) frame.getContentPane().getComponent(3)).setText(String.format("%.2f", amount));
	}
	
	/**
	 * Inner classes for Event Handling 
	 * @author Paul Bryson
	 *
	 */
	class Handler implements ActionListener { 
		// Event handling is handled locally
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Dismiss")) {
				unregisterWithModel();
				frame.setVisible(false);
			}
			else {
				Double amount = Double.parseDouble(((JTextField) frame.getContentPane().getComponent(6)).getText().toString().replace(",", ""));
				if (e.getActionCommand().equals("Withdraw"))
					amount = -amount;
				((AccountController)getController()).operationTransfer(id, currency, amount);
				((JTextField) frame.getContentPane().getComponent(6)).setText("0.0");
			}
	    }
	}
	
	/**
	 * Sets up the transfer view.
	 * @param model the AccountModel
	 * @param controller the AccountController
	 */
	public TransferView (AccountModel model, AccountController controller){
		super(model, controller);
		initialize();
		this.frame.setVisible(true);
	}
	
	/**
	 * Sets the values for the transfer
	 * @param id the accountID
	 * @param currency the currency selected
	 */
	public void setValues (String id, String currency) {
		this.id = id;
		this.currency = currency;
		((JLabel) frame.getContentPane().getComponent(5)).setText("Enter amount in " + this.currency);
	}
	
	/**
	 * This function has the sole purpose of allowing the design window to work
	 */
	public TransferView (){
		super(new AccountModel(), new AccountController());
		initialize();
		this.frame.setVisible(true);
	}
}
