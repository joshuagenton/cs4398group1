package atm.view;

import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;

public class PINScreen extends JPanel {

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
	private String pinNumber = null;
	private String firstname = null;
	private String lastname = null;
	private JTextPane NameInfo = null;
	static final int MAX_CHARS = 4;
	private char[] pinNum = null;  //  @jve:decl-index=0:
	/**
	 * This is the default constructor
	 */
	public PINScreen() {
		super();
		initialize();
	}
	
	public PINScreen(String name) {
		
		super();
		int i = 0;
		Boolean mark = false;
		String first = null, last = null;
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
	
	private JButton getNum1() {
		if (num1 == null) {
			num1 = new JButton();
			num1.setText("1");
			num1.setBounds(new Rectangle(538, 83, 100, 100));
			num1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PIN.setText(String.valueOf(PIN.getPassword())+"1");
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
			num4.setText("4");
			num4.setBounds(new Rectangle(538, 183, 100, 100));
			num4.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PIN.setText(String.valueOf(PIN.getPassword())+"4");
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
			num2.setText("2");
			num2.setBounds(new Rectangle(638, 83, 100, 100));
			num2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PIN.setText(String.valueOf(PIN.getPassword())+"2");
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
			num3.setText("3");
			num3.setBounds(new Rectangle(738, 83, 100, 100));
			num3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PIN.setText(String.valueOf(PIN.getPassword())+"3");
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
			num5.setText("5");
			num5.setBounds(new Rectangle(638, 183, 100, 100));
			num5.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PIN.setText(String.valueOf(PIN.getPassword())+"5");
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
			num6.setText("6");
			num6.setBounds(new Rectangle(738, 183, 100, 100));
			num6.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PIN.setText(String.valueOf(PIN.getPassword())+"6");
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
			num7.setText("7");
			num7.setBounds(new Rectangle(538, 283, 100, 100));
			num7.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PIN.setText(String.valueOf(PIN.getPassword())+"7");
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
			num8.setText("8");
			num8.setBounds(new Rectangle(638, 283, 100, 100));
			num8.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PIN.setText(String.valueOf(PIN.getPassword())+"8");
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
			num9.setText("9");
			num9.setBounds(new Rectangle(738, 283, 100, 100));
			num9.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PIN.setText(String.valueOf(PIN.getPassword())+"9");
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
			num0.setText("0");
			num0.setBounds(new Rectangle(638, 383, 100, 100));
			num0.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PIN.setText(String.valueOf(PIN.getPassword())+"0");
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
	}

	/**
	 * This method initializes PIN	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getPIN() {
		if (PIN == null) {
			PIN = new JPasswordField();
			PIN.setBounds(new Rectangle(299, 324, 158, 45));
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
			Cancel.setBounds(new Rectangle(870, 87, 136, 51));
			Cancel.setBackground(Color.red);
			Cancel.setText("Cancel");
			Cancel.setActionCommand("CancelScreen()");
			Cancel.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pinNum = "Cancel".toCharArray();
					
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
			Clear.setBounds(new Rectangle(870, 172, 136, 51));
			Clear.setActionCommand("CancelScreen()");
			Clear.setText("Clear");
			Clear.setBackground(Color.yellow);
			Clear.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PIN.setText("");
					
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
			Enter.setBounds(new Rectangle(870, 324, 136, 51));
			Enter.setActionCommand("CancelScreen()");
			Enter.setText("Enter");
			Enter.setBackground(Color.green);
			Enter.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pinNum = PIN.getPassword();		
				}
			});
		}
		return Enter;
	}

	public String getPinNumber() {
		return pinNumber;
	}

	/**
	 * This method initializes NameInfo	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getNameInfo() {
		if (NameInfo == null) {
			NameInfo = new JTextPane();
			NameInfo.setBounds(new Rectangle(120, 83, 306, 174));
			NameInfo.setBackground(new Color(238, 238, 238));
			NameInfo.setFont(new Font("Californian FB", Font.PLAIN, 24));
			NameInfo.setText("Welcome, " + firstname + " " + lastname);
		}
		return NameInfo;
	}


	public char[] getPinNum() {
		return pinNum;
	}

}  //  @jve:decl-index=0:visual-constraint="-518,13"
