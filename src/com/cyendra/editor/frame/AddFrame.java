package com.cyendra.editor.frame;

import javax.swing.*;

public class AddFrame extends JFrame {
	private JPanel mainPanel;
	private JPanel namePanel;
	private JLabel nameLabel;
	private JTextField nameText;
	private JPanel buttonPanel;
	private JButton confirmButton;
	private JButton cancelButton;
	
	public AddFrame() {
		mainPanel = new JPanel();
		namePanel = new JPanel();
		nameLabel = new JLabel("文件名称：");
		nameText = new JTextField("",20);
		buttonPanel = new JPanel();
		confirmButton = new JButton("确定");
		cancelButton = new JButton("取消");
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setLocation(200,200);
		this.setResizable(false);
		
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
		namePanel.add(nameLabel);
		namePanel.add(nameText);
		
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		confirmButton.setEnabled(false);
		buttonPanel.add(confirmButton);
		buttonPanel.add(new JLabel("     "));
		buttonPanel.add(cancelButton);
		
		mainPanel.add(namePanel);
		mainPanel.add(buttonPanel);
		
		this.add(mainPanel);
		pack();
	} 
	
}
