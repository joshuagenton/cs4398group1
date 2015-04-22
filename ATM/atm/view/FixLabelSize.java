package atm.view;

import java.awt.Font;

/**
 * The FixLabelSize adjusts the sizing of the labels.
 * 
 * @author Chris Wells
 * @since 2015-04-21
 */

import javax.swing.JLabel;

public class FixLabelSize {
	
	/**
	 * Constructor. 
	 * 
	 * @param label the label to be adjusted
	 * @return the label
	 */
	public JLabel fix(JLabel label){
		Font labelFont = label.getFont();
		String labelText = label.getText();

		int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
		int componentWidth = label.getWidth();

		// Find out how much the font can grow in width.
		double widthRatio = (double)componentWidth / (double)stringWidth;

		int newFontSize = (int)(labelFont.getSize() * widthRatio);
		int componentHeight = label.getHeight();

		// Pick a new font size so it will not be larger than the height of label.
		int fontSizeToUse = Math.min(newFontSize, componentHeight);

		// Set the label's font size to the newly determined size.
		label.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		return label;
		
	}
}
