package com.cyendra.editor;

import com.cyendra.editor.frame.AddFrame;
import com.cyendra.editor.frame.WorkspaceFrame;
import com.cyendra.editor.tree.TreeCreator;
import com.cyendra.editor.tree.TreeCreatorImpl;

public class EditorMain {

	public static void main(String[] args) {
		TreeCreator treeCreator = new TreeCreatorImpl();
		EditorFrame frame = new EditorFrame(treeCreator);
		WorkspaceFrame space = new WorkspaceFrame(frame);
		//AddFrame frame = new AddFrame();
		space.setVisible(true);
	}

}
