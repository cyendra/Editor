package com.cyendra.editor.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.cyendra.editor.exception.FileException;

public class FileUtil {

	public static String readFile(File file){
		StringBuffer result = new StringBuffer();
		try{
			FileInputStream fis = new FileInputStream(file);
			String content = null;
			byte[] arr = new byte[1024];
			int readLength;
			while ((readLength = fis.read(arr)) > 0){
				content = new String(arr,0,readLength);
				result.append(content);
			}
			fis.close();
		}
		catch(IOException e){
			throw new FileException("reaad '"+file.getAbsolutePath()+"'file error");
		}
		return result.toString();
	}

}
