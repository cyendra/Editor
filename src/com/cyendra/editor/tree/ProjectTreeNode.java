package com.cyendra.editor.tree;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

public class ProjectTreeNode extends DefaultMutableTreeNode {
	private File file;
	private List<ProjectTreeNode> children;
	public ProjectTreeNode(File file, boolean allowsChildren) {
		super(file.getName(), allowsChildren);
		this.file = file;
		children = new ArrayList<ProjectTreeNode>();
	}

}
