package com.cyendra.editor;

import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class EditorFrame extends JFrame {
	
	private JTabbedPane tabPane;
	private Box box;
	private JDesktopPane desk;
	private JSplitPane editorSplitPane;
	private JScrollPane infoPane;
	private JTextArea infoArea;
	
	public EditorFrame() {
		super();
		this.setTitle("IDE");
		initFrame();
		this.pack();
	}
	public void initFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tabPane = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
		desk = new JDesktopPane();
		desk.setBackground(Color.gray);
		box = new Box(BoxLayout.Y_AXIS);
		box.add(tabPane);
		box.add(desk);
		infoArea = new JTextArea("",5,50);
		infoPane = new JScrollPane(infoArea);
		infoArea.setEditable(false);
		editorSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,box,infoPane);
		editorSplitPane.setDividerSize(3);
		editorSplitPane.setDividerLocation(500);
		this.add(editorSplitPane);
		this.pack();
	}
	
}
