package atm.view;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import atm.controller.ATMController;
import atm.controller.CardReaderController;
import atm.controller.Controller;
import atm.controller.IdleTimeController;
import atm.model.ATMCoreModel;
import atm.model.Model;
import atm.model.ModelEvent;
import atm.model.TransactionTypes;

import java.awt.Font;
import java.awt.Color;

public class AmountView extends JFrameView {

	private static final long serialVersionUID = 1L;

	public final static String WITHDRAW = "Withdraw";
	public final static String TRANSFER = "Transfer";
	public final static String CANCEL = "Cancel";
	private JPanel textPanel;
	private JPanel buttonPanel;
	
	private JLabel lblAmount;
	
	private JFormattedTextField textAmount;
	
	private JButton submitButton;
	private JButton cancelButton;
	private JButton Clear;
	public CardReaderController card = new CardReaderController(getModel()); 
	private Handler handler = new Handler();
	
	
	
	public AmountView(Model model, Controller controller) {
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
	    
	    add (accountLabel());
	    numPad();
	    
	}
	
	private void numPad() {
		for (Integer i = 0; i<10 ; i++){
			final Integer number = i;
			JButton num = new JButton();
			num.setFont(new Font("Tahoma", Font.PLAIN, 24));
			num.setText(number.toString());
			if (i == 0)
				num.setBounds(new Rectangle(638, 383, 100, 100));
			else
				num.setBounds(new Rectangle(538+(i-1)%3*100, 83+(i-1)/3*100, 100, 100));
			num.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					double amount = (double) textAmount.getValue()*10+number ;
				
					textAmount.setValue(amount);
					textAmount.setText(dec.format(amount));
					IdleTimeController.runTimer((ATMController)getController());
				}
			});
			add(num);
		}
		add(getClear());
	}
	
	public JLabel accountLabel(){
		StringBuilder label = new StringBuilder();
		label.append (((ATMCoreModel)getModel()).getFromAccount().getName());
		if (((ATMCoreModel)getModel()).getTransType() == TransactionTypes.Transfer){
			label.append(" to " +((ATMCoreModel)getModel()).getToAccount().getName() );
		}
		JLabel lbl= new JLabel("TEST:");
		
		lbl.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 24));
		lbl.setText(label.toString());
		lbl.setForeground(new Color(255, 0, 0));
		lbl.setBounds(10, 23, 879, 50);
		return lbl;
	}
	
	private JPanel getButtonPanel()
	{
		if(buttonPanel == null){
			buttonPanel = new JPanel();
			buttonPanel.setBounds(104, 361, 300, 100);
			buttonPanel.add(getSubmitButton());
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
			textPanel.setBounds(104, 250, 300, 100);
			textPanel.setLayout(null);
			textPanel.add(getTextAmount());			
		}
		return textPanel;
	}
	
	DecimalFormat dec = new DecimalFormat("'$'0.00");
	
	private void setMsg(){
		JLabel msg = new JLabel();
		msg = new JLabel();
		msg.setBounds(104, 461, 300, 100);
		
		msg.setFont(new Font("Tahoma", Font.PLAIN, 20));
		msg.setText("<html>Please Enter amount in<br> increments of $20.00</html>");
		msg.setPreferredSize(new Dimension(200, 20));
		add(msg);
		revalidate();
		repaint();
	}
	
	private JLabel getLblAmount(){
		if(lblAmount == null){
			lblAmount = new JLabel();
			lblAmount.setBounds(104, 209, 176, 29);
			lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblAmount.setText("Enter an amount:");
			lblAmount.setPreferredSize(new Dimension(200, 20));
		}
		return lblAmount;
	}
	
	NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
	NumberFormatter formatter = new NumberFormatter(format);
	private JFormattedTextField getTextAmount() {
		
		if (textAmount == null) {
			format.setMaximumFractionDigits(0);
			formatter.setMinimum(0.0);
			formatter.setMaximum(2000.00);
			formatter.setAllowsInvalid(false);
			formatter.setOverwriteMode(true);
			
			textAmount = new JFormattedTextField();
			textAmount.setBackground(new Color(224, 255, 255));
			textAmount.setBorder(null);
			textAmount.setFont(new Font("Tahoma", Font.PLAIN, 32));
			textAmount.setValue(0.0);
			textAmount.setText("$0.00");
			//TextPrompt promptTextAmount = new TextPrompt("$0.00",textAmount);
			//promptTextAmount.setFont(new Font("Tahoma", Font.PLAIN, 32));
			textAmount.setBounds(90, 24, 158, 42);
			//textAmount.setText("$0.00");
			textAmount.setColumns(10);
		}
		return textAmount;
	}
	
	private JButton getSubmitButton(){
		if(submitButton == null){
			if (((ATMCoreModel)getModel()).type == TransactionTypes.Withdraw){
				submitButton = new JButton(WITHDRAW);
				submitButton.setBackground(new Color(0, 128, 0));
				submitButton.setBounds(0, 0, 140, 75);
				submitButton.setActionCommand("processWithdraw");
			}
			else if (((ATMCoreModel)getModel()).type == TransactionTypes.Transfer){
				submitButton = new JButton(TRANSFER);
				submitButton.setBackground(new Color(0, 128, 0));
				submitButton.setBounds(0, 0, 140, 75);
				submitButton.setActionCommand("processTransfer");
			}
			submitButton.addActionListener(handler);
			submitButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if ((double)textAmount.getValue()%20 != 0){
						setMsg();
						textAmount.setValue(0.0);
						textAmount.setText("$0.00");
						IdleTimeController.runTimer((ATMController)getController());
					}
					else
						((ATMController)getController()).withdrawFunds(((ATMCoreModel)getModel()).getFromAccount(), (double)textAmount.getValue());;
				}
			});
		}
		return submitButton;
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
	
	private JButton getClear() {
		if (Clear == null) {
			Clear = new JButton();
			Clear.setFont(new Font("Tahoma", Font.PLAIN, 21));
			Clear.setBounds(new Rectangle(538, 383, 100, 100));
			Clear.setActionCommand("CancelScreen()");
			Clear.setText("Clear");
			Clear.setBackground(Color.yellow);
			Clear.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					textAmount.setValue(0.0);
					textAmount.setText("$0.00");
					IdleTimeController.runTimer((ATMController)getController());
					
				}
			});
		}
		return Clear;
	}

	@Override
	public void modelChanged(ModelEvent me) {
		WithdrawView wv = new WithdrawView(getModel(),getController());

		add(wv);
		repaint();	
	}	
}
