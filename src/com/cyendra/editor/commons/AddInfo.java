package com.cyendra.editor.commons;

import com.cyendra.editor.EditorFrame;
import com.cyendra.editor.handler.add.AddHandler;

public class AddInfo {
	private String info;
	private EditorFrame editorFrame;
	private AddHandler handler;
	public AddInfo(String info, EditorFrame editorFrame, AddHandler handler) {
		this.info = info;
		this.editorFrame = editorFrame;
		this.handler = handler;
	}

	public AddHandler getHandler() {
		return handler;
	}

	public void setHandler(AddHandler handler) {
		this.handler = handler;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public EditorFrame getEditorFrame() {
		return editorFrame;
	}

	public void setEditorFrame(EditorFrame editorFrame) {
		this.editorFrame = editorFrame;
	}
}
