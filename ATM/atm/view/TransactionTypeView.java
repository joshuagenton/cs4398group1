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
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/**
 * The TransactionView is the view users see inbetween transactions.
 * 
 * @author Chris Wells
 * @since 2015-03-25
 */
public class TransactionTypeView extends JFrameView {


	private static final long serialVersionUID = 1L;
	private Handler handler = new Handler();
	
	/**
	 * Contructor.
	 * 
	 * @param model the ATMCoreModel
	 * @param controller the ATMController
	 */
	public TransactionTypeView(Model model, Controller controller) {
		super(model, controller);
		
		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTransfer.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnTransfer.setBounds(169, 133, 200, 200);
		btnTransfer.addActionListener(handler);
		setLayout(null);
		btnTransfer.setActionCommand("Transfer");
		add(btnTransfer);
		
		JButton btnWithdraw = new JButton("");
		btnWithdraw.setIcon(new ImageIcon(TransactionTypeView.class.getResource("/atm/view/button_Withdraw.png")));
		btnWithdraw.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnWithdraw.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnWithdraw.setBounds(386, 133, 200, 200);
		btnWithdraw.addActionListener(handler);
		btnWithdraw.setActionCommand("Withdraw");
		add(btnWithdraw);
		
		JButton btnCheckBalance = new JButton("<html>Check<br>Balance</html>");
		btnCheckBalance.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCheckBalance.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnCheckBalance.setBounds(602, 133, 200, 200);
		btnCheckBalance.addActionListener(handler);
		btnCheckBalance.setActionCommand("Balance");
		add(btnCheckBalance);
	}

	@Override
	public void modelChanged(ModelEvent me) {}
	
	/**
	 * The Handler for the user's actions.
	 * 
	 * @author Chris Wells
	 *
	 */
	private class Handler implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			((ATMController)getController()).operation(evt.getActionCommand());
			System.out.println(evt.getActionCommand());
			
		}
	}
}
