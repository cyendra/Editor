package com.cyendra.editor.tree;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public class ProjectTreeModel extends DefaultTreeModel {
	public ProjectTreeModel(TreeNode arg0) {
		super(arg0);
	}
	
	//--------------------------
	public void reload(ProjectTreeNode node, TreeCreator creator) {
		ProjectTreeNode parent = (ProjectTreeNode)node.getParent();//获取node节点的父节点
		if (parent == null) return;//父节点为null，返回，不需要reload
		int index = parent.getIndex(node);//获取node节点在父节点的索引
		parent.remove(index);//先装node节点从parent中删除
		node = creator.createNode(node.getFile());//再通过TreeCreator获取新的节点
		parent.insert(node, index);//添加到父节点中
		super.reload(node);//调用DefaultTreeModel的reload方法
	}
	
}
