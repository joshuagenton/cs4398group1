package atm.view;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import atm.controller.ATMController;
import atm.controller.CardReaderController;
import atm.controller.Controller;
import atm.model.Model;
import atm.model.ModelEvent;
import atm.model.ATMCoreModel;


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
		for(int i=0; i<3;i++){//((ATMCoreModel)getModel()).getAccount_validated(); i++){
			JButton Account = new JButton();
			Account.setPreferredSize(new Dimension(200, 200));
			Account.setFont(new Font("Dialog", Font.BOLD, 14));
			Account.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			Account.setHorizontalAlignment(SwingConstants.LEFT);
			Account.setText("Rodion's Bribe Fund " + i);
			Account.setActionCommand("SelectAccount");
			Account.addActionListener(handler);
			Account.setBounds(new Rectangle(i%2*300+350, (i/2)* 300+150, 250, 150));
			Account.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {

				}
			});
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
