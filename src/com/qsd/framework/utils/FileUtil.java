package com.qsd.framework.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 * 
 * @author suntongwei
 */
public class FileUtil {

	public static final int BUFFER_SIZE = 8 * 1024;

	/**
	 * 复制文件
	 * 
	 * @param src
	 *            源文件
	 * @param dst
	 *            目标文件
	 */
	public static void copy(File src, File dst) {

		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),BUFFER_SIZE);

				byte[] buffer = new byte[BUFFER_SIZE];

				while (in.read(buffer) > 0)
					out.write(buffer);

			} finally {
				if (null != in)
					in.close();

				if (null != out)
					out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void copy(InputStream src, File dst) throws IOException {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				// in = new BufferedInputStream(new
				// FileInputStream(src),BUFFER_SIZE);
				in = new BufferedInputStream(src, BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0)
					out.write(buffer);
			} finally {
				if (null != in)
					in.close();
				if (null != out)
					out.close();
			}
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * 获取文件夹大小
	 * 
	 * @return
	 */
	public static long getFileSize(File f) {
		long size = 0;
		File flist[] = f.listFiles();
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFileSize(flist[i]);
			} else {
				size = size + flist[i].length();
			}
		}
		return size;
	}
}
