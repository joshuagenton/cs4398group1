package ATM;

import java.awt.GridBagConstraints;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

public class CancelScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JTextArea CancelledAlert = null;  //  @jve:decl-index=0:visual-constraint="232,30"

	/**
	 * This is the default constructor
	 */
	public CancelScreen() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		this.setLayout(null);
		this.setBounds(new Rectangle(0, 0, 1024, 768));
			
	}

	/**
	 * This method initializes CancelledAlert	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	JTextArea getCancelledAlert() {
		if (CancelledAlert == null) {
			CancelledAlert = new JTextArea();
			CancelledAlert.setSize(new Dimension(366, 172));
			CancelledAlert.setEditable(false);
			CancelledAlert.setFont(new Font("Gisha", Font.BOLD, 36));
			CancelledAlert.setBackground(Color.red);
			CancelledAlert.setText("\n The transaction has \n     been cancelled");
		}
		return CancelledAlert;
	}

}
