package com.jt.easymall.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.easymall.util.UUIDUtil;
import com.jt.easymall.util.UploadUtil;
import com.jt.easymall.vo.PicUploadResult;
@Service
public class PicService {

	public PicUploadResult uploadPic(MultipartFile uploadFile) {
		/*
		 * 1.判断后缀
		 * 2.判断木马
		 * 3.生成路径
		 * 4.生成url
		 * 5.存盘
		 * 6.返回
		 * 异常一旦出现，不是木马就是其他问题，返回result对象
		 * erro=1
		 * 
		 */
		PicUploadResult result = new PicUploadResult();
		//获取文件原名称
		String oldFileName = uploadFile.getOriginalFilename();
		//截取后缀，从最后一个"."开始到名称末尾截取
		String extName = oldFileName.substring(oldFileName.lastIndexOf("."));
		//判断是否是jpg,png,gif一员，正则表达式的分组
		if(!extName.matches("^.(jpg|png|gif)$")){
			result.setError(1);
			return result;
		}
		//判断木马，数据中如果有宽和高，就是上图片；如果没有，就不是图片
		try {
			//利用imageIO判断是否为图片数据
			BufferedImage image = ImageIO.read(uploadFile.getInputStream());
			result.setHeight(image.getHeight()+"");//获取图片的高
			result.setWidth(image.getWidth()+"");
			// 生成路径，存储到磁盘的路径，和url虚拟路径都需要一个共同的内容
			//  /dir /upload/2/3/4/5/6/7/f/f/
			String dir = UploadUtil.getUploadPath(oldFileName, "/upload");
			/*
			 * 根据传递的upload生成一个头
			 * 根据原文件名称生成散列的一个多级文件路径
			 * /upload/2/3/4/5/6/7/f/f/
			 * 生成存盘的文件夹路径
			 * 可以使用System.getProperties("user.dir")
			 */
			String path = "./src/main/webapp/"+dir+"/";//文件夹，路径
			//生成磁盘的路径文件夹File
			File _dir = new File(path);//如果已经生成了路径
			if(!_dir.exists()){//如果路径不存在，生成磁盘路径
				_dir.mkdirs();
			}
			//文件名不能使用原文件，会总是重名
			String fileName = UUIDUtil.getUUID()+extName;
			//存盘，将数据输出
			uploadFile .transferTo(new File(path+fileName));
			//error默认0不用动，wedth height url ????
			result.setUrl(dir+"/"+fileName);
			return result;
		} catch (IOException e) {
			result.setError(1);
			return result;
		}
	}

}
