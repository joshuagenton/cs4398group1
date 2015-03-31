package atm.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import atm.controller.ATMController;
import atm.controller.Controller;
import atm.model.Model;
import atm.model.ModelEvent;
import atm.model.atm_core;

public class test2 extends JFrameView {

	private JFrame frame;

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
					test2 window = new test2(account, contr);
					window.frame.setContentPane(new LoginView(account, contr));
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
	public test2(Model model, Controller controller) {
		super(model, controller);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void modelChanged(ModelEvent me) {
		// TODO Auto-generated method stub
		
	}

}
