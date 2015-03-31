package atm.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import atm.controller.ATMController;
import atm.controller.Controller;
import atm.model.Model;
import atm.model.ModelEvent;
import atm.model.atm_core;

public class Test extends JFrameView{

	private JPanel frame;
	public Test (Model model, Controller controller){
		super(model, controller);
		initialize();
		this.setBounds(100, 100, 450, 300);
		//	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				dispose();
				System.exit(0);
	    }
	});
	}

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
					Test window = new Test(account, contr);
					window.add(new LoginView(account, contr));
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JPanel();
		frame.setBounds(100, 100, 450, 300);
	}

	@Override
	public void modelChanged(ModelEvent me) {
		// TODO Auto-generated method stub
		
	}

}
