package atm.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import atm.controller.ATMController;
import atm.controller.CardReaderController;
import atm.controller.Controller;
import atm.controller.MainController;
import atm.model.Model;
import atm.model.ModelEvent;
import atm.model.ATMCoreModel;

public class LoginView extends JFrameView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton TestPhilip = null;
	private JButton ChrisTest = null;
	public JTextField cardinfo = null;
	public CardReaderController card = new CardReaderController(getModel()); 
	private Handler handler = new Handler();
	
	public LoginView(Model model, Controller controller) {
		super(model, controller);
		this.getContentPane().add(initialize());
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
	 * This method initializes this
	 * 
	 * @return void
	 */
	private Component initialize() {
		//this.setExtendedState(MAXIMIZED_BOTH);
		JPanel login = new JPanel();
		login.setBackground(new Color(122, 58, 255));
		login.setSize(new Dimension(527, 265));
		//this.setTitle("Welcome");
		login.setVisible(true);

		login.add(getTestPhilip(), null);
		login.add(getCardinfo(), getCardinfo().getName());
		login.add(getChrisTest(), null);
		cardinfo.requestFocusInWindow();
		
		cardinfo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				System.out.println(cardinfo.getText()); // TODO Auto-generated Event stub actionPerformed()
				card.readCard(cardinfo.getText());
				cardinfo.setText("");
			
			}
		});
	//	this.add(test);
	//	test.setVisible(true);
		return login;
	}
	@Override
	public void modelChanged(ModelEvent me) {
		//change to PIN Screen
		PINScreen pin = new PINScreen(getModel(),getController());
		removeAll();
		add(pin);
		revalidate();
		repaint();
	}

	
	private class Handler implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			((ATMController)getController()).operation(evt.getActionCommand());
		}
	}
	/**
	 * This method initializes TestPhilip	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getTestPhilip() {
		if (TestPhilip == null) {
			TestPhilip = new JButton();
			TestPhilip.setPreferredSize(new Dimension(75, 20));
			TestPhilip.setFont(new Font("Dialog", Font.BOLD, 8));
			TestPhilip.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			TestPhilip.setHorizontalAlignment(SwingConstants.LEFT);
			TestPhilip.setText("Philip Test");
			TestPhilip.addActionListener(handler);
			TestPhilip.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					cardinfo.setText("%B6391480100325586  ^WATERS/PHILIP T           ^4912120?;6391480100325586=4912120?+202=034903800=00?");
					card.readCard(cardinfo.getText());
				}
			});
		}
		return TestPhilip;
	}

	/**
	 * This method initializes ChrisTest	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getChrisTest() {
		if (ChrisTest == null) {
			ChrisTest = new JButton();
			ChrisTest.setPreferredSize(new Dimension(75, 20));
			ChrisTest.setFont(new Font("Dialog", Font.BOLD, 8));
			ChrisTest.setText("Chris Test");
			ChrisTest.addActionListener(handler);
			ChrisTest.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					cardinfo.setText("%T6391480001052388  ^WELLS/CHRISTOPHER         ^4912160?;6391480001052388=4912160?+202=411558900=00?");
					card.readCard(cardinfo.getText());
				}
			});
		}
		return ChrisTest;
	}
	/**
	 * This method initializes cardinfo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCardinfo() {
		if (cardinfo == null) {
			cardinfo = new JTextField();
			cardinfo.setBackground(Color.lightGray);
			cardinfo.setPreferredSize(new Dimension(0, 0));
			cardinfo.setSize(new Dimension(0, 0));

		}
		return cardinfo;
	}
	public static void main(String[] args) {
		final ATMCoreModel account = new ATMCoreModel();
		final ATMController contr = new ATMController();
		contr.setModel(account);
	    SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	    	  LoginView start = new LoginView(account, contr);
	    	  contr.setView(start);
	    	  start.setVisible(true);
	      }
	    });
	  }
}
