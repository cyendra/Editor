package com.cyendra.editor.tree;

import java.io.File;

import javax.swing.JTree;

import com.cyendra.editor.EditorFrame;
import com.cyendra.editor.commons.WorkSpace;

public class TreeCreatorImpl implements TreeCreator {

	public TreeCreatorImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public JTree createTree(EditorFrame editorFrame) {
		File spaceFolder = editorFrame.getWorkSpace().getFolder();
		ProjectTreeNode root = new ProjectTreeNode(spaceFolder, true);
		ProjectTreeModel treeModel = new ProjectTreeModel(root);
		return new JTree(treeModel);
	}

	@Override
	public ProjectTreeNode createNode(File folder) {
		// TODO Auto-generated method stub
		return null;
	}

}
