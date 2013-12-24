package com.cyendra.editor.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cyendra.editor.EditorFrame;
import com.cyendra.editor.commons.WorkSpace;

public class WorkspaceFrame extends JFrame {
	
	private JPanel mainPanel;
	private JLabel infoLabel;
	private JPanel chosePanel;
	private JLabel workTextLabel;
	private JTextField pathText;//�����ռ�����ʾ�û�ѡ���ļ�Ŀ¼��JTextField
	private JButton choseButton;
	private JPanel buttonPanel;
	private JButton confirmButton;//�����ռ��е�ȷ����ť
	private JButton cancelButton;
	private SpaceChooser chooser;
	private File folder;//�û�ѡ����ļ�Ŀ¼����
	
	public WorkspaceFrame(EditorFrame frame){
		mainPanel = new JPanel();
		infoLabel = new JLabel("��ѡ�����ռ�");
		chosePanel = new JPanel();
		workTextLabel = new JLabel("�����ռ�: ");
		pathText = new JTextField("", 40);
		choseButton = new JButton("ѡ��");
		buttonPanel = new JPanel();
		confirmButton = new JButton("ȷ��");
		cancelButton = new JButton("ȡ��");
		chooser = new SpaceChooser(this);
		
		//������Panel�Ĳ���
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(infoLabel);
		//����ѡ�����Ĳ���
		chosePanel.setLayout(new BoxLayout(chosePanel, BoxLayout.X_AXIS));
		choseButton.addActionListener(new ChooseButtonListener(chooser));
		pathText.setEditable(false);
		chosePanel.add(workTextLabel);
		chosePanel.add(pathText);
		chosePanel.add(choseButton);
		mainPanel.add(chosePanel);

		confirmButton.setEnabled(false);
		//Ϊȷ����ť���ȷ�����¼�, ������һ��WorkSpace����
		confirmButton.addActionListener(new ConfirmButtonListener(this, frame));
		buttonPanel.add(confirmButton);
		buttonPanel.add(new JLabel("    "));
		buttonPanel.add(cancelButton);
		//Ϊȡ����ť����˳��¼�
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mainPanel.add(buttonPanel);
		add(mainPanel);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 200);
		setResizable(false);
	
	}
	
	public File getFolder() {
		return folder;
	}

	public void setFolder(File folder) {
		this.folder = folder;
	}
	
	public JTextField getPathText() {
		return pathText;
	}

	public JButton getConfirmButton() {
		return confirmButton;
	}
}

class ChooseButtonListener implements ActionListener{
	private JFileChooser chooser;
	public ChooseButtonListener(JFileChooser chooser){
		this.chooser = chooser;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.showOpenDialog(null);
	}
} 

class SpaceChooser extends JFileChooser{
	private WorkspaceFrame spaceFrame;
	public SpaceChooser(WorkspaceFrame spaceFrame){
		super("/");
		this.spaceFrame = spaceFrame;
	}
	public void approveSelection(){
		File folder = getSelectedFile();
		spaceFrame.setFolder(folder);
		spaceFrame.getPathText().setText(folder.getAbsolutePath());
		spaceFrame.getConfirmButton().setEnabled(true);
		super.approveSelection();
	}
}

class ConfirmButtonListener implements ActionListener {
	private WorkspaceFrame spaceFrame;
	private EditorFrame editorFrame;
	public ConfirmButtonListener(WorkspaceFrame spaceFrame, EditorFrame editorFrame) {
		this.spaceFrame = spaceFrame;
		this.editorFrame = editorFrame;
	}
	public void actionPerformed(ActionEvent arg0) {
		//System.out.println(spaceFrame.toString());
		//System.out.println(editorFrame.toString());
		editorFrame.initFrame(new WorkSpace(spaceFrame.getFolder(), editorFrame));
		editorFrame.setVisible(true);
		spaceFrame.setVisible(false);
	}
}
