package com.examw.oa.controllers.plan;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examw.model.DataGrid;
import com.examw.oa.domain.plan.Report;
import com.examw.oa.model.plan.ReportInfo;
import com.examw.oa.service.plan.IReportService;
/**
 * 员工报表查询控制器。
 * @author lq.
 * @since 2014-07-06.
 */
@Controller
@RequestMapping(value = "/plan/reportFind")
public class ReportFindController {
	/*
	 * 报表服务接口
	 */
	@Resource
	private IReportService reportSerivce;
	/**
	 * 列表页面。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.VIEW})
	@RequestMapping(value = {"","/list"}, method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("STATUS_NONE_VALUE",Report.STATUS_NONE);
		model.addAttribute("STATUS_NONE_NAME", this.reportSerivce.loadStatusName(Report.STATUS_NONE));
		
		model.addAttribute("STATUS_POST_VALUE",Report.STATUS_POST);
		model.addAttribute("STATUS_POST_NAME", this.reportSerivce.loadStatusName(Report.STATUS_POST));
		
		model.addAttribute("STATUS_AUDIT_VALUE",Report.STATUS_AUDIT);
		model.addAttribute("STATUS_AUDIT_NAME", this.reportSerivce.loadStatusName(Report.STATUS_AUDIT));
		
		model.addAttribute("STATUS_LATE_VALUE",Report.STATUS_LATE);
		model.addAttribute("STATUS_LATE_NAME", this.reportSerivce.loadStatusName(Report.STATUS_LATE));
		
		model.addAttribute("STATUS_LACK_VALUE",Report.STATUS_LACK);
		model.addAttribute("STATUS_LACK_NAME", this.reportSerivce.loadStatusName(Report.STATUS_LACK));
		
		return "plan/reportFind_list";
	}
	/**
	/**
	 * 查询数据。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.VIEW})
	@RequestMapping(value="/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<ReportInfo> datagrid(ReportInfo info){
		return this.reportSerivce.datagrid(info);
	}
}