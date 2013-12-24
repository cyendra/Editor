package com.cyendra.editor.frame;

import javax.swing.*;

import com.cyendra.editor.commons.AddInfo;

public class AddFrame extends JFrame {
	private JPanel mainPanel;
	private JPanel namePanel;
	private JLabel nameLabel;
	private JTextField nameText;
	private JPanel buttonPanel;
	private JButton confirmButton;
	private JButton cancelButton;
	
	public AddFrame(final AddInfo info) {
		mainPanel = new JPanel();
		namePanel = new JPanel();
		nameLabel = new JLabel(info.getInfo());
		nameText = new JTextField("",20);
		buttonPanel = new JPanel();
		confirmButton = new JButton("确定");
		cancelButton = new JButton("取消");
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		/*
		addWindowListener(new WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				cancel(info);
			}
		});
		*/
		this.setLocation(200,200);
		this.setResizable(false);
		
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
		namePanel.add(nameLabel);
		namePanel.add(nameText);
		/*
		nameText.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				//判断项目路径与名称是否有值, 如果两个text field都有值, 则确定按钮可用
				if (nameText.getText().equals("")) {
					confirmButton.setEnabled(false);
				} else {
					confirmButton.setEnabled(true);
				}
			}
		});
		*/
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		confirmButton.setEnabled(false);
		buttonPanel.add(confirmButton);
		buttonPanel.add(new JLabel("     "));
		buttonPanel.add(cancelButton);
		/*
		//为取消按钮添加监听器
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cancel(info);
			}
		});
		//为确定按钮添加监听器
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//如果输入框没有值，返回
				if (nameText.getText() == "") return;
				handerConfirm(info);
			}
		});
		*/
		mainPanel.add(namePanel);
		mainPanel.add(buttonPanel);
		
		this.add(mainPanel);
		pack();
	} 
	//处理确定按钮的点击
	private void handerConfirm(AddInfo info) {
		//获取得用户输入
		String data = nameText.getText();
		//新增后会涉及的一些与业务相关的操作留给Handler类处理
		info.getHandler().afterAdd(info.getEditorFrame(), this, data);
	}
	
	private void cancel(AddInfo info) {
		//设置EditorFrame可用
		info.getEditorFrame().setEnabled(true);
		//让本窗口不可见
		setVisible(false);
	}
	
	
}
