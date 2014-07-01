package com.examw.oa.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.examw.oa.domain.plan.Settings;
import com.examw.oa.service.plan.ISettingsService;
import com.thoughtworks.xstream.XStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-examw-oa.xml"})
@Transactional
public class SettingsTest {
	//settings
	@Resource
	private ISettingsService settingsService;
	@Test
	public void testBitAndFunction(){
		List<Settings> list =  this.settingsService.findSettings(Settings.TYPE_DAY);
		XStream xStream = new XStream();
		String xml = xStream.toXML(list);
		System.out.print(xml);
	}
}