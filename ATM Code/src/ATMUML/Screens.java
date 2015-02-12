/**
 * 
 */
package ATMUML;

import java.awt.Color;
import java.awt.Dimension;
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
	public static CardReader readCard = new CardReader();

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
		readCard = welcome.card;
		
		//System.in.read();
		//readCard.read();
		System.out.print("OUTPUT");
		System.out.println(readCard.track1);
	}
	private void initialize() {
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setBackground(new Color(0, 85, 255));
		this.setSize(new Dimension(527, 265));
		this.setTitle("Welcome");
		this.setVisible(true);
	}
}


