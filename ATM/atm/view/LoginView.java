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

import atm.controller.ATMController;
import atm.controller.CardReaderController;
import atm.controller.Controller;
import atm.controller.IdleTimeController;
import atm.model.Model;
import atm.model.ModelEvent;

import javax.swing.JLabel;
import javax.swing.ImageIcon;


/**
 * The LoginView is what the user sees when logging into the ATM.
 * 
 * @author Chris Wells
 * @since 2015-03-25
 */
public class LoginView extends JFrameView {

	private static final long serialVersionUID = 1L;
	public JTextField cardinfo = null;
	public CardReaderController card = new CardReaderController(getModel()); 
	private Handler handler = new Handler();
	private JLabel lblWelcome;
	private JLabel readerImage;
	private JLabel lblScanCard;
	
	/**
	 * Constructor.
	 * 
	 * @param model the ATMCoreModel
	 * @param controller the ATMController
	 */
	public LoginView(Model model, Controller controller) {
		super(model, controller);
		//setBackground(new Color(255, 255, 255,0));
		initialize();
		this.setBounds(100, 100, 800, 600);
		IdleTimeController.cancelTimer();
	}

	
	/**
	 * Initializes this.
	 */
	private void initialize() {
		//setSize(new Dimension(835, 518));
		
		setLayout(null);
	    add(getLblWelcome());
	    add(getLblReaderImage());
	    add(getLblScanCard());

		add(getTestPhilip());
		add(getLblScanCard());
		add(getCardinfo());
		add(getChrisTest());
		add(getStacieTest());
		add(getPaulTest());
		add(getJoshTest());
		add(getNoneTest());
		add(getManyTest());
		add(getBadTest());
		cardinfo.requestFocusInWindow();
		
		cardinfo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				System.out.println(cardinfo.getText()); 
				card.readCard(cardinfo.getText());
				cardinfo.setText("");
			
			}
		});
		cardinfo.requestFocus();
	}

	@Override
	public void modelChanged(ModelEvent me) {
		//change to PIN Screen
		PINScreen pin = new PINScreen(getModel(),getController());
		add(pin);
		repaint();
	}

	/**
	 * Handles user's actions.
	 * 
	 * @author Chris Wells
	 *
	 */
	private class Handler implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			((ATMController)getController()).operation(evt.getActionCommand());
			System.out.println(evt.getActionCommand());
		}
	}

	/**
	 * This method initializes NoneTest	
	 * 	
	 * @return button for use
	 */
	private JButton getNoneTest() {
		JButton TestNone = null;
		if (TestNone == null) {
			TestNone = new JButton();
			TestNone.setBounds(270, 41, 75, 20);
			TestNone.setPreferredSize(new Dimension(75, 20));
			TestNone.setFont(new Font("Dialog", Font.BOLD, 8));
			TestNone.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			TestNone.setHorizontalAlignment(SwingConstants.LEFT);
			TestNone.setText("None Test");
			TestNone.setActionCommand("Login");
			TestNone.addActionListener(handler);
			
			TestNone.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					cardinfo.setText("%B0000000000000001  ^ACCOUNTS/NONE           ^4912120?;6391480100325586=4912120?+202=034903800=00?");
					card.readCard(cardinfo.getText());
				}
			});
		
		}
		return TestNone;
	}

	/**
	 * This method initializes ManyTest	
	 * 	
	 * @return button for use
	 */
	private JButton getManyTest() {
		JButton TestMany = null;
		if (TestMany == null) {
			TestMany = new JButton();
			TestMany.setBounds(270, 11, 75, 20);
			TestMany.setPreferredSize(new Dimension(75, 20));
			TestMany.setFont(new Font("Dialog", Font.BOLD, 8));
			TestMany.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			TestMany.setHorizontalAlignment(SwingConstants.LEFT);
			TestMany.setText("Many Test");
			TestMany.setActionCommand("Login");
			TestMany.addActionListener(handler);
			
			TestMany.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					cardinfo.setText("%B0000000000000002  ^ACCOUNTS/MANY           ^4912120?;6391480100325586=4912120?+202=034903800=00?");
					card.readCard(cardinfo.getText());
				}
			});
		}
		return TestMany;
	}
	/**
	 * This method initializes BadTest
	 * 	
	 * @return button for use
	 */
	private JButton getBadTest() {
		JButton TestMany = null;
		if (TestMany == null) {
			TestMany = new JButton();
			TestMany.setBounds(355, 11, 75, 20);
			TestMany.setPreferredSize(new Dimension(75, 20));
			TestMany.setFont(new Font("Dialog", Font.BOLD, 8));
			TestMany.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			TestMany.setHorizontalAlignment(SwingConstants.LEFT);
			TestMany.setText("Bad Test");
			TestMany.setActionCommand("Login");
			TestMany.addActionListener(handler);
			
			TestMany.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					cardinfo.setText("%B0002  ^Test/BAD           ^4912120?;6391480100325586=4912120?+202=034903800=00?");
					card.readCard(cardinfo.getText());
				}
			});
		}
		return TestMany;
	}
	/**
	 * This method initializes TestPhilip	
	 * 	
	 * @return button for use
	 */
	private JButton getTestPhilip() {
		JButton TestPhilip = null;
		if (TestPhilip == null) {
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
		}
		return TestPhilip;
	}

	/**
	 * This method initializes TestChris
	 * 	
	 * @return button for use
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
	
	/**
	 * This method initializes TestPaul
	 * 	
	 * @return button for use
	 */
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
	
	/**
	 * This method initializes TestJosh
	 * 	
	 * @return button for use
	 */
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
	
	/**
	 * This method initializes TestStacie	
	 * 	
	 * @return button for use
	 */
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
	
	// Getters / Setters
	
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

	private JLabel getLblWelcome() {
		if (lblWelcome == null) {
			lblWelcome = new JLabel("Welcome to Texas State Bank's ATM");
			lblWelcome.setHorizontalAlignment(SwingConstants.LEFT);
			lblWelcome.setBackground(Color.CYAN);
			lblWelcome.setSize(650, 150);
			lblWelcome.setLocation(296, 203);
			lblWelcome.setFont(new Font("Gill Sans MT Ext Condensed Bold", Font.PLAIN, 75));
			lblWelcome.setForeground(Color.BLACK);
		}
		return lblWelcome;
	}
	
	private JLabel getLblReaderImage() {
		if (readerImage == null) {
			readerImage = new JLabel();
			readerImage.setIcon(new ImageIcon(LoginView.class.getResource("/atm/view/pos_mag_swipe_reader_msr.png")));
			readerImage.setBounds(449, 389, 244, 241);	
		}
		return readerImage;
	}

		
	private JLabel getLblScanCard() {
		if (lblScanCard == null) {
			lblScanCard = new JLabel("Swipe card to continue...");
			lblScanCard.setBounds(389, 328, 348, 50);
			lblScanCard.setFont(new Font("Gill Sans MT Condensed", Font.PLAIN, 28));
			lblScanCard.setForeground(Color.RED);
		}
		return lblScanCard;
	}
}
