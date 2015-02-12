package ATM;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import javax.swing.JTextField;


public class WelcomeScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField cardinfo = null;
	private stuff test = new stuff();
	public ATMUML.CardReader card = new ATMUML.CardReader();
	
	public String getText () {
		return cardinfo.getText();
		
	}
	class stuff extends JPanel {
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics shapes) {
			//super.paint(shapes);
			shapes.setColor(Color.BLUE);
			shapes.setFont(new Font("TimesRoman",Font.BOLD,48));
			shapes.drawString("Welcome to First National Group1 ATM", getWidth()/4-25, getHeight()/3);
			shapes.drawString("Please slide your card to continue", getWidth()/4+25, getHeight()/3+50);
		}
	}
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
		this.setBackground(new Color(0, 85, 255));
		this.add(getCardinfo());
		this.setSize(new Dimension(527, 265));
		//this.setTitle("Welcome");
		this.setVisible(true);
		add(test);
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
			cardinfo.setSize(new Dimension(527, 265));
		}
		return cardinfo;
	}
    }  //  @jve:decl-index=0:visual-constraint="0,57"
  //  @jve:decl-index=0:visual-constraint="18,-31"
