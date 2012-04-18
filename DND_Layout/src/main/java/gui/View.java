/**
 * 
 */
package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceAdapter;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import factory.LayoutFactory;
import factory.PanelFactory;
import java.awt.Color;

/**
 * @author GAL version 17.04.2012
 */
public class View {

	private JFrame frame;
	private JPanel pnlDestination;

	private static final int CONTROL_COUNT = 20;
	private static final int CONTROL_SIZE = 20;
	private static Dimension phase = null;
	private static JPanel draggablePanel = null;
	private JPanel pnlSource;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
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
	public View() {
		initialize();
		for (int i = 0; i < CONTROL_COUNT; i++) {
			pnlSource.add(pnlSource.add(generateDND_Panel()));
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 738, 452);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel pnlButtons = new JPanel();

		JButton btnFlowLayout = new JButton("Flow layout");
		pnlButtons.add(btnFlowLayout);

		JButton btnBoxLayout = new JButton("Box layout");
		pnlButtons.add(btnBoxLayout);

		JButton btnGridLayout = new JButton("Grid layout");
		pnlButtons.add(btnGridLayout);

		JButton btnMyLayout = new JButton("My layout");
		pnlButtons.add(btnMyLayout);

		pnlDestination = new JPanel();
		pnlDestination.setBackground(Color.PINK);

		final JPanel pnlControls = new JPanel();

		pnlSource = new JPanel();
		pnlSource.setBackground(Color.ORANGE);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(10)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												pnlDestination,
																												GroupLayout.DEFAULT_SIZE,
																												351,
																												Short.MAX_VALUE)
																										.addPreferredGap(
																												ComponentPlacement.UNRELATED)
																										.addComponent(
																												pnlSource,
																												GroupLayout.DEFAULT_SIZE,
																												341,
																												Short.MAX_VALUE))
																						.addComponent(
																								pnlButtons,
																								GroupLayout.DEFAULT_SIZE,
																								702, Short.MAX_VALUE)))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(pnlControls,
																				GroupLayout.DEFAULT_SIZE, 702,
																				Short.MAX_VALUE))).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addComponent(pnlButtons, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnlControls, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addComponent(pnlDestination, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
												302, Short.MAX_VALUE)
										.addComponent(pnlSource, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
						.addGap(34)));

		pnlDestination.setTransferHandler(new PanelTransferHandler());
		pnlSource.setTransferHandler(new PanelTransferHandler());

		JButton btnMyVertLayout = new JButton("My Vertical layout");
		pnlButtons.add(btnMyVertLayout);

		JButton btnAddButton = new JButton("Add panel");
		pnlControls.add(btnAddButton);

		JButton btnDelButton = new JButton("Del panel");
		pnlControls.add(btnDelButton);

		JButton btnAbsLayout = new JButton("Set manual layout");
		pnlControls.add(btnAbsLayout);

		pnlDestination.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		frame.getContentPane().setLayout(groupLayout);

		btnAbsLayout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pnlDestination.setLayout(LayoutFactory.getLayout(LayoutFactory.GRID_BAG_LAYOUT, pnlDestination));
				pnlDestination.validate();
			}
		});

		btnFlowLayout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pnlDestination.setLayout(LayoutFactory.getLayout(LayoutFactory.FLOW_LAYOUT, pnlDestination));
				pnlDestination.validate();
			}
		});

		btnBoxLayout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDestination.setLayout(LayoutFactory.getLayout(LayoutFactory.BOX_LAYOUT, pnlDestination));
				pnlDestination.validate();
			}
		});

		btnGridLayout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDestination.setLayout(LayoutFactory.getLayout(LayoutFactory.GRID_LAYOUT, pnlDestination));
				pnlDestination.validate();
			}
		});

		btnMyLayout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDestination.setLayout(LayoutFactory.getLayout(LayoutFactory.MY_LAYOUT, pnlDestination));
				pnlDestination.validate();
			}
		});

		btnMyVertLayout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDestination.setLayout(LayoutFactory.getLayout(LayoutFactory.MY_VERTICAL_LAYOUT, pnlDestination));
				pnlDestination.validate();
			}
		});

		btnDelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pnlDestination.getComponentCount() > 0) {
					pnlDestination.remove(0);
				}
				pnlDestination.validate();
			}
		});

		btnAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlSource.add(pnlSource.add(generateDND_Panel()));
				pnlDestination.validate();
			}
		});
	}

	class MyTransferHandler extends TransferHandler {

		public int getSourceActions(JComponent c) {
			return TransferHandler.MOVE;
		}

		protected Transferable createTransferable(JComponent c) {
			return new StringSelection("FUCK");
		}
	}

	class MyMouseHandler extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				System.err.println("In press handler");
				draggablePanel = (JPanel) e.getSource();

				// Для корректной вставки панели позднее
				Point los = draggablePanel.getLocationOnScreen();
				phase = new Dimension(e.getLocationOnScreen().x - los.x, e.getLocationOnScreen().y - los.y);

				JComponent c = (JComponent) e.getSource();
				TransferHandler handler = c.getTransferHandler();
				handler.exportAsDrag(c, e, TransferHandler.MOVE);
			}
		}
	}

	class PanelTransferHandler extends TransferHandler {
		public int getSourceActions(JComponent c) {
			return TransferHandler.NONE;
		}

		public boolean canImport(TransferSupport support) {
			try {
				return support.getTransferable().getTransferData(DataFlavor.stringFlavor) != null;
			} catch (Throwable e) {
				return false;
			}
		}

		@Override
		public boolean importData(TransferHandler.TransferSupport info) {
			if (info.isDrop()) {
				try {
					Point dropPoint = info.getDropLocation().getDropPoint();
					draggablePanel.setLocation(dropPoint.x - phase.width, dropPoint.y - phase.height);
					// draggablePanel.revalidate();

					Container destination = (Container) info.getComponent();
					Container source = draggablePanel.getParent();
					if (!source.equals(destination)) {
						source.remove(draggablePanel);
						destination.add(draggablePanel);

						frame.getContentPane().validate();
						source.repaint();
						destination.repaint();
					}

					return true;
				} catch (Throwable e) {
					return false;
				}
			} else {
				return false;
			}
		}
	}

	private JPanel generateDND_Panel() {
		JPanel jPanel = PanelFactory.getPanel(CONTROL_SIZE);
		jPanel.setTransferHandler(new MyTransferHandler());
		jPanel.addMouseListener(new MyMouseHandler());
		return jPanel;
	}
}
