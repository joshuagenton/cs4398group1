package atm.view;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import atm.controller.ATMController;
import atm.controller.Controller;
import atm.model.ATMCoreModel;
import atm.model.Model;
import atm.model.ModelEvent;


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
		setBackground(new Color(122, 58, 255));
		setSize(new Dimension(527, 265));
		//this.setTitle("Welcome");
		addButtons();
		setVisible(true);
	
	}

	public void addButtons (){
		Map<String,Double> accounts = ((ATMCoreModel)getModel()).getAccounts();
		System.out.println("SIZE: " + accounts.size());
		int i = 0;
		for(Map.Entry<String,Double> a : accounts.entrySet()){
			JButton Account = new JButton();
			Account.setPreferredSize(new Dimension(200, 200));
			Account.setFont(new Font("Dialog", Font.BOLD, 14));
			Account.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			Account.setHorizontalAlignment(SwingConstants.LEFT);
			Account.setText(a.getKey());
			Account.setActionCommand("SelectAccount");
			Account.addActionListener(handler);
			Account.setBounds(new Rectangle(i%2*300+350, (i/2)* 300+150, 250, 150));
			Account.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {

				}
			});
			i++;
			add(Account);
		}
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
