package com.cqeca.web.admin.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/***
 * @ClassName: UploadController
 * @Description:文件上传下载功能
 * @author chenrui
 * @date 2015-4-14 下午2:11:41
 */
@Controller
@RequestMapping(value = "/manager")
public class UploadController {

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(HttpServletRequest request, ModelMap model) {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/HH");
		/** 构建图片保存的目录 **/
		String logoPathDir = "/files" + dateformat.format(new Date());
		/** 得到图片保存目录的真实路径 **/
		String logoRealPathDir = request.getSession().getServletContext()
				.getRealPath(logoPathDir);
		/** 根据真实路径创建目录 **/
		File logoSaveFile = new File(logoRealPathDir);
		if (!logoSaveFile.exists())
			logoSaveFile.mkdirs();
		/** 页面控件的文件流 **/
		MultipartFile multipartFile = multipartRequest.getFile("file");
		/** 获取文件的后缀 **/
		String suffix = multipartFile.getOriginalFilename().substring(
				multipartFile.getOriginalFilename().lastIndexOf("."));
		// /**使用UUID生成文件名称**/
		// String logImageName = UUID.randomUUID().toString()+ suffix;//构建文件名称
		String logImageName = multipartFile.getOriginalFilename();
		/** 拼成完整的文件保存路径加文件 **/
		String fileName = logoRealPathDir + File.separator + logImageName;
		File file = new File(fileName);

		try {
			multipartFile.transferTo(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.put("fileName", fileName);
		return "/test/success";

	}

}
