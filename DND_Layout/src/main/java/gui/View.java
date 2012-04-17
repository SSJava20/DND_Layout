/**
 * 
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.Border;

import factory.LayoutFactory;
import factory.PanelFactory;

/**
 * @author GAL version 17.04.2012
 */
public class View {

	private JFrame frame;
	private JPanel panel;

	private static final int CONTROL_COUNT = 5;
	private static final int CONTROL_SIZE = 20;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {

				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				} else {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				}
			}

		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and
			// feel

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
			panel.add(PanelFactory.getPanel(CONTROL_SIZE));
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 738, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel pnlButtons = new JPanel();

		JButton btnAbsLayout = new JButton("Abs layout");
		pnlButtons.add(btnAbsLayout);

		JButton btnFlowLayout = new JButton("Flow layout");
		pnlButtons.add(btnFlowLayout);

		JButton btnBoxLayout = new JButton("Box layout");
		pnlButtons.add(btnBoxLayout);

		JButton btnGridLayout = new JButton("Grid layout");
		pnlButtons.add(btnGridLayout);

		JButton btnMyLayout = new JButton("My layout");
		pnlButtons.add(btnMyLayout);

		panel = new JPanel();

		final JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
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
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(pnlButtons, Alignment.TRAILING,
																				GroupLayout.DEFAULT_SIZE, 702,
																				Short.MAX_VALUE)
																		.addComponent(panel, Alignment.TRAILING,
																				GroupLayout.DEFAULT_SIZE, 702,
																				Short.MAX_VALUE)))
										.addGroup(
												groupLayout
														.createSequentialGroup()
														.addContainerGap()
														.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 702,
																Short.MAX_VALUE))).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addComponent(pnlButtons, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
						.addGap(1)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)));

		JButton btnAddButton = new JButton("Add button");
		panel_1.add(btnAddButton);

		JButton btnDelButton = new JButton("DelButton");
		panel_1.add(btnDelButton);

		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		frame.getContentPane().setLayout(groupLayout);

		// set handlers
		btnAbsLayout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setLayout(LayoutFactory.getLayout(LayoutFactory.GRID_BAG_LAYOUT, panel));
				panel.validate();
			}
		});

		btnFlowLayout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setLayout(LayoutFactory.getLayout(LayoutFactory.FLOW_LAYOUT, panel));
				panel.validate();
			}
		});

		btnBoxLayout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setLayout(LayoutFactory.getLayout(LayoutFactory.BOX_LAYOUT, panel));
				panel.validate();
			}
		});

		btnGridLayout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setLayout(LayoutFactory.getLayout(LayoutFactory.GRID_LAYOUT, panel));
				panel.validate();
			}
		});

		btnMyLayout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setLayout(LayoutFactory.getLayout(LayoutFactory.MY_LAYOUT, panel));
				panel.validate();
			}
		});

		btnDelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panel.getComponentCount() > 0) {
					panel.remove(0);
				}
				panel.validate();
			}
		});

		btnAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.add(PanelFactory.getPanel(CONTROL_SIZE));
				panel.validate();
			}
		});
	}
}
