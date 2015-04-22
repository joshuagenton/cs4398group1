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
import java.awt.GridBagLayout;
import java.awt.Insets;

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
	//Icon icon = new ImageIcon(this.getClass().getResource("trap.gif"));
	//private JLabel aniImage = new JLabel(icon);
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
		firstname = first; //.replaceAll("\\s+","");
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
			GridBagConstraints gbc_num = new GridBagConstraints();
			final Integer number = i;
			JButton num = new JButton();
			num.setFont(new Font("Tahoma", Font.PLAIN, 24));
			num.setText(number.toString());
			if (i == 0){
				gbc_num.fill = GridBagConstraints.BOTH;
				gbc_num.gridx = 4;
				gbc_num.gridy = 4;
				gbc_num.insets = new Insets(0, 0, 0, 0);
			}
			else{
				gbc_num.fill = GridBagConstraints.BOTH;
				gbc_num.insets = new Insets(0, 0, 0, 0);
				gbc_num.gridx = (i-1)%3 + 3;
				gbc_num.gridy = (i-1)/3 + 1;
			}
			num.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PIN.setText(String.valueOf(PIN.getPassword())+number);
					IdleTimeController.runTimer((ATMController)getController());
				}
			});
			this.add(num, gbc_num);
		}
	}

	/**
	 * The initialization of the view.
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		this.setBounds(new Rectangle(0, 0, 1024, 768));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{300, 100, 110, 110, 110, 100, 100, 100, 100};
		gridBagLayout.rowHeights = new int[]{100, 100, 100, 100, 100, 100, 100};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0, 0, 0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		GridBagConstraints gbc_PIN = new GridBagConstraints();
		gbc_PIN.fill = GridBagConstraints.HORIZONTAL;
		gbc_PIN.anchor = GridBagConstraints.SOUTH;
		gbc_PIN.insets = new Insets(0, 0, 5, 5);
		gbc_PIN.gridwidth = 3;
		gbc_PIN.gridx = 3;
		gbc_PIN.gridy = 0;
		this.add(getPIN(), gbc_PIN);
		GridBagConstraints gbc_NameInfo = new GridBagConstraints();
		gbc_NameInfo.gridwidth = 2;
		gbc_NameInfo.gridheight = 2;
		gbc_NameInfo.fill = GridBagConstraints.BOTH;
		gbc_NameInfo.insets = new Insets(0, 0, 5, 5);
		gbc_NameInfo.gridx = 0;
		gbc_NameInfo.gridy = 3;
		this.add(getNameInfo(), gbc_NameInfo);
		GridBagConstraints gbc_Clear = new GridBagConstraints();
		gbc_Clear.fill = GridBagConstraints.BOTH;
		gbc_Clear.insets = new Insets(0, 0, 0, 0);
		gbc_Clear.gridx = 3;
		gbc_Clear.gridy = 4;
		add(getClear(), gbc_Clear);
		GridBagConstraints gbc_Enter = new GridBagConstraints();
		gbc_Enter.fill = GridBagConstraints.BOTH;
		gbc_Enter.insets = new Insets(0, 0, 0, 0);
		gbc_Enter.gridx = 5;
		gbc_Enter.gridy = 4;
		add(getEnter(), gbc_Enter);
		GridBagConstraints gbc_Cancel = new GridBagConstraints();
		gbc_Cancel.anchor = GridBagConstraints.SOUTH;
		gbc_Cancel.gridwidth = 3;
		gbc_Cancel.insets = new Insets(0, 0, 0, 0);
		gbc_Cancel.gridx = 3;
		gbc_Cancel.gridy = 5;
		this.add(getCancel(), gbc_Cancel);
		numPad();
		//aniImage.setBounds(50, 400, 800, 500);
		//this.add(aniImage);
	}

	/**
	 * Gets the user's PIN.
	 * 
	 * @return the PIN
	 */
	private JPasswordField getPIN() {
		if (PIN == null) {
			PIN = new JPasswordField();
			PIN.setFont(new Font("Dialog", Font.PLAIN, 36));

		}
		return PIN;
	}

	// Button Getters
	
	private JButton getCancel() {
		if (Cancel == null) {
			Icon cancelIcon = new ImageIcon(this.getClass().getResource("/atm/view/cancel.jpg"));
			Cancel = new JButton(cancelIcon);
			Cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			//Cancel.setText("Cancel");
			Cancel.setBorderPainted(false);
			Cancel.setContentAreaFilled(false);
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
			NameInfo.setBackground(new Color(238, 238, 238));
			NameInfo.setFont(new Font("Californian FB", Font.PLAIN, 24));
			NameInfo.setText("    Welcome, " + firstname + " " + lastname);
		}
		return NameInfo;
	}

	/**
	 * modelChanged - do nothing.
	 */
	@Override
	public void modelChanged(ModelEvent me) {}
}
