package com.examw.oa.controllers.plan;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examw.model.DataGrid;
import com.examw.model.Json;
import com.examw.oa.controllers.security.LogController;
import com.examw.oa.domain.plan.Detail;
import com.examw.oa.domain.plan.Report;
import com.examw.oa.model.plan.ReportInfo;
import com.examw.oa.service.plan.IDetailService;
import com.examw.oa.service.plan.IReportService;
/**
 * 员工报表控制器。
 * @author lq.
 * @since 2014-06-26.
 */
@Controller
@RequestMapping(value = "/plan/detail")
public class DetailController {
	private static Logger logger = Logger.getLogger(LogController.class);
	/**
	 * 员工计划服务接口
	 */
	@Resource
	private IReportService reportSerivce;
	/**
	 * 计划总结服务接口
	 */
	@Resource
	private IDetailService detailService;
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
		
		return "plan/detail_list";
	}
	/**
	 * 添加页面。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.UPDATE})
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model){
		model.addAttribute("TYPE_PLAN_NAME", this.detailService.loadTypeName(Detail.TYPE_PLAN));
		model.addAttribute("TYPE_SUMMARY_NAME",this.detailService.loadTypeName(Detail.TYPE_SUMMARY));
		model.addAttribute("TYPE_SUPPORT_NAME",this.detailService.loadTypeName(Detail.TYPE_SUPPORT ));
		model.addAttribute("TYPE_SUGGESTIONS_NAME",this.detailService.loadTypeName(Detail.TYPE_SUGGESTIONS));
		return "plan/detail_edit";
   }
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
	/**
	 * 更新数据。
	 * @param info
	 * 更新源数据。
	 * @return
	 * 更新后数据。
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.UPDATE})
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@ResponseBody
	public Json update(ReportInfo info,String stutasDteail,Model model){
		Json result = new Json();
		try {
			if(stutasDteail !=null){
				info.setStatus(Report.STATUS_AUDIT);
			}
			result.setData(this.reportSerivce.update(info));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("更新员工报表信息数据发生异常", e);
		}
		return result;
	}
}