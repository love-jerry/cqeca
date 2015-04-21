package com.cqeca.web.admin.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cqeca.util.constant.FiledsConstant;
import com.cqeca.web.annotation.FilterCheckUrl;

/***
 * @ClassName: UploadController
 * @Description:文件上传下载功能
 * @author chenrui
 * @date 2015-4-14 下午2:11:41
 */
@Controller
@RequestMapping(value = "/manager")
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	/**
	 * 上传文件
	 * @param request
	 * @return
	 */
//	@FilterCheckUrl(value = true)
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadFile(HttpServletRequest request) {
		logger.info("文件上传开始");
		Map<String,String> result = new HashMap<String,String>();
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
			/** 构建图片保存的目录 **/
			String logoPathDir = FiledsConstant.UPLOAD_PERFIX_PATH + dateformat.format(new Date());
			/** 得到图片保存目录的真实路径 **/
			String logoRealPathDir = request.getSession().getServletContext().getRealPath(logoPathDir);
			/** 根据真实路径创建目录 **/
			File logoSaveFile = new File(logoRealPathDir);
			if (!logoSaveFile.exists()) {
				logoSaveFile.mkdirs();
			}
			/** 页面控件的文件流 **/
			MultipartFile multipartFile = multipartRequest.getFile("imgFile");
			/** 获取文件的后缀 **/
			String logImageName = multipartFile.getOriginalFilename();
			/** 拼成完整的文件保存路径加文件 **/
			String fileName = logoRealPathDir + File.separator + logImageName;
			File file = new File(fileName);
			multipartFile.transferTo(file);
			result.put("code", "ok");
			result.put("filePath", fileName);
		} catch (Exception e) {
			logger.error("upload file error",e);
			result.put("code", "error");
		}
		logger.info("文件上传结束!");
		return result;
	}

	/***
	 * 文件下载
	 * @param fileName
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@FilterCheckUrl(value = false)
	@RequestMapping("/download/{fileName}")  
    public void download(String fileName, HttpServletRequest request, HttpServletResponse response)  
            throws Exception {
        response.setContentType("text/html;charset=utf-8");  
        request.setCharacterEncoding("UTF-8");  
        java.io.BufferedInputStream bis = null;  
        java.io.BufferedOutputStream bos = null;  
  
        String ctxPath = request.getSession().getServletContext().getRealPath("/") + FiledsConstant.DOWNLOAD_PERFIX_PATH;  
        String downLoadPath = ctxPath + fileName;  
        logger.info(downLoadPath);  
        try {  
            long fileLength = new File(downLoadPath).length();  
            response.setContentType("application/x-msdownload;");  
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));  
            response.setHeader("Content-Length", String.valueOf(fileLength));  
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));  
            bos = new BufferedOutputStream(response.getOutputStream());  
            byte[] buff = new byte[2048];  
            int bytesRead;  
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
                bos.write(buff, 0, bytesRead);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (bis != null)  
                bis.close();  
            if (bos != null)  
                bos.close();  
        }  
    }  
}
