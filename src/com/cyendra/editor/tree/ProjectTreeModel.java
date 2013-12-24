package com.cyendra.editor.tree;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public class ProjectTreeModel extends DefaultTreeModel {
	public ProjectTreeModel(TreeNode arg0) {
		super(arg0);
	}
	
	//--------------------------
	public void reload(ProjectTreeNode node, TreeCreator creator) {
		ProjectTreeNode parent = (ProjectTreeNode)node.getParent();//��ȡnode�ڵ�ĸ��ڵ�
		if (parent == null) return;//���ڵ�Ϊnull�����أ�����Ҫreload
		int index = parent.getIndex(node);//��ȡnode�ڵ��ڸ��ڵ������
		parent.remove(index);//��װnode�ڵ��parent��ɾ��
		node = creator.createNode(node.getFile());//��ͨ��TreeCreator��ȡ�µĽڵ�
		parent.insert(node, index);//��ӵ����ڵ���
		super.reload(node);//����DefaultTreeModel��reload����
	}
	
}
