package com.cqeca.web.admin.controller;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqeca.service.login.session.SessionEntity;
import com.cqeca.service.news.NewsService;
import com.cqeca.util.constant.FiledsConstant;
import com.cqeca.util.enums.NewsTypeEnum;
import com.cqeca.util.tools.DateFormatUtil;
import com.cqeca.util.tools.ImageUtil;
import com.cqeca.util.tools.RegUtil;

/**
* @ClassName: AdminNewsController 
* @Description:后台新闻管理控制器 
* @author chenrui
* @date 2015-4-10 下午4:53:06
 */
@Controller
@RequestMapping(value="/manager")
public class AdminNewsController {

	@Autowired
	private NewsService newsService;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminNewsController.class);

	@RequestMapping(value="/add_article")
	public String addNews() {
		return "add_article";
	}
	
	@RequestMapping(value="/publish",method=RequestMethod.POST)
	@ResponseBody
	public Object publishNews(String title,String content,String category,String tag, HttpServletRequest request,Model model) {
		logger.info("welcome to publish news page");
		logger.info("tile=" + title + ",category=" + category + ",tag=" + tag);
		HttpSession session = request.getSession();
		
		SessionEntity sessionEntity = (SessionEntity)(session.getAttribute(FiledsConstant.SESSION_KEY));
		String userId = sessionEntity.getUserId();
//		String userId = "75870570-9d50-46ee-880f-b867133951d4";
		NewsTypeEnum newsType = NewsTypeEnum.getByMessage(category);
		//handle img
		/** 构建图片保存的目录 **/
		String imgPathDir = FiledsConstant.UPLOAD_PERFIX_PATH + DateFormatUtil.dtSimpleFormat(new Date());
		
		/** 得到图片保存目录的真实路径 **/
		String imgRealPathDir = request.getSession().getServletContext().getRealPath(imgPathDir) + "/";
		
		List<String> imgList = RegUtil.matchStr(content, FiledsConstant.IMG_REG_STR);
		if(null != imgList && imgList.size() > 0) {
			String imgTitleStr = "";
			String imgSrcStr = "";
			String imgTitle = "";
			String imgSrc = "";
			String imgBuffer = "";
			boolean status = false;
			String filePath = "";
			for(String imgStr : imgList) {
				imgTitleStr = RegUtil.matchChliedStr(imgStr,FiledsConstant.IMG_TITLE_REG_STR);
				imgSrcStr = RegUtil.matchChliedStr(imgStr,FiledsConstant.IMG_SRC_REG_STR);
				if(null == imgSrcStr || "".equals(imgSrcStr)) {
					continue;
				}
				//只处理data:image图片流，其他情况不做处理
				imgTitle = imgTitleStr.split("\"")[1];
				imgSrc = imgSrcStr.split("\"")[1];
				imgBuffer = imgStr.substring(imgStr.indexOf(',') + 1);
				
				filePath = imgRealPathDir + imgTitle;
				File fileDir = new File(imgRealPathDir);
				if(!fileDir.exists()) {
					fileDir.mkdirs();
				}
				status = ImageUtil.createImageFile(imgBuffer, filePath);
				logger.info("file=" + filePath);
				if(status) {
					imgSrc = RegUtil.escapeExprSpecialWord(imgSrc);
					logger.info("imgSrc=" + imgSrc);
					filePath = RegUtil.escapeExprSpecialWord(filePath);
					logger.info( "filePath=" + filePath);
					content = content.replaceAll(imgSrc, "/cqeca" + imgPathDir + "/" + imgTitle);
				}
			}
		}
		
		newsService.saveNews(userId, newsType, title, content, tag);
		Map<String,String> result = new HashMap<String,String>();
		result.put("code", "200");
		return result;
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object deleteNews(String id) {
		logger.info("into deleta page  newsId=" + id);
		newsService.deleteNewsById(id);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("code", 200);
		return result;
	}
	
	@RequestMapping(value="/preview",method=RequestMethod.POST)
	public String deleteNews(String title,String content,String date,Model model) {
		logger.info("welcome to preview page");
		model.addAttribute("title", title);
		model.addAttribute("date", DateFormatUtil.dtSimpleFormat(Calendar.getInstance().getTime()));
		model.addAttribute("content", content);
		
		return "preview_article";
	}
}
