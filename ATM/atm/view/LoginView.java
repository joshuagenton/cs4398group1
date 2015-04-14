package atm.view;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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

import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginView extends JFrameView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private JButton TestPhilip = null;
//	private JButton ChrisTest = null;
	public JTextField cardinfo = null;
	public CardReaderController card = new CardReaderController(getModel()); 
	private Handler handler = new Handler();
	private JLabel lblNewLabel;
	Icon icon = new ImageIcon(this.getClass().getResource("pos_mag_swipe_reader_msr.jpg"));
	private JLabel readerImage = new JLabel(icon);
	private final JLabel lblNewLabel_1 = new JLabel("readerImage");
	private final JButton TestJosh = new JButton();
	
	public LoginView(Model model, Controller controller) {
		super(model, controller);
		setBackground(new Color(255, 255, 255));
		initialize();
		this.setBounds(100, 100, 450, 300);
		lblNewLabel_1.setIcon(new ImageIcon(LoginView.class.getResource("/atm/view/pos_mag_swipe_reader_msr.jpg")));
		lblNewLabel_1.setBounds(400, 20, 244, 241);
		
		add(lblNewLabel_1);
		TestJosh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		TestJosh.setText("Josh Test");
		TestJosh.setPreferredSize(new Dimension(75, 20));
		TestJosh.setHorizontalAlignment(SwingConstants.LEFT);
		TestJosh.setFont(new Font("Dialog", Font.BOLD, 8));
		TestJosh.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		TestJosh.setActionCommand("Login");
		TestJosh.setBounds(95, 11, 75, 20);
		
		add(TestJosh);
	//	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		//this.setExtendedState(MAXIMIZED_BOTH);
		//JPanel login = new JPanel();
		//setBackground(new Color(122, 58, 255));
		setSize(new Dimension(619, 487));
		//this.setTitle("Welcome");
		setVisible(true);
		setLayout(null);
		add(getLblNewLabel());

		add(getTestPhilip());
		add(getCardinfo());
		add(getChrisTest());
		add(getStacieTest());
		add(getPaulTest());
		add(getJoshTest());
		cardinfo.requestFocusInWindow();
		
		cardinfo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				System.out.println(cardinfo.getText()); 
				card.readCard(cardinfo.getText());
				cardinfo.setText("");
			
			}
		});
		cardinfo.requestFocus();
	//	this.add(test);
	//	test.setVisible(true);
	}
	@Override
	public void modelChanged(ModelEvent me) {
		//change to PIN Screen
		PINScreen pin = new PINScreen(getModel(),getController());
		add(pin);
		repaint();
	}

	
	private class Handler implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			((ATMController)getController()).operation(evt.getActionCommand());
			System.out.println(evt.getActionCommand());
		}
	}
	/**
	 * This method initializes TestPhilip	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getTestPhilip() {
		JButton TestPhilip = new JButton();
			TestPhilip = new JButton();
			TestPhilip.setBounds(10, 11, 75, 20);
			TestPhilip.setPreferredSize(new Dimension(75, 20));
			TestPhilip.setFont(new Font("Dialog", Font.BOLD, 8));
			TestPhilip.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			TestPhilip.setHorizontalAlignment(SwingConstants.LEFT);
			TestPhilip.setText("Philip Test");
			TestPhilip.setActionCommand("Login");
			TestPhilip.addActionListener(handler);
			
			TestPhilip.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					cardinfo.setText("%B6391480100325586  ^WATERS/PHILIP T           ^4912120?;6391480100325586=4912120?+202=034903800=00?");
					card.readCard(cardinfo.getText());
				}
			});
		return TestPhilip;
	}

	/**
	 * This method initializes ChrisTest	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getChrisTest() {
		JButton ChrisTest = null;
		if (ChrisTest == null) {
			ChrisTest = new JButton();
			ChrisTest.setBounds(95, 11, 75, 20);
			ChrisTest.setPreferredSize(new Dimension(75, 20));
			ChrisTest.setFont(new Font("Dialog", Font.BOLD, 8));
			ChrisTest.setText("Chris Test");
			ChrisTest.setActionCommand("Login");
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
	
	private JButton getPaulTest() {
		JButton ChrisTest = null;
		if (ChrisTest == null) {
			ChrisTest = new JButton();
			ChrisTest.setBounds(180, 11, 75, 20);
			ChrisTest.setPreferredSize(new Dimension(75, 20));
			ChrisTest.setFont(new Font("Dialog", Font.BOLD, 8));
			ChrisTest.setText("Paul Test");
			ChrisTest.setActionCommand("Login");
			ChrisTest.addActionListener(handler);
			ChrisTest.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					cardinfo.setText("%T6391480001011525  ^BRYSON/PAUL D             ^4912160?;6391480001011525=4912160?+202=064923400=00?");
					card.readCard(cardinfo.getText());
				}
			});
		}
		return ChrisTest;
	}
	
	private JButton getJoshTest() {
		JButton ChrisTest = null;
		if (ChrisTest == null) {
			ChrisTest = new JButton();
			ChrisTest.setBounds(95, 41, 75, 20);
			ChrisTest.setPreferredSize(new Dimension(75, 20));
			ChrisTest.setFont(new Font("Dialog", Font.BOLD, 8));
			ChrisTest.setText("Josh Test");
			ChrisTest.setActionCommand("Login");
			ChrisTest.addActionListener(handler);
			ChrisTest.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					cardinfo.setText("%T6391480001054426  ^GENTON/JOSHUA CLINTON     ^4912160?;6391480001054426=4912160?+202=423535400=00?");
					card.readCard(cardinfo.getText());
				}
			});
		}
		return ChrisTest;
	}
	
	private JButton getStacieTest() {
		JButton ChrisTest = null;
		if (ChrisTest == null) {
			ChrisTest = new JButton();
			ChrisTest.setBounds(10, 41, 75, 20);
			ChrisTest.setPreferredSize(new Dimension(75, 20));
			ChrisTest.setFont(new Font("Dialog", Font.BOLD, 8));
			ChrisTest.setText("Stacie Test");
			ChrisTest.setActionCommand("Login");
			ChrisTest.addActionListener(handler);
			ChrisTest.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					cardinfo.setText("%B6391480001046430  ^CHRISTENSEN/STACIE L      ^4912120?;6391480001046430=4912120?+202=058728800=00?");
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
			cardinfo.setLocation(250, 15);
			cardinfo.setBackground(Color.lightGray);
			cardinfo.setPreferredSize(new Dimension(0, 0));
			cardinfo.requestFocus();
			cardinfo.addAncestorListener(new RequestFocusListener());
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
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("<html>Group 1 ATM<br>Scan Card to Continue</html>");
			lblNewLabel.setLocation(0, 0);
			lblNewLabel.setSize(450, 300);
			lblNewLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
			lblNewLabel.setAlignmentY(java.awt.Component.CENTER_ALIGNMENT);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 24));
			lblNewLabel.setForeground(new Color(255, 0, 0));
		}
		return lblNewLabel;
	}
}
