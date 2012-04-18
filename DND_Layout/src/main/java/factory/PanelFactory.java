/**
 * 
 */
package factory;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * @author GAL version 17.04.2012
 */
public class PanelFactory {
	public static JPanel getPanel(int size) {
		JPanel newPanel = new JPanel();
		newPanel.setBackground(Color.blue);
		newPanel.setBorder(BorderFactory.createLineBorder(Color.red, 2));
                Random r=new Random();
		newPanel.setPreferredSize(new Dimension(r.nextInt(50)+10, r.nextInt(50)+10));
		return newPanel;
	}
}
