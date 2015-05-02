package simpleAccount.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import simpleAccount.controller.Controller;
import simpleAccount.model.Model;
import simpleAccount.model.ModelEvent;
import simpleAccount.view.JFrameView;


/**
 * This is the MainView.  This is the initial view that is created
 * when the program is launched.  This allows the user to interact
 * with funds in different currencies, save their changes, or exit.
 * 
 * @author Paul Bryson
 * @since 2015-04-05
 */
public class MainView extends JFrameView {

	private static final long serialVersionUID = 1L;
	private JFrame frame;

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 351, 192);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 11, 311, 20);
		frame.getContentPane().add(comboBox);
		
		JButton btnEditInUsd = new JButton("Edit in USD");
		btnEditInUsd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditInUsd.setBounds(10, 42, 97, 23);
		frame.getContentPane().add(btnEditInUsd);
		
		JButton btnEditInEuros = new JButton("Edit in Euros");
		btnEditInEuros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditInEuros.setBounds(117, 42, 97, 23);
		frame.getContentPane().add(btnEditInEuros);
		
		JButton btnEditInYuan = new JButton("Edit in Yuan");
		btnEditInYuan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditInYuan.setBounds(224, 42, 97, 23);
		frame.getContentPane().add(btnEditInYuan);
		
		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(10, 110, 97, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(117, 110, 97, 23);
		frame.getContentPane().add(btnNewButton_2);
	}

	@Override
	public void modelChanged(ModelEvent event) {}
	
	/**
	 * Sets up the main view.
	 * @param model the AccountModel
	 * @param controller the AccountController
	 */
	public MainView (Model model, Controller controller){
		super(model, controller);
		initialize();
		this.frame.setVisible(true);
	}

}
