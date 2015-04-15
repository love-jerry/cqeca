package com.cqeca.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransationTest {

	@Autowired
	private TestDao testDao;
	
	@Transactional
	public void A() {
		B();
		
	}
	
	@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
	public void B() {
		testDao.insert();
		int i = 1/0;
	}
	
	
}
