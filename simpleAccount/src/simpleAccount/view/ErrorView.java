package simpleAccount.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import simpleAccount.controller.AccountController;
import simpleAccount.model.AccountModel;
import simpleAccount.model.ModelEvent;
import simpleAccount.view.TransferView.Handler;
import javax.swing.JTextArea;


/**
 * This is the ErrorView for if an error is encountered in the view.
 * 
 * @author Paul Bryson
 * @since 2015-04-05
 */
public class ErrorView extends JFrameView {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("");
		frame.setBounds(100, 100, 463, 171);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Handler handler = new Handler();
		
		JButton btnDismiss = new JButton("Dismiss");
		btnDismiss.addActionListener(handler);
		btnDismiss.setBounds(158, 98, 123, 23);
		frame.getContentPane().add(btnDismiss);
		
		JTextArea textErrorMsg = new JTextArea();
		textErrorMsg.setWrapStyleWord(true);
		textErrorMsg.setLineWrap(true);
		textErrorMsg.setEditable(false);
		textErrorMsg.setText("");
		textErrorMsg.setBounds(10, 11, 427, 60);
		frame.getContentPane().add(textErrorMsg);
		textErrorMsg.setColumns(10);
	}

	@Override
	public void modelChanged(ModelEvent event) {
	}
	
	/**
	 * Inner classes for Event Handling 
	 * @author Paul Bryson
	 *
	 */
	class Handler implements ActionListener { 
		// Event handling is handled locally
		public void actionPerformed(ActionEvent e) {
			unregisterWithModel();
			frame.setVisible(false);
	    }
	}
	
	/**
	 * Sets up the error view.
	 * @param model the AccountModel
	 * @param controller the AccountController
	 */
	public ErrorView (AccountModel model, AccountController controller){
		super(model, controller);
		initialize();
		this.frame.setVisible(true);
	}
	
	/**
	 * Sets the error message.
	 * @param msg the error message
	 */
	public void setError (String msg) {
		((JTextArea) frame.getContentPane().getComponent(1)).setText(msg);
	}
	
	/**
	 * This function has the sole purpose of allowing the design window to work
	 */
	public ErrorView (){
		super(new AccountModel(), new AccountController());
		initialize();
		this.frame.setVisible(true);
	}
}
