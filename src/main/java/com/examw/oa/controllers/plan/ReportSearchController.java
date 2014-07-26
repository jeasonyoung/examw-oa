package com.examw.oa.controllers.plan;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examw.model.DataGrid; 
import com.examw.model.Json;
import com.examw.oa.domain.plan.Report;
import com.examw.oa.domain.plan.ReportDetail;
import com.examw.oa.domain.plan.Settings;
import com.examw.oa.domain.security.Right;
import com.examw.oa.model.plan.ReportReviewInfo;
import com.examw.oa.service.plan.IReportReviewService;
import com.examw.oa.service.plan.IReportService;
import com.examw.oa.service.plan.ISettingsService;
/**
 * 员工报告查询控制器。
 * @author lq.
 * @since 2014-07-06.
 */
@Controller
@RequestMapping(value = "/plan/search")
public class ReportSearchController {
	private static Logger logger = Logger.getLogger(ReportSearchController.class);
	//注入报告设置服务接口。
	@Resource
	private ISettingsService settingsService;
	//注入报告服务接口。
	@Resource
	private IReportService reportService;
	//注入报告查阅服务接口。
	@Resource
	private IReportReviewService reportReviewService;
	/**
	 * 列表页面。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.PLAN_SEARCH + ":" + Right.VIEW})
	@RequestMapping(value = {"","/list"}, method = RequestMethod.GET)
	public String list(Model model){
		if(logger.isDebugEnabled()) logger.debug("加载列表页面...");
		
		model.addAttribute("PER_UPDATE", ModuleConstant.PLAN_SEARCH + ":" + Right.UPDATE);
		model.addAttribute("PER_DELETE", ModuleConstant.PLAN_SEARCH + ":" + Right.DELETE);
		
		model.addAttribute("CURRENT_STATUS_NONE", Report.STATUS_NONE);
		model.addAttribute("CURRENT_STATUS_POST", Report.STATUS_POST);
		model.addAttribute("CURRENT_STATUS_LATE", Report.STATUS_LATE);
		model.addAttribute("CURRENT_STATUS_LACK", Report.STATUS_LACK);
		model.addAttribute("CURRENT_STATUS_AUDIT", Report.STATUS_AUDIT);
		
		Map<String, String> statusMap = new TreeMap<String, String>(new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				Integer x = new Integer(o1), y = new Integer(o2);
				if(x== null || y == null) return o1.compareTo(o2);
				return x - y;
			}
		}), 
		typeMap = new TreeMap<String, String>(new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				Integer x = new Integer(o1), y = new Integer(o2);
				if(x== null || y == null) return o1.compareTo(o2);
				return x - y;
			}
		});
		statusMap.put(Report.STATUS_NONE.toString(), this.reportService.loadStatusName(Report.STATUS_NONE));
		statusMap.put(Report.STATUS_POST.toString(), this.reportService.loadStatusName(Report.STATUS_POST));
		statusMap.put(Report.STATUS_AUDIT.toString(), this.reportService.loadStatusName(Report.STATUS_AUDIT));
		statusMap.put(Report.STATUS_LATE.toString(), this.reportService.loadStatusName(Report.STATUS_LATE));
		statusMap.put(Report.STATUS_LACK.toString(), this.reportService.loadStatusName(Report.STATUS_LACK)); 
		model.addAttribute("ReportStatusMap", statusMap);
		
		typeMap.put(Settings.TYPE_DAILY.toString(), this.settingsService.loadTypeName(Settings.TYPE_DAILY));
		typeMap.put(Settings.TYPE_WEEKLY.toString(), this.settingsService.loadTypeName(Settings.TYPE_WEEKLY));
		typeMap.put(Settings.TYPE_MONTHLY.toString(), this.settingsService.loadTypeName(Settings.TYPE_MONTHLY));
		model.addAttribute("ReportTypeMap", typeMap);
		
		return "plan/search_list";
	}
	/**
	 * 编辑页面。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.PLAN_SEARCH + ":" + Right.UPDATE})
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable String id,Model model){
		if(logger.isDebugEnabled()) logger.debug("加载编辑页面...");
		
		model.addAttribute("TYPE_SUMMARY_NAME", this.reportService.loadDetailTypeName(ReportDetail.TYPE_SUMMARY));
		model.addAttribute("TYPE_PLAN_NAME", this.reportService.loadDetailTypeName(ReportDetail.TYPE_PLAN));
		model.addAttribute("TYPE_SUPPORT_NAME", this.reportService.loadDetailTypeName(ReportDetail.TYPE_SUPPORT));
		model.addAttribute("TYPE_SUGGESTION_NAME", this.reportService.loadDetailTypeName(ReportDetail.TYPE_SUGGESTION));
		
		model.addAttribute("Review", this.reportReviewService.loadReportReview(id));
		
		return "plan/review_edit";
	}
	/**
	 * 查询数据。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.PLAN_SEARCH + ":" + Right.VIEW})
	@RequestMapping(value="/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<ReportReviewInfo> datagrid(ReportReviewInfo info){
		if(logger.isDebugEnabled()) logger.debug("加载列表数据...");
		return this.reportReviewService.datagrid(info);
	}
	/**
	 * 更新数据。
	 * @param info
	 * 更新源数据。
	 * @return
	 * 更新后数据。
	 */
	@RequiresPermissions({ModuleConstant.PLAN_SEARCH + ":" + Right.UPDATE})
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@ResponseBody
	public Json update(ReportReviewInfo info,Model model){
		if(logger.isDebugEnabled()) logger.debug("更新数据...");
		Json result = new Json();
		try {
			result.setData(this.reportReviewService.update(info));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("更新数据发生异常", e);
		}
		return result;
	}
}