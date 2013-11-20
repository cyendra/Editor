package com.cyendra.editor;

import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTree;

/**
 * EditorFrame IDE窗体
 * @author cyendra
 * @version 0.1
 * */
public class EditorFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	//屏幕大小
	private final int WIDTH = 800;
	private final int HEIGHT = 600;
	
	//主编辑区和信息显示区
	private JTabbedPane tabPane;
	private Box box;
	private JDesktopPane desk;
	private JSplitPane editorSplitPane;
	private JScrollPane infoPane;
	private JTextArea infoArea;
	
	//建立项目显示区
	private JScrollPane treePane;
	private JSplitPane mainSplitPane;
	private JTree tree;
	
	//菜单与工具栏
	private JMenuBar menuBar;
	private JMenu editMenu;
	private JMenu fileMenu;
	
	public EditorFrame() {
		super();
		this.setTitle("IDE");
		initFrame();
		this.setSize(this.WIDTH, this.HEIGHT);
		this.setLocation(200, 75);
	}
	
	/**
	 * 初始化窗体
	 * */
	public void initFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//主编辑区和信息显示区
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
		editorSplitPane.setDividerLocation(400);
		this.add(editorSplitPane);
		
		//建立项目显示区
		tree = new JTree();
		treePane = new JScrollPane(tree);
		mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,treePane,editorSplitPane);
		mainSplitPane.setDividerLocation(200);
		mainSplitPane.setDividerSize(3);
		this.add(mainSplitPane);
		
	}
	
}
