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
 * EditorFrame IDE����
 * @author cyendra
 * @version 0.1
 * */
public class EditorFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	//��Ļ��С
	private final int WIDTH = 800;
	private final int HEIGHT = 600;
	
	//���༭������Ϣ��ʾ��
	private JTabbedPane tabPane;
	private Box box;
	private JDesktopPane desk;
	private JSplitPane editorSplitPane;
	private JScrollPane infoPane;
	private JTextArea infoArea;
	
	//������Ŀ��ʾ��
	private JScrollPane treePane;
	private JSplitPane mainSplitPane;
	private JTree tree;
	
	//Action����
	//�½��ļ���Action����
	private Action fileNew = new AbstractAction("�½��ļ�", new ImageIcon("images/newFile.gif")) {
		public void actionPerformed(ActionEvent e) {
			//newFile();
		}
	};
	//�½�Ŀ¼��Action����
	private Action folerNew = new AbstractAction("�½�Ŀ¼", new ImageIcon("images/newFile.gif")) {
		public void actionPerformed(ActionEvent e) {
			//newFolder();
		}
	};
	//�½���Ŀ��Action����
	private Action projectNew = new AbstractAction("�½���Ŀ", new ImageIcon("images/newFile.gif")) {
		public void actionPerformed(ActionEvent e) {
			//newProject();
		}
	};
	//���ļ���Action����
	private Action open = new AbstractAction("��     ��", new ImageIcon("images/open.gif")) {
		public void actionPerformed(ActionEvent e) {
			//selectFile();
		}
	};
	//�����ļ���Action����
	private Action save = new AbstractAction("��     ��", new ImageIcon("images/save.gif")) {
		public void actionPerformed(ActionEvent e) {
			//saveFile(getCurrentFile());
		}
	};
	//ˢ������Action����
	private Action refresh = new AbstractAction("ˢ     ��", new ImageIcon("images/refresh.gif")) {
		public void actionPerformed(ActionEvent e) {
			//reloadNode(getSelectNode());
		}
	};
	//�����ļ���Action����
	private Action run = new AbstractAction("��     ��", new ImageIcon("images/run.gif")) {
		public void actionPerformed(ActionEvent e) {
			//run();
		}
	};
	//�˳���Action����
	private Action exit = new AbstractAction("��     ��") {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);//ֱ���˳�
		}
	};
	//�����ı���Action����
	private Action copy = new AbstractAction("��     ��", new ImageIcon("images/copy.gif")) {
		public void actionPerformed(ActionEvent e) {
			//if (getCurrentFile() != null) getCurrentFile().getEditPane().copy();
		}
	};
	//�����ı���Action����
	private Action cut = new AbstractAction("��     ��", new ImageIcon("images/cut.gif")) {
		public void actionPerformed(ActionEvent e) {
			//if (getCurrentFile() != null) getCurrentFile().getEditPane().cut();
		}
	};
	//ճ���ı���Action����
	private Action paste = new AbstractAction("ճ     ��", new ImageIcon("images/paste.gif")) {
		public void actionPerformed(ActionEvent e) {
			//if (getCurrentFile() != null) getCurrentFile().getEditPane().paste();
		}
	};
	
	//�˵���
	private JMenuBar menuBar;
	private JMenu editMenu;
	private JMenu fileMenu;
	private JMenu projMenu;
	
	//������
	private JToolBar toolBar;
	
	//�����ռ�
	private WorkSpace workSpace;
	
	//�������ڵ���
	private TreeCreator treeCreator;
	
	public EditorFrame(TreeCreator treeCreator) {
		super();
		this.setTitle("IDE");
	}
	
	/**
	 * ��ʼ������
	 * */
	public void initFrame(WorkSpace space) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//���༭������Ϣ��ʾ��
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
		
		//������Ŀ��ʾ��
		tree = new JTree();
		treePane = new JScrollPane(tree);
		mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,treePane,editorSplitPane);
		mainSplitPane.setDividerLocation(200);
		mainSplitPane.setDividerSize(3);
		this.add(mainSplitPane);
		
		//�˵��빤����
		createMenuBar();
		createToolBar();
		
		//������
		tree = treeCreator.createTree(this);
		
		//��Ӽ�����
		addListeners();
		
		this.setSize(this.WIDTH, this.HEIGHT);
		this.setLocation(200, 75);
	}
	
	//����������
	private void createToolBar(){
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setMargin(new Insets(0,10,5,5));
		this.add(toolBar,BorderLayout.NORTH);
	}
	
	//��Ӽ�����
	private void addListeners(){
		//�ļ��˵�
		fileMenu.add(fileNew).setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_MASK));
		fileMenu.add(folerNew).setAccelerator(KeyStroke.getKeyStroke('F', InputEvent.CTRL_MASK));
		fileMenu.add(projectNew).setAccelerator(KeyStroke.getKeyStroke('P', InputEvent.CTRL_MASK));
		fileMenu.add(open).setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_MASK));
		fileMenu.add(save).setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_MASK));
		fileMenu.add(exit);
		
		//�༭�˵�
		editMenu.add(copy).setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_MASK));
		editMenu.add(cut).setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_MASK));
		editMenu.add(paste).setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_MASK));
		
		//��Ŀ�˵�
		projMenu.add(refresh).setAccelerator(KeyStroke.getKeyStroke("F5"));
		projMenu.add(run).setAccelerator(KeyStroke.getKeyStroke('R', InputEvent.CTRL_MASK));
		
		//Ϊ��������Ӹ�������
		toolBar.add(fileNew).setToolTipText("�½��ļ�");
		toolBar.add(open).setToolTipText("��");
		toolBar.add(save).setToolTipText("����");
		toolBar.addSeparator();

		toolBar.add(copy).setToolTipText("����");
		toolBar.add(cut).setToolTipText("����");
		toolBar.add(paste).setToolTipText("ճ��");
		toolBar.addSeparator();
		
		toolBar.add(refresh).setToolTipText("ˢ��");
		toolBar.add(run).setToolTipText("����");
		
	}
	
	//�����˵���
	private void createMenuBar() {
		menuBar = new JMenuBar();
		fileMenu = new JMenu("�ļ�");
		editMenu = new JMenu("�༭");
		projMenu = new JMenu("��Ŀ");
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(projMenu);
		this.setJMenuBar(menuBar);
	}

	public WorkSpace getWorkSpace() {
		return workSpace;
	}
	
}
