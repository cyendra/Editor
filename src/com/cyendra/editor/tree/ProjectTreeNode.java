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
	public File getFile() {
		return file;
	}
	//--------------------------------------------------------
	public void setFile(File file) {
		this.file = file;
	}
	public List<ProjectTreeNode> getChildren() {
		//清空children, 再重新获取一次
		children.removeAll(children);
		for (int i = 0; i < getChildCount(); i++) {
			children.add((ProjectTreeNode)getChildAt(i));
		}
		return this.children;
	}
	//--------------------------------------------------------
}
