package atm.view;

import atm.controller.ATMController;
import atm.controller.Controller;
import atm.model.Model;
import atm.model.ModelEvent;

import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionTypeView extends JFrameView {


	private static final long serialVersionUID = 1L;
	private Handler handler = new Handler();
	
	public TransactionTypeView(Model model, Controller controller) {
		super(model, controller);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{56, 79, 73, 69, 101, 0};
		gridBagLayout.rowHeights = new int[]{23, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.addActionListener(handler);
		btnTransfer.setActionCommand("Transfer");
		GridBagConstraints gbc_btnTransfer = new GridBagConstraints();
		gbc_btnTransfer.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnTransfer.insets = new Insets(0, 0, 5, 5);
		gbc_btnTransfer.gridx = 2;
		gbc_btnTransfer.gridy = 2;
		add(btnTransfer, gbc_btnTransfer);
		
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
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(handler);
		btnWithdraw.setActionCommand("Withdraw");
		GridBagConstraints gbc_btnWithdraw = new GridBagConstraints();
		gbc_btnWithdraw.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnWithdraw.insets = new Insets(0, 0, 0, 5);
		gbc_btnWithdraw.gridx = 2;
		gbc_btnWithdraw.gridy = 4;
		add(btnWithdraw, gbc_btnWithdraw);
		
		JButton btnCheckBalance = new JButton("Check Balance");
		btnCheckBalance.addActionListener(handler);
		btnCheckBalance.setActionCommand("Balance");
		GridBagConstraints gbc_btnCheckBalance = new GridBagConstraints();
		gbc_btnCheckBalance.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnCheckBalance.gridx = 4;
		gbc_btnCheckBalance.gridy = 4;
		add(btnCheckBalance, gbc_btnCheckBalance);
		// TODO Auto-generated constructor stub
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
