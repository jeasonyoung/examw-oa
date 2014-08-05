package com.examw.oa.controllers.plan;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.examw.aware.IUserAware;
import com.examw.oa.controllers.adm.LeaveController;
/**
 * 我的计划控制器。
 * @author lq.
 * @since 2014-08-05.
 */
@Controller
@RequestMapping(value = "/plan/dept/my")
public class DeptMyController implements IUserAware{
	private static final Logger logger = Logger.getLogger(LeaveController.class);
	private String current_user_id;
	/*
	 * 设置当前用户ID。
	 * @see com.examw.aware.IUserAware#setUserId(java.lang.String)
	 */
	@Override
	public void setUserId(String userId) {
		if(logger.isDebugEnabled()) logger.debug("注入当前用户［id="+ userId +"］...");
		this.current_user_id = userId;
	}
	/*
	 * 设置当前用户名称。
	 * @see com.examw.aware.IUserAware#setUserName(java.lang.String)
	 */
	@Override
	public void setUserName(String userName) {}
	/*
	 * 设置当期用户昵称。
	 * @see com.examw.aware.IUserAware#setUserNickName(java.lang.String)
	 */
	@Override
	public void setUserNickName(String userNickName) {}

}
