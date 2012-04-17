/**
 * 
 */
package factory;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * @author GAL version 17.04.2012
 */
public class PanelFactory {
	public static JPanel getPanel(int size) {
		JPanel newPanel = new JPanel();
		newPanel.setBackground(Color.blue);
		newPanel.setBorder(BorderFactory.createLineBorder(Color.white));
		newPanel.setPreferredSize(new Dimension(size, size));
		return newPanel;
	}
}
