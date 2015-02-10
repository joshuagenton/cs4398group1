package ATM;

import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import javax.swing.JPasswordField;
import java.awt.Color;

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

	/**
	 * This is the default constructor
	 */
	public PINScreen() {
		super();
		initialize();
	}
	
	private JButton getNum1() {
		if (num1 == null) {
			num1 = new JButton();
			num1.setText("1");
			num1.setBounds(new Rectangle(400, 40, 100, 100));
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
			num4.setBounds(new Rectangle(400, 140, 100, 100));
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
			num2.setBounds(new Rectangle(500, 40, 100, 100));
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
			num3.setBounds(new Rectangle(600, 40, 100, 100));
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
			num5.setBounds(new Rectangle(500, 140, 100, 100));
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
			num6.setBounds(new Rectangle(600, 140, 100, 100));
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
			num7.setBounds(new Rectangle(400, 240, 100, 100));
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
			num8.setBounds(new Rectangle(500, 240, 100, 100));
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
			num9.setBounds(new Rectangle(600, 240, 100, 100));
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
			num0.setBounds(new Rectangle(500, 340, 100, 100));
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
	}

	/**
	 * This method initializes PIN	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getPIN() {
		if (PIN == null) {
			PIN = new JPasswordField();
			PIN.setBounds(new Rectangle(289, 201, 70, 20));
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
			Cancel.setBounds(new Rectangle(79, 136, 136, 51));
			Cancel.setBackground(Color.red);
			Cancel.setText("Cancel");
			Cancel.setActionCommand("CancelScreen()");
			Cancel.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					CancelScreen cancel = new CancelScreen();
					cancel.getCancelledAlert();
					cancel.setVisible(true);
					
				}
			});
		}
		return Cancel;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
