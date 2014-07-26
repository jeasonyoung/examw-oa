package com.examw.oa.controllers.plan;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examw.aware.IUserAware;
import com.examw.model.DataGrid;
import com.examw.model.Json;
import com.examw.oa.domain.plan.Report;
import com.examw.oa.domain.plan.ReportDetail;
import com.examw.oa.domain.security.Right;
import com.examw.oa.model.plan.ReportInfo;
import com.examw.oa.service.plan.IBusinessService;
import com.examw.oa.service.plan.IReportService;
/**
 * 员工报告控制器。
 * @author lq.
 * @since 2014-06-26.
 */
@Controller
@RequestMapping(value = "/plan/report")
public class ReportController implements IUserAware {
	private static final Logger logger = Logger.getLogger(ReportController.class);
	private String current_user_id;
	//报告服务接口。
	@Resource
	private IReportService reportSerivce;
	//业务系统服务
	@Resource
	private IBusinessService businessService;
	/*
	 * 设置当前用户ID。
	 * @see com.examw.aware.IUserAware#setUserId(java.lang.String)
	 */
	@Override
	public void setUserId(String userId) {
		if(logger.isDebugEnabled()) logger.debug("注入当前用户：" + userId);
		this.current_user_id = userId;
	}
	/*
	 * 设置当前用户名称。
	 * @see com.examw.aware.IUserAware#setUserName(java.lang.String)
	 */
	@Override
	public void setUserName(String userName) {}
	/*
	 * 设置当前用户昵称。
	 * @see com.examw.aware.IUserAware#setUserNickName(java.lang.String)
	 */
	@Override
	public void setUserNickName(String nickName) {}
	/**
	 * 列表页面。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.PLAN_REPORT + ":" + Right.VIEW})
	@RequestMapping(value = {"","/list"}, method = RequestMethod.GET)
	public String list(Model model){
		if(logger.isDebugEnabled()) logger.debug("加载列表页面...");
		
		model.addAttribute("PER_UPDATE", ModuleConstant.PLAN_REPORT + ":" + Right.UPDATE);
		model.addAttribute("PER_DELETE", ModuleConstant.PLAN_REPORT + ":" + Right.DELETE);
		
		model.addAttribute("CURRENT_STATUS_NONE", Report.STATUS_NONE);
		model.addAttribute("CURRENT_STATUS_POST", Report.STATUS_POST);
		model.addAttribute("CURRENT_STATUS_LATE", Report.STATUS_LATE);
		model.addAttribute("CURRENT_STATUS_LACK", Report.STATUS_LACK);
		model.addAttribute("CURRENT_STATUS_AUDIT", Report.STATUS_AUDIT);
		
		return "plan/report_list";
	}
	/**
	 * 编辑页面。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.PLAN_REPORT + ":" + Right.UPDATE})
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Integer status,Model model){
		if(logger.isDebugEnabled()) logger.debug("加载编辑页面...");
		
		model.addAttribute("TYPE_SUMMARY_NAME", this.reportSerivce.loadDetailTypeName(ReportDetail.TYPE_SUMMARY));
		model.addAttribute("TYPE_PLAN_NAME", this.reportSerivce.loadDetailTypeName(ReportDetail.TYPE_PLAN));
		model.addAttribute("TYPE_SUPPORT_NAME", this.reportSerivce.loadDetailTypeName(ReportDetail.TYPE_SUPPORT));
		model.addAttribute("TYPE_SUGGESTION_NAME", this.reportSerivce.loadDetailTypeName(ReportDetail.TYPE_SUGGESTION));
		
		return "plan/report_edit";
   }
	/**
	 * 查询数据。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.PLAN_REPORT + ":" + Right.VIEW})
	@RequestMapping(value="/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<ReportInfo> datagrid(ReportInfo info){
		if(logger.isDebugEnabled()) logger.debug("加载列表数据...");
		info.setCurrentUserId(this.current_user_id);
		return this.reportSerivce.datagrid(info);
	}
	/**
	 * 更新数据。
	 * @param info
	 * 更新源数据。
	 * @return
	 * 更新后数据。
	 */
	@RequiresPermissions({ModuleConstant.PLAN_REPORT + ":" + Right.UPDATE})
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@ResponseBody
	public Json update(ReportInfo info,Model model){
		if(logger.isDebugEnabled()) logger.debug("更新数据...");
		Json result = new Json();
		try {
			result.setData(this.reportSerivce.update(info));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("更新数据发生异常", e);
		}
		return result;
	}
}