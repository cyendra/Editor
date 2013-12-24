package com.cyendra.editor.tree;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import com.cyendra.editor.EditorFrame;
import com.cyendra.editor.commons.WorkSpace;
import com.cyendra.editor.util.ImageUtil;

public class TreeCreatorImpl implements TreeCreator {
	@Override
	public JTree createTree(EditorFrame editorFrame) {
		File spaceFolder = editorFrame.getWorkSpace().getFolder();
		ProjectTreeNode root = new ProjectTreeNode(spaceFolder, true);
		ProjectTreeModel treeModel = new ProjectTreeModel(root);
		JTree tree = new JTree(treeModel);
		
		//��ȡ�����ռ��������е�Ŀ¼��������projectName.project���Ӧ��Ŀ¼����Ҳ������ĿĿ¼
		List<File> projectFolders = getProjectFolders(spaceFolder);
		
		//������ĿĿ¼���ϣ���Ϊ�䴴���ӽڵ�
		for (int i = 0; i < projectFolders.size(); i++) {
			File projectFolder = projectFolders.get(i);//��ȡѭ���е�Ŀ¼
			ProjectTreeNode node = createNode(projectFolder);//����createNode���������е��ӽڵ�
			root.add(node);//����ڵ�����ӽڵ㣨��ĿĿ¼��
		}

		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();//���ƽڵ�ͼƬ
		renderer.setOpenIcon(ImageUtil.getImageIcon(ImageUtil.FOLDER_OPEN));//Ŀ¼��ʱ��ͼƬ
		renderer.setLeafIcon(ImageUtil.getImageIcon(ImageUtil.FILE));//�ڵ�û���ӽڵ��ͼƬ
		renderer.setClosedIcon(ImageUtil.getImageIcon(ImageUtil.FOLDER_CLOSE));//Ŀ¼�ر�ʱ��ͼƬ
		tree.setCellRenderer(renderer);//�������Ĳ���������Ϊ�����renderer
		
		tree.addMouseListener(new ProjectTreeSelectionListener(editorFrame));//Ϊ��Ŀ�����һ����ѡ�������
		
		TreePath path = new TreePath(root);//����������Ŀ���е�·��
		tree.expandPath(path);//����Ĭ��չ�����ڵ�
		tree.setRootVisible(false);//�������ĸ��ڵ�ɼ�
		return tree;
	}

	@Override
	public ProjectTreeNode createNode(File folder) {
		ProjectTreeNode parent = null;//����һ�����ڵ㣬���������������صĽڵ����
		
		// �������foler����һ��Ŀ¼�Ļ�������һ��ProjectTreeNode���󲢷��أ�������������ӵ���ӽڵ�
		// �����һ��Ŀ¼�Ļ����򴴽������parent����������һ��Ŀ¼������ӵ���ӽڵ�
		if (!folder.isDirectory()) return new ProjectTreeNode(folder, false);
		else parent = new ProjectTreeNode(folder, true);
			
		// ���������parent�ڵ�ȥ�������������е�ֱ�ӽڵ�
		List<ProjectTreeNode> nodes = createNodes(parent.getFile());
		
		for (ProjectTreeNode node : nodes) { // ��ȡ��parent���������ֱ���ӽڵ����ȥѭ���ݹ���ñ�����
			parent.add(createNode(node.getFile()));// �ݹ鴴���ӽڵ㣬�������صĽڵ���ӵ�parent��
		}
		return parent;
	}

	private List<ProjectTreeNode> createNodes(File folder) {
		File[] files = folder.listFiles();//��ȡ��Ŀ¼�µ������ļ�
		List<ProjectTreeNode> result = new ArrayList<ProjectTreeNode>();
		for (File file : files) {	
			if (file.isDirectory()) result.add(new ProjectTreeNode(file, true));
			if (!file.isDirectory()) result.add(new ProjectTreeNode(file, false));
		}
		return result;
	}
	
	/**
	 * ��ȡ�����ռ�Ŀ¼�����е���Ŀ����
	 * @return
	 */
	private List<String> getProjectNames(File spaceFolder) {
		List<String> result = new ArrayList<String>();
		for (File file : spaceFolder.listFiles()) {
			if (file.isDirectory()){
				for (File proj : file.listFiles()){
					if (proj.getName().endsWith(".project")) {//ȡ��.project��β���ļ�
						System.out.println(proj.getName());
						result.add(proj.getName().substring(0, proj.getName().indexOf(".project")));
						break;
					}
				}
			}

		}
		return result;
	}
	

	/**
	 * ��ȡ�����ռ�Ŀ¼�����е���ĿĿ¼
	 */
	private List<File> getProjectFolders(File spaceFolder) {
		List<String> projectNames = getProjectNames(spaceFolder);
		List<File> result = new ArrayList<File>();
		//��ȡ�����ռ��������е��ļ�
		File[] files = spaceFolder.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				for (String projectName : projectNames) {
					if (projectName.equals(file.getName())) {
						result.add(file);
						break;
					}
				}
			}
		}
		
		return result;
	}
}
