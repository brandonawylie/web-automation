package amerishore.automation.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomePanel extends JPanel{

	public HomePanel(){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JButton button = new JButton("Sandbox");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame f = (JFrame)SwingUtilities.getAncestorOfClass(Main.class, HomePanel.this);
				f.setContentPane(new TestingInstance());
			}
		});
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(25, 25, 25, 25);
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		add(button, c);

		c = new GridBagConstraints();
		button = new JButton("New Project");
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(25,25,25,25);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		add(button, c);

		c = new GridBagConstraints();
		button = new JButton("Open Project");
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(25,25,25,25);
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		add(button, c);

	}

}
