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
		
		//获取工作空间下面所有的目录（即与有projectName.project相对应的目录），也就是项目目录
		List<File> projectFolders = getProjectFolders(spaceFolder);
		
		//遍历项目目录集合，并为其创建子节点
		for (int i = 0; i < projectFolders.size(); i++) {
			File projectFolder = projectFolders.get(i);//获取循环中的目录
			ProjectTreeNode node = createNode(projectFolder);//调用createNode创建它所有的子节点
			root.add(node);//向根节点添加子节点（项目目录）
		}

		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();//定制节点图片
		renderer.setOpenIcon(ImageUtil.getImageIcon(ImageUtil.FOLDER_OPEN));//目录打开时的图片
		renderer.setLeafIcon(ImageUtil.getImageIcon(ImageUtil.FILE));//节点没有子节点的图片
		renderer.setClosedIcon(ImageUtil.getImageIcon(ImageUtil.FOLDER_CLOSE));//目录关闭时的图片
		tree.setCellRenderer(renderer);//设置树的部件处理类为上面的renderer
		
		tree.addMouseListener(new ProjectTreeSelectionListener(editorFrame));//为项目树添加一个树选择监听器
		
		TreePath path = new TreePath(root);//创建根在项目树中的路径
		tree.expandPath(path);//让树默认展开根节点
		tree.setRootVisible(false);//设置树的根节点可见
		return tree;
	}

	@Override
	public ProjectTreeNode createNode(File folder) {
		ProjectTreeNode parent = null;//创建一个父节点，即本方法即将返回的节点对象
		
		// 如果参数foler不是一个目录的话，创建一个ProjectTreeNode对象并返回，表明它不允许拥有子节点
		// 如果是一个目录的话，则创建上面的parent，表明它是一个目录，可以拥有子节点
		if (!folder.isDirectory()) return new ProjectTreeNode(folder, false);
		else parent = new ProjectTreeNode(folder, true);
			
		// 利用上面的parent节点去查找它下面所有的直接节点
		List<ProjectTreeNode> nodes = createNodes(parent.getFile());
		
		for (ProjectTreeNode node : nodes) { // 获取到parent下面的所有直接子节点后，再去循环递归调用本方法
			parent.add(createNode(node.getFile()));// 递归创建子节点，并将返回的节点添加到parent中
		}
		return parent;
	}

	private List<ProjectTreeNode> createNodes(File folder) {
		File[] files = folder.listFiles();//获取该目录下的所有文件
		List<ProjectTreeNode> result = new ArrayList<ProjectTreeNode>();
		for (File file : files) {	
			if (file.isDirectory()) result.add(new ProjectTreeNode(file, true));
			if (!file.isDirectory()) result.add(new ProjectTreeNode(file, false));
		}
		return result;
	}
	
	/**
	 * 获取工作空间目录下所有的项目名称
	 * @return
	 */
	private List<String> getProjectNames(File spaceFolder) {
		List<String> result = new ArrayList<String>();
		for (File file : spaceFolder.listFiles()) {
			if (file.isDirectory()){
				for (File proj : file.listFiles()){
					if (proj.getName().endsWith(".project")) {//取以.project结尾的文件
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
	 * 获取工作空间目录下所有的项目目录
	 */
	private List<File> getProjectFolders(File spaceFolder) {
		List<String> projectNames = getProjectNames(spaceFolder);
		List<File> result = new ArrayList<File>();
		//获取工作空间下面所有的文件
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
