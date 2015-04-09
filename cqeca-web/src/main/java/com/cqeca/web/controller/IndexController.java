package com.cqeca.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqeca.web.annotation.FilterCheckUrl;

/**
 * @ClassName: IndexController
 * @Description:首页控制器
 * @author chenrui
 * @date 2015-3-31 上午11:17:44
 */
@Controller
public class IndexController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(value = "/index")
	public String toIndex() {
		logger.info("welcome to index page!");
		return "index";
	}

	@FilterCheckUrl(value = false)
	@RequestMapping(value = "/manager/index")
	public String toManagerIndex() {
		logger.info("welcome to manager index page!");
		return "manager_index";
	}

}
