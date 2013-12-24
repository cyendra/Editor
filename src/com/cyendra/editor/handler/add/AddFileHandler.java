package com.cyendra.editor.handler.add;

import java.io.File;

import javax.swing.JOptionPane;

import com.cyendra.editor.EditorFrame;
import com.cyendra.editor.exception.FileException;
import com.cyendra.editor.frame.AddFrame;
import com.cyendra.editor.tree.ProjectTreeNode;

public class AddFileHandler implements AddHandler {
	public void afterAdd(EditorFrame editorFrame, AddFrame addFrame, Object data) {
		try {
			//获得当前所选择的树节点
			ProjectTreeNode selectNode = editorFrame.getSelectNode();
			//获取当前选择节点所对应的文件
			File folder = selectNode.getFile();
			//如果folder不是一个目录，则用selectNode的父节点（是一个目录）作为新文件的存放目录
			if (!folder.isDirectory()) {
				ProjectTreeNode parent = (ProjectTreeNode)selectNode.getParent();
				selectNode = parent;
				folder = parent.getFile();
			}
			//创建文件，放到folder下
			File newFile = new File(folder.getAbsoluteFile() + File.separator + data);
			//如果文件已经存在，就弹出提示并返回
			if (newFile.exists()) {
				JOptionPane.showMessageDialog(addFrame, "文件已经存在");
				return;
			}
			newFile.createNewFile();
			editorFrame.reloadNode(selectNode);
			//使主编辑frame可用
			editorFrame.setEnabled(true);
			///让添加的frame不可见
			addFrame.setVisible(false);
		} catch (Exception e) {
			throw new FileException("create file error: " + e.getMessage());
		}
	}

}
