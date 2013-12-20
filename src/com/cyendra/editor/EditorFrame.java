package com.cyendra.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.File;

import javax.swing.*;

import com.cyendra.editor.commons.WorkSpace;
import com.cyendra.editor.tree.TreeCreator;

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
	
	//Action对象
	//新建文件的Action对象
	private Action fileNew = new AbstractAction("新建文件", new ImageIcon("images/newFile.gif")) {
		public void actionPerformed(ActionEvent e) {
			//newFile();
		}
	};
	//新建目录的Action对象
	private Action folerNew = new AbstractAction("新建目录", new ImageIcon("images/newFile.gif")) {
		public void actionPerformed(ActionEvent e) {
			//newFolder();
		}
	};
	//新建项目的Action对象
	private Action projectNew = new AbstractAction("新建项目", new ImageIcon("images/newFile.gif")) {
		public void actionPerformed(ActionEvent e) {
			//newProject();
		}
	};
	//打开文件的Action对象
	private Action open = new AbstractAction("打     开", new ImageIcon("images/open.gif")) {
		public void actionPerformed(ActionEvent e) {
			//selectFile();
		}
	};
	//保存文件的Action对象
	private Action save = new AbstractAction("保     存", new ImageIcon("images/save.gif")) {
		public void actionPerformed(ActionEvent e) {
			//saveFile(getCurrentFile());
		}
	};
	//刷新树的Action对象
	private Action refresh = new AbstractAction("刷     新", new ImageIcon("images/refresh.gif")) {
		public void actionPerformed(ActionEvent e) {
			//reloadNode(getSelectNode());
		}
	};
	//运行文件的Action对象
	private Action run = new AbstractAction("运     行", new ImageIcon("images/run.gif")) {
		public void actionPerformed(ActionEvent e) {
			//run();
		}
	};
	//退出的Action对象
	private Action exit = new AbstractAction("退     出") {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);//直接退出
		}
	};
	//复制文本的Action对象
	private Action copy = new AbstractAction("复     制", new ImageIcon("images/copy.gif")) {
		public void actionPerformed(ActionEvent e) {
			//if (getCurrentFile() != null) getCurrentFile().getEditPane().copy();
		}
	};
	//剪切文本的Action对象
	private Action cut = new AbstractAction("剪     切", new ImageIcon("images/cut.gif")) {
		public void actionPerformed(ActionEvent e) {
			//if (getCurrentFile() != null) getCurrentFile().getEditPane().cut();
		}
	};
	//粘贴文本的Action对象
	private Action paste = new AbstractAction("粘     贴", new ImageIcon("images/paste.gif")) {
		public void actionPerformed(ActionEvent e) {
			//if (getCurrentFile() != null) getCurrentFile().getEditPane().paste();
		}
	};
	
	//菜单栏
	private JMenuBar menuBar;
	private JMenu editMenu;
	private JMenu fileMenu;
	private JMenu projMenu;
	
	//工具栏
	private JToolBar toolBar;
	
	//工作空间
	private WorkSpace workSpace;
	
	//建树及节点器
	private TreeCreator treeCreator;
	
	public EditorFrame(TreeCreator treeCreator) {
		super();
		this.setTitle("IDE");
	}
	
	/**
	 * 初始化窗体
	 * */
	public void initFrame(WorkSpace space) {
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
		
		//菜单与工具栏
		createMenuBar();
		createToolBar();
		
		//创建树
		tree = treeCreator.createTree(this);
		
		//添加监听器
		addListeners();
		
		this.setSize(this.WIDTH, this.HEIGHT);
		this.setLocation(200, 75);
	}
	
	//创建工具栏
	private void createToolBar(){
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setMargin(new Insets(0,10,5,5));
		this.add(toolBar,BorderLayout.NORTH);
	}
	
	//添加监听器
	private void addListeners(){
		//文件菜单
		fileMenu.add(fileNew).setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_MASK));
		fileMenu.add(folerNew).setAccelerator(KeyStroke.getKeyStroke('F', InputEvent.CTRL_MASK));
		fileMenu.add(projectNew).setAccelerator(KeyStroke.getKeyStroke('P', InputEvent.CTRL_MASK));
		fileMenu.add(open).setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_MASK));
		fileMenu.add(save).setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_MASK));
		fileMenu.add(exit);
		
		//编辑菜单
		editMenu.add(copy).setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_MASK));
		editMenu.add(cut).setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_MASK));
		editMenu.add(paste).setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_MASK));
		
		//项目菜单
		projMenu.add(refresh).setAccelerator(KeyStroke.getKeyStroke("F5"));
		projMenu.add(run).setAccelerator(KeyStroke.getKeyStroke('R', InputEvent.CTRL_MASK));
		
		//为工具条添加各个操作
		toolBar.add(fileNew).setToolTipText("新建文件");
		toolBar.add(open).setToolTipText("打开");
		toolBar.add(save).setToolTipText("保存");
		toolBar.addSeparator();

		toolBar.add(copy).setToolTipText("复制");
		toolBar.add(cut).setToolTipText("剪切");
		toolBar.add(paste).setToolTipText("粘贴");
		toolBar.addSeparator();
		
		toolBar.add(refresh).setToolTipText("刷新");
		toolBar.add(run).setToolTipText("运行");
		
	}
	
	//创建菜单栏
	private void createMenuBar() {
		menuBar = new JMenuBar();
		fileMenu = new JMenu("文件");
		editMenu = new JMenu("编辑");
		projMenu = new JMenu("项目");
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(projMenu);
		this.setJMenuBar(menuBar);
	}

	public WorkSpace getWorkSpace() {
		return workSpace;
	}
	
}
