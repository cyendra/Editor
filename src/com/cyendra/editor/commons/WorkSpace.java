package com.cyendra.editor.commons;

import java.io.File;

import com.cyendra.editor.EditorFrame;

public class WorkSpace {
	private File folder;//�����ռ��Ӧ��Ŀ¼
	private EditorFrame editorFrame;//�����ռ��е����༭������
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
