/**
 * 
 */
package gui;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

/**
 * @author GAL version 17.04.2012
 */
public class SwingView {

	private static final int CONTROL_COUNT = 10;
	private static final int CONTROL_SIZE = 40;
	private JFrame frame;
	private JPanel pnlTo;
	private JPanel pnlFrom;
	private JPanel pnlMain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingView window = new SwingView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SwingView() {
		initialize();
		generateControls();
	}

	/**
	 * 
	 */
	private void generateControls() {
		Random r = new Random();
		for (int i = 0; i < CONTROL_COUNT; i++) {

			JPanel jPanel = new JPanel();
			jPanel.setBackground(Color.black);
			jPanel.setBorder(BorderFactory.createLineBorder(Color.red, 2));
			int size = r.nextInt(CONTROL_SIZE) + CONTROL_SIZE;
			jPanel.setBounds(r.nextInt(pnlFrom.getWidth() - size),
					r.nextInt(pnlFrom.getHeight() - size), size, size);
			jPanel.addMouseMotionListener(new PnlMouseListener());
			jPanel.addMouseListener(new PnlMouseListener());
			pnlTo.add(jPanel);

		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 630, 659);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		pnlFrom = new JPanel();
		pnlFrom.setBounds(0, 0, 614, 310);
		pnlFrom.setBackground(UIManager
				.getColor("EditorPane.selectionBackground"));
		frame.getContentPane().add(pnlFrom);
		pnlFrom.setLayout(null);

		pnlTo = new JPanel();
		pnlTo.setBounds(0, 310, 614, 310);
		pnlTo.setBackground(Color.YELLOW);
		frame.getContentPane().add(pnlTo);
		pnlTo.setLayout(null);

		pnlMain = new JPanel();
		pnlMain.setBounds(0, 0, 614, 620);
		frame.getContentPane().add(pnlMain);
		pnlMain.setLayout(null);
	}

	class PnlMouseListener implements MouseMotionListener, MouseListener {
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseAdapter#mouseDragged(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseDragged(MouseEvent e) {
			System.out.println("DREG");
			Component c = (Component) e.getSource();
			pnlFrom.remove(c);
			pnlMain.add(c);
			int x = e.getX() + c.getLocation().x;
			int y = e.getY() + c.getLocation().y;
			c.setLocation(x, y);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
			Component c = (Component) e.getSource();
			if (c.getBounds().intersects(pnlTo.getBounds())) {
//				pnlTo.add(c);
//				pnlFrom.add(c);
				System.out.println("FUCK");
//				pnlMain.remove(c);
			}
			pnlTo.add(c);
//			c.setBounds(34, 43, 43, 43);
			pnlFrom.add(c);
			c.getParent().repaint();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent
		 * )
		 */
		@Override
		public void mouseMoved(MouseEvent e) {
			// System.out.println("ЬЩМУ " + e.getSource());

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("CKU");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseEntered(MouseEvent e) {
			System.out.println("ENTER");

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}
}