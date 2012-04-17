/**
 * 
 */
package layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

/**
 * @author GAL version 17.04.2012
 */
public class MyLayout implements LayoutManager {
	private int vgap;
	private int preferredWidth = 0, preferredHeight = 0;
	private boolean sizeUnknown = true;

	public MyLayout() {
	}

	/* Required by LayoutManager. */
	public void addLayoutComponent(String name, Component comp) {
	}

	/* Required by LayoutManager. */
	public void removeLayoutComponent(Component comp) {
	}

	private void setSizes(Container parent) {
		int nComps = parent.getComponentCount();

		Dimension d = null;

		// Reset preferred/minimum width and height.
		int lastCollHeight = 0;
		preferredWidth = 0;
		preferredHeight = 0;


		for (int i = 0; i < nComps; i++) {
			Component c = parent.getComponent(i);
			if (!c.isVisible()) {
				continue;
			}

			d = c.getPreferredSize();
			if (lastCollHeight < parent.getHeight()) {
				lastCollHeight += d.height;
			} else {
				preferredHeight = lastCollHeight;
				preferredWidth += d.width;
				lastCollHeight = 0;
			}

		}
		if (preferredHeight < lastCollHeight) {
			preferredHeight = lastCollHeight;
		}
	}

	/* Required by LayoutManager. */
	public Dimension preferredLayoutSize(Container parent) {
		System.out.println("FDF1111");
		Dimension dim = new Dimension(0, 0);

		setSizes(parent);

		// Always add the container's insets!
		Insets insets = parent.getInsets();
		dim.width = preferredWidth + insets.left + insets.right;
		dim.height = preferredHeight + insets.top + insets.bottom;

		sizeUnknown = false;

		return dim;
	}

	public void layoutContainer(Container parent) {

		if (sizeUnknown) {
			setSizes(parent);
		}
		int lastY = 0;
		int lastX = 0;
		int nComps = parent.getComponentCount();

		for (int i = 0; i < nComps; i++) {
			Component c = parent.getComponent(i);
			if (!c.isVisible()) {
				continue;
			}
			Dimension d = c.getPreferredSize();
			if (parent.getHeight() < lastY + d.height) {
				System.out.println("FUCK");
				lastY = 0;
				lastX += d.width;

			}
			c.setBounds(lastX, lastY, d.width, d.height);

			lastY += d.height;
//			System.out.println(lastY);
		}
		System.out.print(preferredLayoutSize(parent).height + " : ");
		System.out.println(preferredLayoutSize(parent).width);
		System.out.print(parent.getHeight() + " : ");
		System.out.println(parent.getWidth());
		System.out.println();
	}

	public String toString() {
		String str = "";
		return getClass().getName() + "[vgap=" + vgap + str + "]";
	}

	/* (non-Javadoc)
	 * @see java.awt.LayoutManager#minimumLayoutSize(java.awt.Container)
	 */
	@Override
	public Dimension minimumLayoutSize(Container parent) {
		System.out.println("FDF");
		return preferredLayoutSize(parent);
	}
}
