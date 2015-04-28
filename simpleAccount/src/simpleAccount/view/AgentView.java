package simpleAccount.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Random;
import java.util.SortedMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import simpleAccount.controller.AccountController;
import simpleAccount.model.AccountModel;
import simpleAccount.model.ModelEvent;
import simpleAccount.view.AccountView.Handler;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;


/**
 * This is the AgentView.  This is the secondary window that pops up
 * when the user selects that they want a deposit/withdraw agent.
 * 
 * @author Paul Bryson/Stacie Christensen
 * @since 2015-04-05
 */
public class AgentView extends JFrameView {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private String id = "", agentType = "";
	private JTextField agentID;
	private JTextField txtAmounttransferred;
	private JTextField txtStatedisplay;
	private JTextField txtOpscompleted;
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("");
		frame.setBounds(100, 100, 474, 307);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Handler handler = new Handler();
		
		JButton btnStartAgent = new JButton("Start Agent");
		btnStartAgent.addActionListener(handler);
		btnStartAgent.setBounds(10, 231, 139, 23);
		frame.getContentPane().add(btnStartAgent);
		
		JButton btnStopAgent = new JButton("Stop Agent");
		btnStopAgent.setEnabled(false);
		btnStopAgent.addActionListener(handler);
		btnStopAgent.setBounds(159, 231, 139, 23);
		frame.getContentPane().add(btnStopAgent);
		
		JButton btnDismiss = new JButton("Dismiss");
		btnDismiss.addActionListener(handler);
		btnDismiss.setBounds(309, 231, 139, 23);
		frame.getContentPane().add(btnDismiss);
		
		JLabel lblAgentID = new JLabel("Agent ID:");
		lblAgentID.setBounds(10, 14, 139, 14);
		frame.getContentPane().add(lblAgentID);
		
		JLabel lblEnterAmountIn = new JLabel("Amount in $");
		lblEnterAmountIn.setBounds(10, 45, 139, 14);
		frame.getContentPane().add(lblEnterAmountIn);
		
		NumberFormat amountFormat = NumberFormat.getNumberInstance();
		JFormattedTextField frmtdtxtfldTransferamount = new JFormattedTextField(amountFormat);
		frmtdtxtfldTransferamount.setText("0.0");
		frmtdtxtfldTransferamount.setBounds(159, 42, 139, 20);
		frame.getContentPane().add(frmtdtxtfldTransferamount);
		
		agentID = new JTextField();
		agentID.setBounds(159, 11, 139, 20);
		agentID.setText(Integer.toString((new Random()).nextInt(100)));
		frame.getContentPane().add(agentID);
		agentID.setColumns(10);
		
		JFormattedTextField frmtdtxtOpspersec = new JFormattedTextField(amountFormat);
		frmtdtxtOpspersec.setText("0");
		frmtdtxtOpspersec.setBounds(159, 73, 139, 20);
		frame.getContentPane().add(frmtdtxtOpspersec);
		
		JLabel lblOperationsPerSecond = new JLabel("Operations per second");
		lblOperationsPerSecond.setBounds(10, 74, 139, 14);
		frame.getContentPane().add(lblOperationsPerSecond);
		
		JLabel lblAmountIn = new JLabel("Amount in $ transferred");
		lblAmountIn.setBounds(10, 104, 139, 14);
		frame.getContentPane().add(lblAmountIn);
		
		txtAmounttransferred = new JTextField();
		txtAmounttransferred.setEditable(false);
		txtAmounttransferred.setText("0");
		txtAmounttransferred.setBounds(159, 101, 139, 20);
		frame.getContentPane().add(txtAmounttransferred);
		txtAmounttransferred.setColumns(10);
		
		txtStatedisplay = new JTextField();
		txtStatedisplay.setEditable(false);
		txtStatedisplay.setText("Stopped");
		txtStatedisplay.setBounds(159, 135, 139, 20);
		frame.getContentPane().add(txtStatedisplay);
		txtStatedisplay.setColumns(10);
		
		txtOpscompleted = new JTextField();
		txtOpscompleted.setEditable(false);
		txtOpscompleted.setText("0");
		txtOpscompleted.setBounds(159, 166, 139, 20);
		frame.getContentPane().add(txtOpscompleted);
		txtOpscompleted.setColumns(10);
		
		JLabel lblState = new JLabel("State");
		lblState.setBounds(10, 138, 46, 14);
		frame.getContentPane().add(lblState);
		
		JLabel lblOperationsCompleted = new JLabel("Operations Completed");
		lblOperationsCompleted.setBounds(10, 169, 139, 14);
		frame.getContentPane().add(lblOperationsCompleted);
	}

	@Override
	public void modelChanged(ModelEvent event) {
		HashMap<String, Object> account = event.getAccounts().get(Integer.parseInt(id));
		frame.setTitle("Start " + this.agentType + " agent for account: " + account.get("id"));
	}
	
	/**
	 * Inner classes for Event Handling 
	 * @author Paul Bryson
	 *
	 */
	class Handler implements ActionListener { 
		// Event handling is handled locally
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Dismiss")) {
				unregisterWithModel();
				frame.setVisible(false);
			}
			else {
				Double amount = Double.parseDouble(((JTextField) frame.getContentPane().getComponent(6)).getText().toString().replace(",", ""));
				if (e.getActionCommand().equals("Withdraw"))
					amount = -amount;
				((AccountController)getController()).operationTransfer(id, agentType, amount);
				((JTextField) frame.getContentPane().getComponent(6)).setText("0.0");
			}
	    }
	}
	
	/**
	 * Sets up the AgentView
	 * @param model the AccountModel
	 * @param controller the AccountController
	 */
	public AgentView (AccountModel model, AccountController controller){
		super(model, controller);
		initialize();
		this.frame.setVisible(true);
	}
	
	/**
	 * Sets the agent values.
	 * @param id the agentID
	 * @param agentType the agent type (deposit/withdraw)
	 */
	public void setValues (String id, String agentType) {
		this.id = id;
		this.agentType = agentType;
	}
	
	/**
	 * This function has the sole purpose of allowing the design window to work
	 */
	public AgentView (){
		super(new AccountModel(), new AccountController());
		initialize();
		this.frame.setVisible(true);
	}
}
