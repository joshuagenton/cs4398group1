package atm.view;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import atm.controller.ATMController;
import atm.controller.Controller;
import atm.controller.IdleTimeController;
import atm.controller.Results;
import atm.model.ATMCoreModel;
import atm.model.Model;
import atm.model.ModelEvent;

import javax.swing.JLabel;


public class AccountSelectView extends JFrameView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Handler handler = new Handler();
	
	public AccountSelectView(Model model, Controller controller) {
		super(model, controller);
		setLayout(null);
		

		start();
	}
	
	ArrayList<JButton> accountButtons;
	
	public void start(){
		//this.setExtendedState(MAXIMIZED_BOTH);
		//JPanel login = new JPanel();
		//setBackground(new Color(122, 58, 255));
		setSize(new Dimension(1002, 679));
		//this.setTitle("Welcome");
		addButtons();
		setVisible(true);
	
	}

	public void addButtons (){
		Set<Results> accounts = ((ATMCoreModel)getModel()).getAccounts();
		UIManager.put("Button.disabled",UIManager.get("Button.enabled"));
		System.out.println("SIZE: " + accounts.size());
		int i = 0;
		for(final Results a : accounts){
			JButton Account = new JButton();
			Account.setPreferredSize(new Dimension(200, 200));
			Account.setFont(new Font("Dialog", Font.BOLD, 14));
			Account.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			Account.setHorizontalAlignment(SwingConstants.LEFT);
			Account.setText("<html>" + a.getName() +"<br>$"+ a.getBalance() +"</html>");
			Account.setFont(new Font("Tahoma", Font.PLAIN, 22));
			Account.setActionCommand("SelectAccount");
			Account.addActionListener(handler);
			Account.setBounds(new Rectangle(i%2*300+350, (i/2)* 300+150, 250, 150));
			Account.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					((ATMController)getController()).SetFromAccount(a);
					IdleTimeController.runTimer((ATMController)getController());
				}
			});
			i++;
			if(a == ((ATMCoreModel)getModel()).getFromAccount()) Account.setEnabled(false);
			add(Account);
			if (((ATMCoreModel)getModel()).getTransType() == null){
				Account.setEnabled(false);

				
			}
		}
		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTransfer.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnTransfer.setBounds(49, 232, 200, 200);
		btnTransfer.addActionListener(handler);
		setLayout(null);
		btnTransfer.setActionCommand("Transfer");
		add(btnTransfer);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(Color.RED);
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnCancel.setBounds(1025, 537, 275, 106);
		btnCancel.addActionListener(handler);
		setLayout(null);
		btnCancel.setActionCommand("Cancel");
		add(btnCancel);
		/*
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(handler);
		btnDeposit.setActionCommand("Deposit");
		GridBagConstraints gbc_btnDeposit = new GridBagConstraints();
		gbc_btnDeposit.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnDeposit.insets = new Insets(0, 0, 5, 0);
		gbc_btnDeposit.gridx = 4;
		gbc_btnDeposit.gridy = 2;
		add(btnDeposit, gbc_btnDeposit);
		*/
		
		JButton btnWithdraw = new JButton("");
		btnWithdraw.setIcon(new ImageIcon(TransactionTypeView.class.getResource("/atm/view/button_Withdraw.png")));
		btnWithdraw.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnWithdraw.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnWithdraw.setBounds(49, 443, 200, 200);
		btnWithdraw.addActionListener(handler);
		btnWithdraw.setActionCommand("Withdraw");
		add(btnWithdraw);
	}
	
	public void SelectFrom(){
		JLabel lblSelectFromAccount = new JLabel("Select Account");
		
		lblSelectFromAccount.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 24));
		lblSelectFromAccount.setForeground(new Color(255, 0, 0));
		lblSelectFromAccount.setBounds(500, 27, 300, 50);
		add(lblSelectFromAccount);
	}
	
	public void SelectTo(){
		JLabel lblSelectFromAccount = new JLabel("Select the account to transfer to");
		
		lblSelectFromAccount.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 24));
		lblSelectFromAccount.setForeground(new Color(255, 0, 0));
		lblSelectFromAccount.setBounds(500, 27, 450, 50);
		add(lblSelectFromAccount);
	}
	@Override
	public void modelChanged(ModelEvent me) {
		// TODO Auto-generated method stub
		
	}

	private class Handler implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			((ATMController)getController()).operation(evt.getActionCommand());
			System.out.println(evt.getActionCommand());
		}
	}
}
