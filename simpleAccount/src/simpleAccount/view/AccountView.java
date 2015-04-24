package simpleAccount.view;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.SortedMap;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import simpleAccount.controller.Controller;
import simpleAccount.controller.AccountController;
import simpleAccount.model.Model;
import simpleAccount.model.AccountModel;
import simpleAccount.model.ModelEvent;
import simpleAccount.view.JFrameView;

public class AccountView extends JFrameView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 423, 192);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Handler handler = new Handler();
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 11, 389, 20);
		frame.getContentPane().add(comboBox);
		
		JButton btnEditInUsd = new JButton("Edit in USD");
		btnEditInUsd.addActionListener(handler);
		btnEditInUsd.setBounds(10, 42, 123, 23);
		frame.getContentPane().add(btnEditInUsd);
		
		JButton btnEditInEuros = new JButton("Edit in Euros");
		btnEditInEuros.addActionListener(handler);
		btnEditInEuros.setBounds(143, 42, 123, 23);
		frame.getContentPane().add(btnEditInEuros);
		
		JButton btnEditInYuan = new JButton("Edit in Yuan");
		btnEditInYuan.addActionListener(handler);
		btnEditInYuan.setBounds(276, 42, 123, 23);
		frame.getContentPane().add(btnEditInYuan);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(handler);
		btnSave.setBounds(10, 110, 123, 23);
		frame.getContentPane().add(btnSave);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(handler);
		btnExit.setBounds(143, 110, 123, 23);
		frame.getContentPane().add(btnExit);
	}

	@Override
	public void modelChanged(ModelEvent event) {
		// TODO Auto-generated method stub
		//String msg = event.getAmount() + "";
		//textField.setText(msg);
		if (((JComboBox) frame.getContentPane().getComponent(0)).getItemCount() == 0) {
			SortedMap<Integer, HashMap> accounts = event.getAccounts();
			for (Integer key : accounts.keySet()) {
				((JComboBox) frame.getContentPane().getComponent(0)).addItem((accounts.get(key).get("id") + " " + accounts.get(key).get("name")));
			}
		}
		
		
	}
	 // Inner classes for Event Handling 
	class Handler implements ActionListener { 
		// Event handling is handled locally
		public void actionPerformed(ActionEvent e) {
			String id = (((JComboBox) frame.getContentPane().getComponent(0)).getSelectedItem().toString().split(" "))[0].trim();
			((AccountController)getController()).operation(e.getActionCommand(), id);
			
			if (e.getActionCommand().equals("Exit")) {
				Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
	    }
	}
	
	public AccountView (AccountModel model, AccountController controller){
		super(model, controller);
		initialize();
		this.frame.setVisible(true);
	}
	
	//  This function has the sole purpose of allowing the design window to work
	public AccountView (){
		super(new AccountModel(), new AccountController());
		initialize();
		this.frame.setVisible(true);
	}

}
