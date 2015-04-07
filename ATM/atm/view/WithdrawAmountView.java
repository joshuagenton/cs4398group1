package atm.view;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import atm.controller.ATMController;
import atm.controller.DatabaseController;
import atm.controller.CardReaderController;
import atm.controller.Controller;
import atm.controller.IdleTimeController;
import atm.model.ATMCoreModel;
import atm.model.Model;
import atm.model.ModelEvent;
import atm.model.TransactionTypes;

public class WithdrawAmountView extends JFrameView {

	private static final long serialVersionUID = 1L;

	public final static String WITHDRAW = "Withdraw";
	public final static String TRANSFER = "Transfer";
	public final static String CANCEL = "Cancel";

	
	private JPanel topPanel;
	private JPanel textPanel;
	private JPanel buttonPanel;
	
	private JLabel lblAmount;
	
	private JTextField textAmount;
	
	private JButton withdrawButton;
	private JButton cancelButton;
	
	public CardReaderController card = new CardReaderController(getModel()); 
	private Handler handler = new Handler();
	
	
	
	public WithdrawAmountView(Model model, Controller controller) {
		super(model, controller);
		
		this.setBounds(100, 100, 450, 300);
		add(getContent());
		
		
		Toolkit toolkit =  Toolkit.getDefaultToolkit();
		Dimension dim = toolkit.getScreenSize();
		
		int x = (int) ((dim.getWidth() - this.getWidth()) * 0.5f);
	    int y = (int) ((dim.getHeight() - this.getHeight()) * 0.5f);
	    this.setLocation(x, y);
	    
	}

	
	private JPanel getContent() {
		if (topPanel == null) {
			topPanel = new JPanel();
			GridLayout layout = new GridLayout(0, 1);
			topPanel.setLayout(layout);
			//topPanel.setPreferredSize(new Dimension(300, 100));
			GridBagConstraints ps = new GridBagConstraints();
			ps.gridx = 0;
			ps.gridy = 3;
			ps.fill = GridBagConstraints.HORIZONTAL;
			
			GridBagConstraints bs = new GridBagConstraints();
			bs.gridx = 0;
			bs.gridy = 3;
			topPanel.add(getTextFieldPanel(), null);
			topPanel.add(getButtonPanel(), null);
		}
		return topPanel;
	}
	
	private JPanel getButtonPanel()
	{
		if(buttonPanel == null){
			GridBagConstraints wButtonCtr = new GridBagConstraints();
			wButtonCtr.gridx = 0;
			wButtonCtr.gridy = 1;
			
			GridBagConstraints cButtonCtr = new GridBagConstraints();
			cButtonCtr.gridx = 1;
			cButtonCtr.gridy = 1;
			
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridBagLayout());
			buttonPanel.add(getWithdrawButton(), wButtonCtr);
			buttonPanel.add(getCancelButton(), cButtonCtr);
		}
		
		return buttonPanel;
	}

	private JPanel getTextFieldPanel()
	{
		if(textPanel == null){
			GridBagConstraints bl = new GridBagConstraints();
			bl.gridx = 0;
			bl.gridy = 0;
			
			GridBagConstraints bf = new GridBagConstraints();
			bf.gridx = 0;
			bf.gridy = 1;
			
			
			textPanel = new JPanel();
			textPanel.setLayout(new GridBagLayout());
			textPanel.add(getLblAmount(), bl);
			textPanel.add(getTextAmount(), bf);			
		}
		return textPanel;
	}
	
	
	
	
	
	
	DecimalFormat dec = new DecimalFormat("'$'0.00");
	
	private JLabel getLblAmount(){
		if(lblAmount == null){
			lblAmount = new JLabel();
			lblAmount.setText("Amount:");
			lblAmount.setPreferredSize(new Dimension(200, 20));
		}
		return lblAmount;
	}
	
	private void setAmount(){
	
	}
	private JTextField getTextAmount() {
		if (textAmount == null) {
			textAmount = new JTextField();
			textAmount.setText("$0.00");
			textAmount.setColumns(10);
		}
		return textAmount;
	}
	
	private JButton getWithdrawButton(){
		if(withdrawButton == null){
			if (((ATMCoreModel)getModel()).type == TransactionTypes.Withdraw){
				withdrawButton = new JButton(WITHDRAW);
				withdrawButton.setActionCommand("processWithdraw");
			}
			else if (((ATMCoreModel)getModel()).type == TransactionTypes.Transfer){
				withdrawButton = new JButton(TRANSFER);
				withdrawButton.setActionCommand("processTransfer");
			}
			withdrawButton.addActionListener(handler);
			withdrawButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					((ATMController)getController()).withdrawFunds(((ATMCoreModel)getModel()).getFromAccount(), Double.parseDouble(getTextAmount().getText()));;
				}
			});
		}
		return withdrawButton;
	}
	
	private JButton getCancelButton(){
		if(cancelButton == null){
			cancelButton = new JButton(CANCEL);
			cancelButton.setActionCommand("Cancel");
			cancelButton.addActionListener(handler);
		}
		return cancelButton;
	}
	
	
	private class Handler implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			((ATMController)getController()).operation(evt.getActionCommand());
		}
	}


	@Override
	public void modelChanged(ModelEvent me) {
		WithdrawView wv = new WithdrawView(getModel(),getController());

		add(wv);
		repaint();	
	}	
}
