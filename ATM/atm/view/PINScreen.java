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

public class PINScreen extends JFrameView {
	
	private static final long serialVersionUID = 1L;
	private JButton num1 = null;
	private JButton num2 = null;
	private JButton num3 = null;
	private JButton num4 = null;
	private JButton num5 = null;
	private JButton num6 = null;
	private JButton num7 = null;
	private JButton num8 = null;
	private JButton num9 = null;
	private JButton num0 = null;
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
	 * This is the default constructor
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
	
	private class Handler implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			((ATMController)getController()).operation(evt.getActionCommand());
			IdleTimeController.runTimer((ATMController)getController());
		}
	}
	
	private JButton getNum1() {
		if (num1 == null) {
			num1 = new JButton();
			num1.setFont(new Font("Tahoma", Font.PLAIN, 24));
			num1.setText("1");
			num1.setBounds(new Rectangle(538, 83, 100, 100));
			num1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PIN.setText(String.valueOf(PIN.getPassword())+"1");
					IdleTimeController.runTimer((ATMController)getController());
				}
			});
		}
		return num1;
	}

	/**
	 * This method initializes num4	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNum4() {
		if (num4 == null) {
			num4 = new JButton();
			num4.setFont(new Font("Tahoma", Font.PLAIN, 24));
			num4.setText("4");
			num4.setBounds(new Rectangle(538, 183, 100, 100));
			num4.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PIN.setText(String.valueOf(PIN.getPassword())+"4");
					IdleTimeController.runTimer((ATMController)getController());
				}
			});
		}
		return num4;
	}

	/**
	 * This method initializes num2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNum2() {
		if (num2 == null) {
			num2 = new JButton();
			num2.setFont(new Font("Tahoma", Font.PLAIN, 24));
			num2.setText("2");
			num2.setBounds(new Rectangle(638, 83, 100, 100));
			num2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PIN.setText(String.valueOf(PIN.getPassword())+"2");
					IdleTimeController.runTimer((ATMController)getController());
				}
			});
		}
		return num2;
	}

	/**
	 * This method initializes num3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNum3() {
		if (num3 == null) {
			num3 = new JButton();
			num3.setFont(new Font("Tahoma", Font.PLAIN, 24));
			num3.setText("3");
			num3.setBounds(new Rectangle(738, 83, 100, 100));
			num3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PIN.setText(String.valueOf(PIN.getPassword())+"3");
					IdleTimeController.runTimer((ATMController)getController());
				}
			});
		}
		return num3;
	}

	/**
	 * This method initializes num5	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNum5() {
		if (num5 == null) {
			num5 = new JButton();
			num5.setFont(new Font("Tahoma", Font.PLAIN, 24));
			num5.setText("5");
			num5.setBounds(new Rectangle(638, 183, 100, 100));
			num5.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PIN.setText(String.valueOf(PIN.getPassword())+"5");
					IdleTimeController.runTimer((ATMController)getController());
				}
			});
		}
		return num5;
	}

	/**
	 * This method initializes num6	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNum6() {
		if (num6 == null) {
			num6 = new JButton();
			num6.setFont(new Font("Tahoma", Font.PLAIN, 24));
			num6.setText("6");
			num6.setBounds(new Rectangle(738, 183, 100, 100));
			num6.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PIN.setText(String.valueOf(PIN.getPassword())+"6");
					IdleTimeController.runTimer((ATMController)getController());
				}
			});
		}
		return num6;
	}

	/**
	 * This method initializes num7	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNum7() {
		if (num7 == null) {
			num7 = new JButton();
			num7.setFont(new Font("Tahoma", Font.PLAIN, 24));
			num7.setText("7");
			num7.setBounds(new Rectangle(538, 283, 100, 100));
			num7.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PIN.setText(String.valueOf(PIN.getPassword())+"7");
					IdleTimeController.runTimer((ATMController)getController());
				}
			});
		}
		return num7;
	}

	/**
	 * This method initializes num8	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNum8() {
		if (num8 == null) {
			num8 = new JButton();
			num8.setFont(new Font("Tahoma", Font.PLAIN, 24));
			num8.setText("8");
			num8.setBounds(new Rectangle(638, 283, 100, 100));
			num8.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PIN.setText(String.valueOf(PIN.getPassword())+"8");
					IdleTimeController.runTimer((ATMController)getController());
				}
			});
		}
		return num8;
	}

	/**
	 * This method initializes num9	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNum9() {
		if (num9 == null) {
			num9 = new JButton();
			num9.setFont(new Font("Tahoma", Font.PLAIN, 24));
			num9.setText("9");
			num9.setBounds(new Rectangle(738, 283, 100, 100));
			num9.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PIN.setText(String.valueOf(PIN.getPassword())+"9");
					IdleTimeController.runTimer((ATMController)getController());
				}
			});
		}
		return num9;
	}

	/**
	 * This method initializes num0	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNum0() {
		if (num0 == null) {
			num0 = new JButton();
			num0.setFont(new Font("Tahoma", Font.PLAIN, 24));
			num0.setText("0");
			num0.setBounds(new Rectangle(638, 383, 100, 100));
			num0.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PIN.setText(String.valueOf(PIN.getPassword())+"0");
					IdleTimeController.runTimer((ATMController)getController());
				}
			});
		}
		return num0;
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
		this.add(getPIN(), null);
		this.add(getNum1(), null);
		this.add(getNum2(), null);
		this.add(getNum4(), null);
		this.add(getNum3(), null);
		this.add(getNum6(), null);
		this.add(getNum8(), null);
		this.add(getNum5(), null);
		this.add(getNum0(), null);
		this.add(getNum9(), null);
		this.add(getNum7(), null);
		this.add(getCancel(), null);
		this.add(getClear(), null);
		this.add(getEnter(), null);
		this.add(getNameInfo(), null);
		
		aniImage.setBounds(50, 400, 800, 500);
		this.add(aniImage);
	}

	/**
	 * This method initializes PIN	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getPIN() {
		if (PIN == null) {
			PIN = new JPasswordField();
			PIN.setBounds(new Rectangle(538, 27, 300, 45));
			PIN.setFont(new Font("Dialog", Font.PLAIN, 36));

		}
		return PIN;
	}

	/**
	 * This method initializes Cancel	
	 * 	
	 * @return javax.swing.JButton	
	 */
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

	/**
	 * This method initializes Clear	
	 * 	
	 * @return javax.swing.JButton	
	 */
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

	/**
	 * This method initializes Enter	
	 * 	
	 * @return javax.swing.JButton	
	 */
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

	/**
	 * This method initializes NameInfo	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
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


	@Override
	public void modelChanged(ModelEvent me) {
		// TODO Auto-generated method stub
		
	}
}  //  @jve:decl-index=0:visual-constraint="-518,13"
