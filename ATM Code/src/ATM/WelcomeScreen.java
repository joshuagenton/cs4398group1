package ATM;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Color;
import java.util.Scanner;

import javax.swing.JButton;

public class WelcomeScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;  //  @jve:decl-index=0:visual-constraint="194,129"
	private JButton gotoPin = null;

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
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setContentPane(getGotoPin());
		this.setBackground(new Color(0, 85, 255));
		this.setSize(new Dimension(392, 150));
		this.setTitle("Welcome");
		this.setVisible(true);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
		}
		return jContentPane;
	}

	/**
	 * This method initializes gotoPin	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getGotoPin() {
		if (gotoPin == null) {
			gotoPin = new JButton();
			gotoPin.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String cardRead;
					Scanner in = new Scanner(System.in);
					cardRead = in.nextLine();
					System.out.print(cardRead);
				}
			});
		}
		return gotoPin;
	}

}  //  @jve:decl-index=0:visual-constraint="214,10"
