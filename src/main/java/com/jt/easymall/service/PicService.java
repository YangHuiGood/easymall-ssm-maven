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
		 * 1.�жϺ�׺
		 * 2.�ж�ľ��
		 * 3.����·��
		 * 4.����url
		 * 5.����
		 * 6.����
		 * �쳣һ�����֣�����ľ������������⣬����result����
		 * erro=1
		 * 
		 */
		PicUploadResult result = new PicUploadResult();
		//��ȡ�ļ�ԭ����
		String oldFileName = uploadFile.getOriginalFilename();
		//��ȡ��׺�������һ��"."��ʼ������ĩβ��ȡ
		String extName = oldFileName.substring(oldFileName.lastIndexOf("."));
		//�ж��Ƿ���jpg,png,gifһԱ��������ʽ�ķ���
		if(!extName.matches("^.(jpg|png|gif)$")){
			result.setError(1);
			return result;
		}
		//�ж�ľ������������п�͸ߣ�������ͼƬ�����û�У��Ͳ���ͼƬ
		try {
			//����imageIO�ж��Ƿ�ΪͼƬ����
			BufferedImage image = ImageIO.read(uploadFile.getInputStream());
			result.setHeight(image.getHeight()+"");//��ȡͼƬ�ĸ�
			result.setWidth(image.getWidth()+"");
			// ����·�����洢�����̵�·������url����·������Ҫһ����ͬ������
			//  /dir /upload/2/3/4/5/6/7/f/f/
			String dir = UploadUtil.getUploadPath(oldFileName, "/upload");
			/*
			 * ���ݴ��ݵ�upload����һ��ͷ
			 * ����ԭ�ļ���������ɢ�е�һ���༶�ļ�·��
			 * /upload/2/3/4/5/6/7/f/f/
			 * ���ɴ��̵��ļ���·��
			 * ����ʹ��System.getProperties("user.dir")
			 */
			String path = "./src/main/webapp/"+dir+"/";//�ļ��У�·��
			//���ɴ��̵�·���ļ���File
			File _dir = new File(path);//����Ѿ�������·��
			if(!_dir.exists()){//���·�������ڣ����ɴ���·��
				_dir.mkdirs();
			}
			//�ļ�������ʹ��ԭ�ļ�������������
			String fileName = UUIDUtil.getUUID()+extName;
			//���̣����������
			uploadFile .transferTo(new File(path+fileName));
			//errorĬ��0���ö���wedth height url ????
			result.setUrl(dir+"/"+fileName);
			return result;
		} catch (IOException e) {
			result.setError(1);
			return result;
		}
	}

}
