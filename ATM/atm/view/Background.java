package atm.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Background extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image img = new ImageIcon(this.getClass().getResource("/atm/view/ATM.jpg")).getImage();
    @Override
	public void paintComponent(Graphics g)
    {

        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }
}
