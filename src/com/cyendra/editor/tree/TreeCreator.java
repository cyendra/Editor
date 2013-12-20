package com.cyendra.editor.tree;

import java.io.File;

import javax.swing.JTree;

import com.cyendra.editor.EditorFrame;

public interface TreeCreator {
	JTree createTree(EditorFrame editorFrame);
	ProjectTreeNode createNode(File folder);
}
