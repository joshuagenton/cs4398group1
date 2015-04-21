package atm.view;

import java.awt.Cursor;
import java.awt.Rectangle;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JPasswordField;

import java.awt.Color;

import atm.controller.ATMController;
import atm.controller.Controller;
import atm.controller.IdleTimeController;
import atm.model.Model;
import atm.model.ModelEvent;
import atm.model.ATMCoreModel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The PIN Screen allows the user to enter their 4-digit PIN that is 
 * associated to their card.
 * 
 * @author Chris Wells
 * @since 2015-03-25
 */
public class PINScreen extends JFrameView {
	
	private static final long serialVersionUID = 1L;
	private JPasswordField PIN = null;
	private JButton Cancel = null;
	private JButton Clear = null;
	private JButton Enter = null;
	private String firstname = null;
	private String lastname = null;
	private JLabel NameInfo = null;
	Icon icon = new ImageIcon(this.getClass().getResource("trap.gif"));
	private JLabel aniImage = new JLabel(icon);
	static final int MAX_CHARS = 4;
	private Handler handler = new Handler();

	
	/**
	 * Constructor.
	 * 
	 * @param model the ATMCoreModel
	 * @param controller the ATMController
	 */
	public PINScreen(Model model, Controller controller) {
		super(model, controller);
		setBackground(new Color(255, 255, 255));
		int i = 0;
		Boolean mark = false;
		String first = null, last = null;
		String name = ((ATMCoreModel)getModel()).getName();
		for (char c : name.toCharArray())
		{
			if(mark == false)
			{
				if (c == '/')
				{
					mark = true;
					last = name.substring(0, i);
				}
				i++;
			}
			else
				first = name.substring(i);
			
		}
		firstname = first.replaceAll("\\s+","");
		lastname= last.replaceAll("\\s+", "");
		initialize();
	}
	
	/**
	 * The Handler that handles the user's actions.
	 * 
	 * @author Chris Wells
	 *
	 */
	private class Handler implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			((ATMController)getController()).operation(evt.getActionCommand());
			IdleTimeController.runTimer((ATMController)getController());
		}
	}
	
	// Button Getters
	
	private void numPad() {
		for (Integer i = 0; i<10 ; i++){
			final Integer number = i;
			JButton num = new JButton();
			num.setFont(new Font("Tahoma", Font.PLAIN, 24));
			num.setText(number.toString());
			if (i == 0)
				num.setBounds(new Rectangle(638, 383, 100, 100));
			else
				num.setBounds(new Rectangle(538+(i-1)%3*100, 83+(i-1)/3*100, 100, 100));
			num.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PIN.setText(String.valueOf(PIN.getPassword())+number);
					IdleTimeController.runTimer((ATMController)getController());
				}
			});
			add(num);
		}
		add(getClear());
		add(getEnter());
	}

	/**
	 * The initialization of the view.
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		this.setLayout(null);
		this.setBounds(new Rectangle(0, 0, 1024, 768));
		this.add(getPIN(), null);
		this.add(getCancel(), null);
		this.add(getNameInfo(), null);
		numPad();
		aniImage.setBounds(50, 400, 800, 500);
		this.add(aniImage);
	}

	/**
	 * Gets the user's PIN.
	 * 
	 * @return the PIN
	 */
	private JPasswordField getPIN() {
		if (PIN == null) {
			PIN = new JPasswordField();
			PIN.setBounds(new Rectangle(538, 27, 300, 45));
			PIN.setFont(new Font("Dialog", Font.PLAIN, 36));

		}
		return PIN;
	}

	// Button Getters
	
	private JButton getCancel() {
		if (Cancel == null) {
			Cancel = new JButton();
			Cancel.setBackground(Color.RED);
			Cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			Cancel.setFont(new Font("Tahoma", Font.PLAIN, 40));
			Cancel.setBounds(1025, 537, 275, 106);
			Cancel.setText("Cancel");
			Cancel.setActionCommand("Cancel");
			Cancel.addActionListener(handler);
			Cancel.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					IdleTimeController.runTimer((ATMController)getController());
				}
			});
		}
		return Cancel;
	}

	private JButton getClear() {
		if (Clear == null) {
			Clear = new JButton();
			Clear.setFont(new Font("Tahoma", Font.PLAIN, 21));
			Clear.setBounds(new Rectangle(538, 383, 100, 100));
			Clear.setActionCommand("CancelScreen()");
			Clear.setText("Clear");
			Clear.setBackground(Color.yellow);
			Clear.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PIN.setText("");
					IdleTimeController.runTimer((ATMController)getController());
					
				}
			});
		}
		return Clear;
	}

	private JButton getEnter() {
		if (Enter == null) {
			Enter = new JButton();
			Enter.setFont(new Font("Tahoma", Font.PLAIN, 21));
			Enter.setBounds(new Rectangle(738, 383, 100, 100));
			Enter.setActionCommand("Enter");
			Enter.setText("Enter");
			Enter.setBackground(Color.green);
			
			Enter.addActionListener(handler);
			Enter.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					((ATMController)getController()).login(PIN.getPassword());
					IdleTimeController.runTimer((ATMController)getController());
				}
			});
		}
		return Enter;
	}

	private JLabel getNameInfo() {
		if (NameInfo == null) {
			NameInfo = new JLabel();
			NameInfo.setBounds(67, 83, 407, 174);
			NameInfo.setBackground(new Color(238, 238, 238));
			NameInfo.setFont(new Font("Californian FB", Font.PLAIN, 24));
			NameInfo.setText("Welcome, " + firstname + " " + lastname);
		}
		return NameInfo;
	}

	/**
	 * modelChanged - do nothing.
	 */
	@Override
	public void modelChanged(ModelEvent me) {}
}
