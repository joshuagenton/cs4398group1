package atm.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import atm.controller.CardReaderController;
import atm.controller.Controller;
import atm.model.Model;
import atm.model.ModelEvent;

import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.Cursor;

public class TransferView extends JFrameView {

	private static final long serialVersionUID = 1L;

	public final static String LOGOUT = "Logout";
	
	private JPanel topPanel;
	private JPanel textPanel;
	private JPanel buttonPanel;
	
	private JLabel transferCompleteLabel;
	
	private JButton logoutButton;
	
	public CardReaderController card = new CardReaderController(getModel()); 
	
	public TransferView(Model model, Controller controller) {
		super(model, controller);
		setBackground(new Color(255, 255, 255));
		
		this.setBounds(100, 100, 450, 300);
		add(getContent());
		
		
		Toolkit toolkit =  Toolkit.getDefaultToolkit();
		Dimension dim = toolkit.getScreenSize();
		
		int x = (int) ((dim.getWidth() - this.getWidth()) * 0.5f);
	    int y = (int) ((dim.getHeight() - this.getHeight()) * 0.5f);
	    this.setLocation(x, y);
	    
	}

	
	private JPanel getContent() {
		if (topPanel == null) {
			topPanel = new JPanel();
			GridLayout layout = new GridLayout(0, 1);
			topPanel.setLayout(layout);
			//topPanel.setPreferredSize(new Dimension(300, 100));
			GridBagConstraints ps = new GridBagConstraints();
			ps.gridx = 0;
			ps.gridy = 3;
			ps.fill = GridBagConstraints.HORIZONTAL;
			
			GridBagConstraints bs = new GridBagConstraints();
			bs.gridx = 0;
			bs.gridy = 3;
			topPanel.add(getTextFieldPanel(), null);
			topPanel.add(getButtonPanel(), null);
		}
		return topPanel;
	}
	
	private JPanel getButtonPanel()
	{
		if(buttonPanel == null){
			GridBagConstraints wButtonCtr = new GridBagConstraints();
			wButtonCtr.fill = GridBagConstraints.VERTICAL;
			wButtonCtr.gridheight = 2;
			wButtonCtr.gridx = 0;
			wButtonCtr.gridy = 0;
			
			buttonPanel = new JPanel();
			buttonPanel.setBackground(new Color(255, 255, 255));
			GridBagLayout gbl_buttonPanel = new GridBagLayout();
			gbl_buttonPanel.rowHeights = new int[]{0, 72};
			gbl_buttonPanel.columnWidths = new int[]{185};
			gbl_buttonPanel.columnWeights = new double[]{1.0};
			gbl_buttonPanel.rowWeights = new double[]{1.0, 0.0};
			buttonPanel.setLayout(gbl_buttonPanel);
			buttonPanel.add(getLogoutButton(), wButtonCtr);
		}
		
		return buttonPanel;
	}

	private JPanel getTextFieldPanel()
	{
		if(textPanel == null){
			GridBagConstraints bl = new GridBagConstraints();
			bl.gridx = 0;
			bl.gridy = 0;
			
			textPanel = new JPanel();
			textPanel.setBackground(new Color(255, 255, 255));
			GridBagLayout gbl_textPanel = new GridBagLayout();
			gbl_textPanel.rowHeights = new int[]{46};
			gbl_textPanel.rowWeights = new double[]{0.0};
			gbl_textPanel.columnWidths = new int[]{169};
			gbl_textPanel.columnWeights = new double[]{0.0};
			textPanel.setLayout(gbl_textPanel);
			textPanel.add(getTransferCompleteLabel(), bl);		
		}
		return textPanel;
	}
	
	
	
	
	
	
	
	
	private JLabel getTransferCompleteLabel(){
		if(transferCompleteLabel == null){
			transferCompleteLabel = new JLabel();
			transferCompleteLabel.setForeground(new Color(84,120,38));
			transferCompleteLabel.setFont(new Font("Georgia", Font.PLAIN, 48));
			transferCompleteLabel.setHorizontalAlignment(SwingConstants.CENTER);
			transferCompleteLabel.setText("Transfer Complete");
		}
		return transferCompleteLabel;
	}
	
	private JButton getLogoutButton(){
		if(logoutButton == null){
			logoutButton = new JButton(new ImageIcon(TransferView.class.getResource("/atm/view/button.png")));
			logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			logoutButton.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, new Color(0, 0, 0), null));
			logoutButton.setBackground(new Color(255, 255, 255));
			logoutButton.setForeground(new Color(255, 255, 255));
			logoutButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		}
		return logoutButton;
	}

	@Override
	public void modelChanged(ModelEvent me) {
		LogoutView lv = new LogoutView(getModel(),getController());

		add(lv);
		repaint();	
	}	
}
