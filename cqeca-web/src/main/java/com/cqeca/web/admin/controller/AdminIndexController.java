package com.cqeca.web.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqeca.web.annotation.FilterCheckUrl;

/**
 * @ClassName: AdminIndexController
 * @Description:后台管理首页控制器
 * @author chenrui
 * @date 2015-3-31 上午11:17:44
 */
@Controller
@RequestMapping(value="/manager")
public class AdminIndexController {

	private static final Logger logger = LoggerFactory.getLogger(AdminIndexController.class);

	@RequestMapping(value = "/main")
	public String toManagerIndex() {
		logger.info("welcome to manager index page!");
		return "manager_index";
	}

}
