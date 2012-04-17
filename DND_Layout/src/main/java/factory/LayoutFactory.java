/**
 * 
 */
package factory;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;

import layout.MyLayout;

/**
 * @author GAL version 17.04.2012
 */
public class LayoutFactory {

	public static final int GRID_BAG_LAYOUT = 1;
	public static final int FLOW_LAYOUT = 2;
	public static final int GRID_LAYOUT = 3;
	public static final int BOX_LAYOUT = 4;
	public static final int MY_LAYOUT = 5;

	private LayoutFactory() {
	};

	public static LayoutManager getLayout(int layout, Container parent) {
		switch (layout) {
		case GRID_BAG_LAYOUT:
			return new GridBagLayout();
		case FLOW_LAYOUT:
			return new FlowLayout();

		case GRID_LAYOUT:
			return new GridLayout();

		case BOX_LAYOUT:
			return new BoxLayout(parent, BoxLayout.X_AXIS);

		case MY_LAYOUT:
			return new MyLayout();

		}
		return null;
	}
}
