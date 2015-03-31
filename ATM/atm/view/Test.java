package atm.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import atm.controller.ATMController;
import atm.model.atm_core;

public class Test {

	private JPanel frame;

	/** 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		final atm_core account = new atm_core();
		final ATMController contr = new ATMController();
		contr.setModel(account);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frame.add    (new LoginView(account, contr));
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JPanel();
		frame.setBounds(100, 100, 450, 300);
	}

}
