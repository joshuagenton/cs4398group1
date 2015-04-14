package atm.view;

import java.awt.Dimension;
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
import atm.controller.CardReaderController;
import atm.controller.Controller;
import atm.model.ATMCoreModel;
import atm.model.Model;
import atm.model.ModelEvent;
import atm.model.TransactionTypes;
import javax.swing.JLayeredPane;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.awt.Color;

public class WithdrawAmountView extends JFrameView {

	private static final long serialVersionUID = 1L;

	public final static String WITHDRAW = "Withdraw";
	public final static String TRANSFER = "Transfer";
	public final static String CANCEL = "Cancel";
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
		setLayout(null);
		setBackground(Color.WHITE);
		
		this.setBounds(100, 100, 847, 588);	
				
		
		Toolkit toolkit =  Toolkit.getDefaultToolkit();
		Dimension dim = toolkit.getScreenSize();
		
		int x = (int) ((dim.getWidth() - this.getWidth()) * 0.5f);
	    int y = (int) ((dim.getHeight() - this.getHeight()) * 0.5f);
	    this.setLocation(x, y);
	    add(getButtonPanel());
	    add(getTextFieldPanel());
	    add(getLblAmount());
	    
	    
	}
	

	
	private JPanel getButtonPanel()
	{
		if(buttonPanel == null){
			buttonPanel = new JPanel();
			buttonPanel.setBounds(450, 361, 300, 100);
			buttonPanel.add(getWithdrawButton());
			buttonPanel.setLayout(null);
			buttonPanel.add(getCancelButton());
		}
		
		return buttonPanel;
	}

	private JPanel getTextFieldPanel()
	{
		if(textPanel == null){
			
			
			textPanel = new JPanel();
			textPanel.setBackground(new Color(224, 255, 255));
			textPanel.setBounds(450, 250, 300, 100);
			textPanel.setLayout(null);
			textPanel.add(getTextAmount());			
		}
		return textPanel;
	}
	
	
	
	
	
	
	DecimalFormat dec = new DecimalFormat("'$'0.00");
	
	private JLabel getLblAmount(){
		if(lblAmount == null){
			lblAmount = new JLabel();
			lblAmount.setBounds(450, 209, 176, 29);
			lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblAmount.setText("Enter an amount:");
			lblAmount.setPreferredSize(new Dimension(200, 20));
		}
		return lblAmount;
	}
	
	private JTextField getTextAmount() {
		if (textAmount == null) {
			textAmount = new JTextField();
			textAmount.setBackground(new Color(224, 255, 255));
			textAmount.setBorder(null);
			textAmount.setFont(new Font("Tahoma", Font.PLAIN, 32));
			textAmount.setBounds(90, 24, 158, 42);
			textAmount.setText("$0.00");
			textAmount.setColumns(10);
		}
		return textAmount;
	}
	
	private JButton getWithdrawButton(){
		if(withdrawButton == null){
			if (((ATMCoreModel)getModel()).type == TransactionTypes.Withdraw){
				withdrawButton = new JButton(WITHDRAW);
				withdrawButton.setBackground(new Color(0, 128, 0));
				withdrawButton.setBounds(0, 0, 140, 75);
				withdrawButton.setActionCommand("processWithdraw");
			}
			else if (((ATMCoreModel)getModel()).type == TransactionTypes.Transfer){
				withdrawButton = new JButton(TRANSFER);
				withdrawButton.setBackground(new Color(0, 128, 0));
				withdrawButton.setBounds(0, 0, 140, 75);
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
			cancelButton.setForeground(new Color(102, 0, 0));
			cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
			cancelButton.setBackground(new Color(255, 51, 51));
			cancelButton.setBounds(160, 0, 140, 75);
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
