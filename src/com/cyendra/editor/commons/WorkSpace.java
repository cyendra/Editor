package com.cyendra.editor.commons;

import java.io.File;

import com.cyendra.editor.EditorFrame;

public class WorkSpace {
	private File folder;//工作空间对应的目录
	private EditorFrame editorFrame;//工作空间中的主编辑区对象
	public WorkSpace(File folder, EditorFrame editorFrame) {
		this.folder = folder;
		this.editorFrame = editorFrame;
	}
	public EditorFrame getEditorFrame() {
		return editorFrame;
	}
	public void setEditorFrame(EditorFrame editorFrame) {
		this.editorFrame = editorFrame;
	}
	public File getFolder() {
		return folder;
	}
	public void setFolder(File folder) {
		this.folder = folder;
	}
}
