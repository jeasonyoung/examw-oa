package com.examw.oa.test.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.examw.oa.service.plan.IReportService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-examw-oa.xml"})
public class SettingsTest {
	@Resource
	private IReportService reportService;
	 
	@Test
	public void testCreateReport(){
		 //this.reportService.addTaskDaily();
		this.reportService.addTaskWeek();
		 //this.reportService.addTaskMonth();
		 
	}
}