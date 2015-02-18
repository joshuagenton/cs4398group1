package ATM;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.ComponentOrientation;
import javax.swing.SwingConstants;


public class WelcomeScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTextField cardinfo = null;
//	private stuff test = null;
	public ATMUML.CardReader card = new ATMUML.CardReader();  //  @jve:decl-index=0:
	private JButton TestPhilip = null;
	private JButton ChrisTest = null;
	
	public String getText () {
		return cardinfo.getText();
		
	}
	//class stuff extends JPanel {
	//	private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics shapes) {
			//super.paint(shapes);
			shapes.setColor(Color.BLUE);
			shapes.setFont(new Font("TimesRoman",Font.BOLD,48));
			shapes.drawString("Welcome to First National Group1 ATM", getWidth()/4-25, getHeight()/3);
			shapes.drawString("Please slide your card to continue", getWidth()/4+25, getHeight()/3+50);
		}
//	}
	/**
	 * This is the default constructor
	 */
	public WelcomeScreen() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		//this.setExtendedState(MAXIMIZED_BOTH);
		this.setBackground(new Color(122, 58, 255));
		this.setSize(new Dimension(527, 265));
		//this.setTitle("Welcome");
		this.setVisible(true);

		this.add(getTestPhilip(), null);
		this.add(getCardinfo(), getCardinfo().getName());
		this.add(getChrisTest(), null);
		cardinfo.requestFocusInWindow();
		
		cardinfo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				System.out.println(cardinfo.getText()); // TODO Auto-generated Event stub actionPerformed()
				try {
					card.read(cardinfo.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cardinfo.setText("");
			
			}
		});
		paintComponents(null);
	//	this.add(test);
	//	test.setVisible(true);
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
			cardinfo.setSize(new Dimension(527, 265));

		}
		return cardinfo;
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
			TestPhilip.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					cardinfo.setText("%B6391480100325586  ^WATERS/PHILIP T           ^4912120?;6391480100325586=4912120?+202=034903800=00?");
					try {
						card.read(cardinfo.getText());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
			ChrisTest.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					cardinfo.setText("%T6391480001052388  ^WELLS/CHRISTOPHER         ^4912160?;6391480001052388=4912160?+202=411558900=00?");
					try {
						card.read(cardinfo.getText());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return ChrisTest;
	}
    }  //  @jve:decl-index=0:visual-constraint="33,21"
  //  @jve:decl-index=0:visual-constraint="18,-31"
