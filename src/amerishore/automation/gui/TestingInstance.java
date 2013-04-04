package amerishore.automation.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTree;
import javax.swing.JScrollBar;

public class TestingInstance extends JPanel {

	/**
	 * Create the panel.
	 */
	public TestingInstance() {
		String[] data = {"one", "two", "three"};
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[]{58, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

				JPanel panel = new JPanel();
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.insets = new Insets(0, 0, 5, 0);
				gbc_panel.weighty = 1.0;
				gbc_panel.weightx = 1.0;
				gbc_panel.anchor = GridBagConstraints.NORTH;
				gbc_panel.fill = GridBagConstraints.BOTH;
				gbc_panel.gridx = 0;
				gbc_panel.gridy = 0;
				add(panel, gbc_panel);
				GridBagLayout gbl_panel = new GridBagLayout();
				panel.setLayout(gbl_panel);
						JList list = new JList(data);
						GridBagConstraints gbc_list = new GridBagConstraints();
						gbc_list.fill = GridBagConstraints.BOTH;
						gbc_list.weighty = 1.0;
						gbc_list.weightx = 1.0;
						gbc_list.insets = new Insets(0, 0, 5, 5);
						gbc_list.gridx = 0;
						gbc_list.gridy = 0;
						panel.add(list, gbc_list);

										JPanel panel_2 = new JPanel();
										GridBagConstraints gbc_panel_2 = new GridBagConstraints();
										gbc_panel_2.insets = new Insets(0, 0, 5, 5);
										gbc_panel_2.fill = GridBagConstraints.BOTH;
										gbc_panel_2.gridx = 0;
										gbc_panel_2.gridy = 1;
										panel.add(panel_2, gbc_panel_2);
										panel_2.setLayout(new BorderLayout(0, 0));

										JButton btnTestcases = new JButton("+ TestCases");
										panel_2.add(btnTestcases, BorderLayout.NORTH);

										JButton btnNewButton = new JButton("+SetupCases");
										panel_2.add(btnNewButton, BorderLayout.SOUTH);
										btnNewButton.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
											}
										});

										JPanel panel_1 = new JPanel();
										GridBagConstraints gbc_panel_1 = new GridBagConstraints();
										gbc_panel_1.insets = new Insets(30, 20, 30, 20);
										gbc_panel_1.anchor = GridBagConstraints.SOUTH;
										gbc_panel_1.weighty = 1.0;
										gbc_panel_1.weightx = 1.0;
										gbc_panel_1.fill = GridBagConstraints.BOTH;
										gbc_panel_1.gridx = 0;
										gbc_panel_1.gridy = 1;
										add(panel_1, gbc_panel_1);
										panel_1.setLayout(new BorderLayout(0, 0));

										JTree tree = new JTree();
										panel_1.add(tree, BorderLayout.CENTER);

										JScrollPane scrollPane = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
										panel_1.add(scrollPane, BorderLayout.CENTER);

	}

}
