package atm.view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
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

import javax.swing.SwingConstants;

/**
 * The AmountView view is where the user is allowed to enter the amoun they would like to
 * transfer or withdraw.
 * 
 * @author Chris Wells
 * @since 2015-03-25
 */
public class AmountView extends JFrameView {

	private static final long serialVersionUID = 1L;

	public final static String WITHDRAW = "Withdraw";
	public final static String TRANSFER = "Transfer";
	public final static String CANCEL = "Cancel";
	
	private JLabel lblAmount;
	
	private JFormattedTextField textAmount;
	
	private JButton submitButton;
	private JButton Cancel;
	private JButton Clear;
	public CardReaderController card = new CardReaderController(getModel()); 
	private Handler handler = new Handler();
	
	
	/**
	 * Constructor. 
	 * 
	 * @param model the ATMCoreModel
	 * @param controller the ATMController
	 */
	public AmountView(Model model, Controller controller) {
		super(model, controller);
		setLayout(null);
		setBackground(Color.WHITE);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{300, 100, 110, 110, 110, 100, 100, 100, 100};
		gridBagLayout.rowHeights = new int[]{200, 100, 100, 100, 100, 100, 100};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0, 0, 0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		GridBagConstraints gbc_textAmount = new GridBagConstraints();
		gbc_textAmount.fill = GridBagConstraints.HORIZONTAL;
		gbc_textAmount.anchor = GridBagConstraints.SOUTH;
		gbc_textAmount.gridwidth = 3;
		gbc_textAmount.insets = new Insets(0, 0, 5, 5);
		gbc_textAmount.gridx = 3;
		gbc_textAmount.gridy = 0;
		add(getTextAmount(), gbc_textAmount);
		
		
		GridBagConstraints gbc_Clear = new GridBagConstraints();
		gbc_Clear.insets = new Insets(0, 0, 0, 0);
		gbc_Clear.fill = GridBagConstraints.BOTH;
		gbc_Clear.gridx = 3;
		gbc_Clear.gridy = 4;
		add(getClear(), gbc_Clear);
		GridBagConstraints gbc_Enter = new GridBagConstraints();
		gbc_Enter.fill = GridBagConstraints.BOTH;
		gbc_Enter.insets = new Insets(0, 0, 0, 0);
		gbc_Enter.gridx = 5;
		gbc_Enter.gridy = 4;
		add(getSubmitButton(), gbc_Enter);
		GridBagConstraints gbc_Cancel = new GridBagConstraints();
		gbc_Cancel.anchor = GridBagConstraints.SOUTH;
		gbc_Cancel.gridwidth = 3;
		gbc_Cancel.insets = new Insets(0, 0, 0, 0);
		gbc_Cancel.gridx = 3;
		gbc_Cancel.gridy = 5;
		this.add(getCancel(), gbc_Cancel);
		
		GridBagConstraints amount_size = new GridBagConstraints();
		gbc_Cancel.anchor = GridBagConstraints.SOUTH;
		gbc_Cancel.gridwidth = 3;
		gbc_Cancel.insets = new Insets(0, 0, 0, 0);
		gbc_Cancel.gridx = 0;
		gbc_Cancel.gridy = 3;
		this.add(getSubmitButton(), amount_size);
		numPad();

	    GridBagConstraints gbc_lblAmount = new GridBagConstraints();
	    gbc_lblAmount.anchor = GridBagConstraints.SOUTHEAST;
	    gbc_lblAmount.gridwidth = 2;
	    gbc_lblAmount.insets = new Insets(0, 0, 0, 0);
	    gbc_lblAmount.gridx = 1;
	    gbc_lblAmount.gridy = 0;
	    add(getLblAmount(), gbc_lblAmount);
	    
	    GridBagConstraints gbc_lbl = new GridBagConstraints();
	    gbc_lbl.anchor = GridBagConstraints.NORTH;
	    gbc_lbl.gridwidth = 2;
	    gbc_lbl.insets = new Insets(0, 0, 0, 0);
	    gbc_lbl.gridx = 0;
	    gbc_lbl.gridy = 0;
	    add (accountLabel(), gbc_lbl);

	    add(getSubmitButton(),gbc_Enter);
	    numPad();
	}

	/**
	 * The numeric pad for the user to enter an amount.
	 */

	
	private void numPad() {
		for (Integer i = 0; i<10 ; i++){
			GridBagConstraints gbc_num = new GridBagConstraints();
			final Integer number = i;
			JButton num = new JButton();
			num.setFont(new Font("Tahoma", Font.PLAIN, 24));
			num.setText(number.toString());
			if (i == 0){
				gbc_num.fill = GridBagConstraints.BOTH;
				gbc_num.gridx = 4;
				gbc_num.gridy = 4;
				gbc_num.insets = new Insets(0, 0, 0, 0);
			}
			else{
				gbc_num.fill = GridBagConstraints.BOTH;
				gbc_num.insets = new Insets(0, 0, 0, 0);
				gbc_num.gridx = (i-1)%3 + 3;
				gbc_num.gridy = (i-1)/3 + 1;
			}
			num.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					double amount = (double) textAmount.getValue()*10+number ;
					textAmount.setValue(amount);
					textAmount.setText(dec.format(amount));
					IdleTimeController.runTimer((ATMController)getController());
				}
			});
			this.add(num, gbc_num);
		}
	}
	// Getters/Setters
	
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
	
	DecimalFormat dec = new DecimalFormat("'$'0.00");
	JTextArea msg = new JTextArea(5,15);
	private void setMsg(String notice){
		
		msg.setOpaque(false);
		setLayout(null);
		//msg.setLineWrap(true);
		msg.setBounds(404, 461, 300, 100);
	    add (msg);
		msg.setFont(new Font("Tahoma", Font.PLAIN, 16));
		msg.setText(notice);
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
			textAmount.setHorizontalAlignment(SwingConstants.CENTER);
			textAmount.setBackground(new Color(224, 255, 255));
			textAmount.setBorder(null);
			textAmount.setFont(new Font("Tahoma", Font.PLAIN, 32));
			textAmount.setValue(0.0);
			textAmount.setText("$0.00");
			textAmount.setEditable(false);
			//textAmount.setText("$0.00");
			textAmount.setColumns(10);
		}
		return textAmount;
	}
	private JButton getCancel() {
		if (Cancel == null) {
			Icon cancelIcon = new ImageIcon(this.getClass().getResource("/atm/view/cancel.jpg"));
			Cancel = new JButton(cancelIcon);
			Cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			//Cancel.setText("Cancel");
			Cancel.setBorderPainted(false);
			Cancel.setContentAreaFilled(false);
			Cancel.setActionCommand("Cancel");
			
			Cancel.addActionListener(handler);
			Cancel.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					IdleTimeController.runTimer((ATMController)getController());
				}
			});
		}
		return Cancel;
	}
	private JButton getSubmitButton(){
		if(submitButton == null){
			if (((ATMCoreModel)getModel()).type == TransactionTypes.Withdraw){
				submitButton = new JButton(WITHDRAW);
				submitButton.setBackground(new Color(0, 128, 0));
				submitButton.setBounds(738, 400, 100, 100);
				submitButton.setActionCommand("processWithdraw");
			}
			else if (((ATMCoreModel)getModel()).type == TransactionTypes.Transfer){
				submitButton = new JButton(TRANSFER);
				submitButton.setBackground(new Color(0, 128, 0));
				submitButton.setBounds(738, 400, 100, 100);
				submitButton.setActionCommand("processTransfer");
			}
			submitButton.addActionListener(handler);
			submitButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if ((double)textAmount.getValue()%20 != 0 && ((ATMCoreModel)getModel()).type == TransactionTypes.Withdraw){
						setMsg("Please Enter\nan amount in \nincrements of\n$20.00");
						textAmount.setValue(0.0);
						textAmount.setText("$0.00" );
						IdleTimeController.runTimer((ATMController)getController());
					}
					else if ((double)textAmount.getValue() > 800.0 && ((ATMCoreModel)getModel()).type == TransactionTypes.Withdraw){
						setMsg("Please Enter\nan amount less \nthan $800.00");
						textAmount.setValue(0.0);
						textAmount.setText("$0.00" );
						IdleTimeController.runTimer((ATMController)getController());
					}
					else if (((ATMCoreModel)getModel()).type == TransactionTypes.Withdraw)
						((ATMController)getController()).withdrawFunds(((ATMCoreModel)getModel()).getFromAccount(), (double)textAmount.getValue());
					else if (((ATMCoreModel)getModel()).type == TransactionTypes.Transfer)
						((ATMController)getController()).transferFunds(((ATMCoreModel)getModel()).getFromAccount(), ((ATMCoreModel)getModel()).getToAccount(), (double)textAmount.getValue());
					else
						System.out.println("ERROR");
				}
			});
		}
		return submitButton;
	}

	
	private JButton getClear() {
		if (Clear == null) {
			Clear = new JButton();
			Clear.setFont(new Font("Tahoma", Font.PLAIN, 21));
			Clear.setBounds(new Rectangle(538, 389, 100, 100));
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
	
	/**
	 * Handles user's actions.
	 *
	 */
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
