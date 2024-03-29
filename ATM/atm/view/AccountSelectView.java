package atm.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
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

/**
 * The AccountSelectView allows the user to select an account they
 * would like to interact with.
 * 
 * @author Chris Wells
 * @since 2015-03-25
 */
public class AccountSelectView extends JFrameView {

	private static final long serialVersionUID = 1L;
	private Handler handler = new Handler();
	DecimalFormat dec = new DecimalFormat("'$'0.00");
	
	/**
	 * Constructor.
	 * 
	 * @param model the ATMCoreModel
	 * @param controller the ATMController
	 */
	public AccountSelectView(Model model, Controller controller) {
		super(model, controller);
		setLayout(null);

		start();
	}
	
	ArrayList<JButton> accountButtons;
	
	/**
	 * Start assembles the content of the page.
	 */
	public void start(){
		//setSize(new Dimension(1434, 1062));
		addButtons();
		setVisible(true);
	
	}
	
	int current_page = 0;
	int total_pages = 0;
	/**
	 * Adds the button to the page.
	 */
	public void addButtons (){
		List<Results> accounts = ((ATMCoreModel)getModel()).getAccounts();
		if(accounts.size() == 0){
			setLayout(new BorderLayout(0, 0));
			
			Label label = new Label("There are no accounts associated with your card.");
			label.setAlignment(Label.CENTER);
			label.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 24));
			label.setForeground(new Color(255, 0, 0));
			add(label, BorderLayout.CENTER);
			setVisible(true);
			repaint();
			return;
		}
		total_pages = (int) Math.ceil((double)accounts.size()/4);
		
		if (total_pages > 1){
			System.out.println("Total Pages: " + total_pages+ " \nAccounts: " + accounts.size());
			Icon nextIcon = new ImageIcon(this.getClass().getResource("/atm/view/next_page_arrow.gif"));
			JButton nextPage = new JButton(nextIcon);
			nextPage.setBounds(750, 650, 250, 150);
			nextPage.setOpaque(false);
			nextPage.setBorderPainted(false);
			nextPage.setContentAreaFilled(false);
			nextPage.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					removeAll();
					current_page = current_page + 1;
					if (current_page == total_pages)
						current_page = 0;
					addButtons();
					revalidate();
					repaint();
				}
			});
			add(nextPage);
		}
		
		BufferedImage picture = ((ATMCoreModel)getModel()).getPicture();
		UIManager.put("Button.disabled",UIManager.get("Button.enabled"));
		System.out.println("SIZE: " + accounts.size());
		int i = 0;
		int lastResult = current_page*4+4;
		System.out.println("Current Page: "+ current_page);
		if (current_page +1 == total_pages){
			lastResult = accounts.size();
		}
		
		for(final Results a : accounts.subList(current_page*4, lastResult)){
			JButton Account = new JButton();
			Account.setPreferredSize(new Dimension(200, 200));
			Account.setFont(new Font("Dialog", Font.BOLD, 14));
			Account.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			Account.setHorizontalAlignment(SwingConstants.LEFT);
			Account.setText("<html>" + a.getName() +"<br>"+ dec.format(a.getBalance()) +"</html>");
			Account.setFont(new Font("Gill Sans MT Condensed", Font.PLAIN, 22));
			Account.setActionCommand("SelectAccount");
			Account.addActionListener(handler);
			Account.setBounds(new Rectangle(i%2*300+350, (i/2)* 250+150, 250, 150));
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
		
		if (accounts.size() > 1){
			Icon transIcon = new ImageIcon(this.getClass().getResource("/atm/view/transfer.jpg"));
			JButton btnTransfer = new JButton(transIcon);
			btnTransfer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnTransfer.setFont(new Font("Gill Sans MT Ext Condensed Bold", Font.PLAIN, 40));
			btnTransfer.setBounds(25, 472, 300, 100);
			btnTransfer.addActionListener(handler);
			setLayout(null);
			btnTransfer.setActionCommand("Transfer");
			add(btnTransfer);
		}
		
		Icon cancelIcon = new ImageIcon(this.getClass().getResource("/atm/view/cancel.jpg"));
		JButton btnCancel = new JButton(cancelIcon);
		//btnCancel.setBackground(Color.RED);
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		//btnCancel.setFont(new Font("Gill Sans MT Ext Condensed Bold", Font.PLAIN, 40));
		btnCancel.setBounds(25, 694, 300, 100);
		btnCancel.addActionListener(handler);
		setLayout(null);
		btnCancel.setActionCommand("Cancel");
		add(btnCancel);
		
		Icon withIcon = new ImageIcon(this.getClass().getResource("/atm/view/withdraw.jpg"));
		JButton btnWithdraw = new JButton(withIcon);
		btnWithdraw.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnWithdraw.setFont(new Font("Gill Sans MT Ext Condensed Bold", Font.PLAIN, 40));
		btnWithdraw.setBounds(25, 583, 300, 100);
		btnWithdraw.addActionListener(handler);
		btnWithdraw.setActionCommand("Withdraw");
		add(btnWithdraw);
		
		JLabel userImage = new JLabel();
		userImage.setIcon(new ImageIcon(picture));
		userImage.setBounds(350, 600, 200, 200);
		add(userImage);
		
	}
	
	/**
	 * The account the user wants to transfer from.
	 */
	public void SelectFrom(){
		JLabel lblSelectFromAccount = new JLabel("Select Account");
		
		lblSelectFromAccount.setFont(new Font("Gill Sans MT Ext Condensed Bold", Font.PLAIN, 24));
		lblSelectFromAccount.setForeground(new Color(255, 0, 0));
		lblSelectFromAccount.setBounds(500, 27, 300, 50);
		add(lblSelectFromAccount);
	}
	
	/** 
	 * The account the user wants to transfer to.
	 */
	public void SelectTo(){
		JLabel lblSelectFromAccount = new JLabel("Select the account to transfer to");
		
		lblSelectFromAccount.setFont(new Font("Gill Sans MT Ext Condensed Bold", Font.PLAIN, 24));
		lblSelectFromAccount.setForeground(new Color(255, 0, 0));
		lblSelectFromAccount.setBounds(500, 27, 450, 50);
		add(lblSelectFromAccount);
	}
	
	@Override
	public void modelChanged(ModelEvent me) {}

	/**
	 * Handling the user's actions.
	 *
	 */
	private class Handler implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			((ATMController)getController()).operation(evt.getActionCommand());
			System.out.println(evt.getActionCommand());
		}
	}
}
