package atm.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import atm.controller.ATMController;
import atm.controller.CardReaderController;
import atm.controller.Controller;
import atm.model.ATMCoreModel;
import atm.model.Model;
import atm.model.ModelEvent;
import atm.model.TransactionTypes;

public class WithdrawView extends JFrameView {

	private static final long serialVersionUID = 1L;

	public final static String LOGOUT = "Logout";
	
	
	private JPanel textPanel;
	private JPanel buttonPanel;
	
	private JLabel takeCashLabel;
	
	private JButton logoutButton;
	private JLabel moneyGif;
	
	public CardReaderController card = new CardReaderController(getModel()); 
	private Handler handler = new Handler();
	
	
	public WithdrawView(Model model, Controller controller) {
		super(model, controller);
		
		this.setBounds(100, 100, 450, 300);
		setLayout(null);
		
		Toolkit toolkit =  Toolkit.getDefaultToolkit();
		Dimension dim = toolkit.getScreenSize();
		
		int x = (int) ((dim.getWidth() - this.getWidth()) * 0.5f);
	    int y = (int) ((dim.getHeight() - this.getHeight()) * 0.5f);
	    this.setLocation(x, y);
	    add(getButtonPanel());
	    add(getTextFieldPanel());
	    
	}

	private JPanel getButtonPanel()
	{
		if(buttonPanel == null){
			GridBagConstraints wButtonCtr = new GridBagConstraints();
			wButtonCtr.gridx = 0;
			wButtonCtr.gridy = 1;
			
			buttonPanel = new JPanel();
			buttonPanel.setBounds(125, 400, 200, 53);
			buttonPanel.setLayout(new GridBagLayout());
			buttonPanel.add(getLogoutButton(), wButtonCtr);
		}
		
		return buttonPanel;
	}

	private JPanel getTextFieldPanel()
	{
		if(textPanel == null){
			GridBagConstraints bl = new GridBagConstraints();
			bl.gridx = 0;
			bl.gridy = 0;
			
			textPanel = new JPanel();
			textPanel.setBounds(125, 300, 200, 106);
			textPanel.setLayout(new GridBagLayout());
			if (((ATMCoreModel)getModel()).type == TransactionTypes.Withdraw) {
				textPanel.add(getTakeCashLabel(), bl);
				add(getLblMoneyGif());
			}
			else if (((ATMCoreModel)getModel()).type == TransactionTypes.Transfer) textPanel.add(getTransferCompleteLabel(), bl);
		}
		return textPanel;
	}

	
	private JLabel getTransferCompleteLabel(){
		if(takeCashLabel == null){
			takeCashLabel = new JLabel();
			takeCashLabel.setText("Transfer Complete");
			takeCashLabel.setPreferredSize(new Dimension(200, 20));
		}
		return takeCashLabel;
	}
	
	
	private JLabel getTakeCashLabel(){
		if(takeCashLabel == null){
			takeCashLabel = new JLabel();
			takeCashLabel.setText("Please Take Your Cash");
			takeCashLabel.setPreferredSize(new Dimension(200, 20));
			
		}
		return takeCashLabel;
	}
	
	private JButton getLogoutButton(){
		if(logoutButton == null){
			logoutButton = new JButton(LOGOUT);
			logoutButton.addActionListener(handler);
		}
		return logoutButton;
	}
	
	private JLabel getLblMoneyGif() {
		if (moneyGif == null) {
			moneyGif = new JLabel();
			moneyGif.setIcon(new ImageIcon(LoginView.class.getResource("/atm/view/moneyDispensed.gif")));
			moneyGif.setBounds(500, 300, 250, 150);	
		}
		return moneyGif;
	}
	
	
	private class Handler implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			((ATMController)getController()).operation(evt.getActionCommand());
		}
	}


	@Override
	public void modelChanged(ModelEvent me) {
		LogoutView lv = new LogoutView(getModel(),getController());

		add(lv);
		repaint();	
	}	
}
