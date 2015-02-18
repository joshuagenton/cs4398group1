/**
 * 
 */
package ATMUML;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author CSWells
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Screens extends JFrame{

	private static final long serialVersionUID = 1L;
	private static CardReader readCard = new CardReader();
	private static String pinNum = null;

	public Screens() {
		super();
		initialize();
	}
	public static void main (String[] args) throws IOException, InterruptedException {
		Screens mainWindow = new Screens();
		ATM.WelcomeScreen welcome = new ATM.WelcomeScreen();
		mainWindow.add(welcome);
		mainWindow.setVisible(true);
		while (welcome.card.done == false) {
			Thread.sleep(500);
		}
		welcome.setVisible(false);
		readCard = welcome.card;
		welcome = null;
		final ATM.PINScreen pin = new ATM.PINScreen(readCard.track1_cardholder);
		mainWindow.add(pin);
		pin.setVisible(true);
		mainWindow.repaint();
		while (pin.getPinNum() == null)
		{
			Thread.sleep(500);
		}
		pin.setVisible(false);
		
		if(pin.getPinNum().toString() == "Cancel") {
			pinNum = pin.getPinNum().toString();
		}
		else {
			pinNum = pin.getPinNum().toString();
		}

		System.out.println(pin.getPinNum());
		System.exit(0);

	}
	
	private void initialize() {
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setBackground(new Color(0, 85, 255));
		this.setSize(new Dimension(527, 265));
		this.setTitle("Welcome");
		this.setVisible(true);
	}
}


