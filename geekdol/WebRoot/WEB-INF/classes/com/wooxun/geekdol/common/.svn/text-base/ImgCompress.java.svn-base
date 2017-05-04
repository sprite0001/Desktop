package com.wooxun.geekdol.common;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @Title
 * @Description 图片压缩处理
 * @version 1.0.0
 * @Author 863soft-王肖东
 * @CreateDate 2016年9月12日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. 王肖东 2016年9月12日 下午1:49:49 创建
 *           ==========================================================
 * 
 */
public class ImgCompress {
	private Image img;
	private int width;
	private int height;
	private File file;

	/**
	 * 构造函数
	 */
	public ImgCompress(File file) throws IOException {
		img = ImageIO.read(file); // 构造Image对象
		width = img.getWidth(null); // 得到源图宽
		height = img.getHeight(null); // 得到源图长
		this.file = file; // 要压缩的文件
	}

	/**
	 * 按照宽度还是高度进行压缩
	 * 
	 * @param w
	 *            int 最大宽度
	 * @param h
	 *            int 最大高度
	 */
	public void resizeFix(int w, int h) throws IOException {
		if (width / height > w / h) {
			resizeByWidth(w);
		} else {
			resizeByHeight(h);
		}
	}

	/**
	 * 以宽度为基准，等比例放缩图片
	 * 
	 * @param w
	 *            int 新宽度
	 */
	public void resizeByWidth(int w) throws IOException {
		int h = (int) (height * w / width);
		resize(w, h);
	}

	/**
	 * 以高度为基准，等比例缩放图片
	 * 
	 * @param h
	 *            int 新高度
	 */
	public void resizeByHeight(int h) throws IOException {
		int w = (int) (width * h / height);
		resize(w, h);
	}

	/**
	 * 强制压缩/放大图片到固定的大小
	 * 
	 * @param w
	 *            int 新宽度
	 * @param h
	 *            int 新高度
	 */
	public void resize(int w, int h) throws IOException {
		// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
		FileOutputStream out = new FileOutputStream(file); // 输出到文件流
		// 可以正常实现bmp、png、gif转jpg
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image); // JPEG编码
		out.close();
	}
}
