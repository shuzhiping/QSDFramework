package com.qsd.framework.utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * 
 * @author suntongwei
 */
public final class ImageUtils {

	public enum ImageFileType {
		PNG("png"), JPG("jpg"), JPEG("jpeg"), GIF("gif");
		private String name;
		private ImageFileType(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return this.name;
		}
	}

	/**
	 * 缩放图片
	 * 
	 * @return
	 */
	public static BufferedImage zoomImage(int width, int height,BufferedImage originalImage) {
		BufferedImage newImage = new BufferedImage(width, height, originalImage.getType());
		Graphics g = newImage.getGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
		return newImage;
	}

	public static void zoomImage(int width, int height, String srcPath, String newPath) throws IOException {
		zoomImage(width, height, new File(srcPath), new File(newPath));
	}

	public static boolean zoomImage(int width, int height,
			MultipartFile srcPath, File newPath) {
		BufferedImage bufferedImage = null;
		try {
			if (srcPath != null && srcPath.getSize() > 0) {
				bufferedImage = ImageIO.read(srcPath.getInputStream());
			}
		} catch (IOException e) {
			return false;
		}
		if (bufferedImage != null) {
			bufferedImage = zoomImage(width, height, bufferedImage);
			try {
				ImageIO.write(bufferedImage, ImageFileType.JPEG.toString(),newPath); 
			} catch (IOException e) {
				return false;
			}
		}
		return true;
	}

	public static void zoomImage(int width, int height, File srcPath, File newPath) throws IOException {
		BufferedImage bufferedImage = null;
		if (srcPath.canRead()) {
			bufferedImage = ImageIO.read(srcPath);
		}
		if (bufferedImage != null) {
			bufferedImage = zoomImage(width, height, bufferedImage);
			// 保存修改后的图像,全部保存为JPG格式
			ImageIO.write(bufferedImage, ImageFileType.JPEG.toString(),newPath);
		}
	}

	/**
	 * 对图片进行放大
	 * 
	 * @param originalImage
	 *            原始图片
	 * @param times
	 *            放大倍数
	 * @return
	 */
	public static BufferedImage zoomInImage(BufferedImage originalImage,Integer times) {
		int width = originalImage.getWidth() * times;
		int height = originalImage.getHeight() * times;
		BufferedImage newImage = new BufferedImage(width, height, originalImage.getType());
		Graphics g = newImage.getGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
		return newImage;
	}

	/**
	 * 对图片进行放大
	 * 
	 * @param srcPath
	 *            原始图片路径(绝对路径)
	 * @param newPath
	 *            放大后图片路径（绝对路径）
	 * @param times
	 *            放大倍数
	 * @return 是否放大成功
	 */
	public static boolean zoomInImage(String srcPath, String newPath,Integer times) {
		BufferedImage bufferedImage = null;
		try {
			File of = new File(srcPath);
			if (of.canRead()) {
				bufferedImage = ImageIO.read(of);
			}
		} catch (IOException e) {
			return false;
		}
		if (bufferedImage != null) {
			bufferedImage = zoomInImage(bufferedImage, times);
			try {
				ImageIO.write(bufferedImage, ImageFileType.JPG.toString(),new File(newPath)); 
			} catch (IOException e) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 对图片进行缩小
	 * 
	 * @param originalImage
	 *            原始图片
	 * @param times
	 *            缩小倍数
	 * @return 缩小后的Image
	 */
	public static BufferedImage zoomOutImage(BufferedImage originalImage,Integer times) {
		int width = originalImage.getWidth() / times;
		int height = originalImage.getHeight() / times;
		BufferedImage newImage = new BufferedImage(width, height, originalImage.getType());
		Graphics g = newImage.getGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
		return newImage;
	}

	/**
	 * 对图片进行缩小
	 * 
	 * @param srcPath
	 *            源图片路径（绝对路径）
	 * @param newPath
	 *            新图片路径（绝对路径）
	 * @param times
	 *            缩小倍数
	 * @return 保存是否成功
	 */
	public static boolean zoomOutImage(String srcPath, String newPath,Integer times) {
		BufferedImage bufferedImage = null;
		try {
			File of = new File(srcPath);
			if (of.canRead()) {
				bufferedImage = ImageIO.read(of);
			}
		} catch (IOException e) {
			return false;
		}
		if (bufferedImage != null) {
			bufferedImage = zoomOutImage(bufferedImage, times);
			try {
				ImageIO.write(bufferedImage, ImageFileType.JPG.toString(),new File(newPath));
			} catch (IOException e) {
				return false;
			}
		}
		return true;
	}

}
