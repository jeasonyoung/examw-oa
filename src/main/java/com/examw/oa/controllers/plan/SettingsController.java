package com.examw.oa.controllers.plan;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examw.model.DataGrid;
import com.examw.model.Json;
import com.examw.oa.domain.plan.Settings;
import com.examw.oa.domain.security.Right;
import com.examw.oa.model.plan.SettingsInfo;
import com.examw.oa.service.plan.ISettingsService;
/**
 * 员工计划总结控制器。
 * @author lq.
 * @since 2014-06-24.
 */
@Controller
@RequestMapping(value = "/plan/settings")
public class SettingsController {
	private static final Logger logger = Logger.getLogger(SettingsController.class);
	//计划总计设置服务。
	@Resource
	private ISettingsService settingsService;
	/**
	 * 列表页面。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.PLAN_SETTINGS + ":" + Right.VIEW})
	@RequestMapping(value = {"","/list"}, method = RequestMethod.GET)
	public String list(Model model){
		if(logger.isDebugEnabled()) logger.debug("加载列表页面...");
		
		model.addAttribute("PER_UPDATE", ModuleConstant.PLAN_BUSINESS + ":" + Right.UPDATE);
		model.addAttribute("PER_DELETE", ModuleConstant.PLAN_BUSINESS + ":" + Right.DELETE);
		
		return "plan/settings_list";
	}
	/**
	 * 添加页面。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.PLAN_SETTINGS + ":" + Right.UPDATE})
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(String deptId,Model model){
		if(logger.isDebugEnabled()) logger.debug("加载编辑页面...");
		
		model.addAttribute("CURRENT_DEPT_ID", StringUtils.isEmpty(deptId) ? "" : deptId);
		
		model.addAttribute("TYPE_DAILY_VALUE",Settings.TYPE_DAILY);
		model.addAttribute("TYPE_DAILY_NAME", this.settingsService.loadTypeName(Settings.TYPE_DAILY));
		
		model.addAttribute("TYPE_WEEKLY_VALUE",Settings.TYPE_WEEKLY);
		model.addAttribute("TYPE_WEEKLY_NAME", this.settingsService.loadTypeName(Settings.TYPE_WEEKLY));
		
		model.addAttribute("TYPE_MONTHLY_VALUE",Settings.TYPE_MONTHLY);
		model.addAttribute("TYPE_MONTHLY_NAME", this.settingsService.loadTypeName(Settings.TYPE_MONTHLY));
		
		return "plan/settings_edit";
   }
	/**
	 * 查询数据。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.PLAN_SETTINGS + ":" + Right.VIEW})
	@RequestMapping(value="/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<SettingsInfo> datagrid(SettingsInfo info){
		if(logger.isDebugEnabled()) logger.debug("加载列表数据...");
		return this.settingsService.datagrid(info);
	}
	/**
	 * 更新数据。
	 * @param info
	 * 更新源数据。
	 * @return
	 * 更新后数据。
	 */
	@RequiresPermissions({ModuleConstant.PLAN_SETTINGS + ":" + Right.UPDATE})
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@ResponseBody
	public Json update(SettingsInfo info){
		if(logger.isDebugEnabled()) logger.debug("更新数据...");
		Json result = new Json();
		try {
			if(StringUtils.isEmpty(info.getEmployeeId())){
				result.setSuccess(false);
				result.setMsg("未选择员工！");
				return result;
			}
			if(info.getType() == null || info.getType().length == 0){
				result.setSuccess(false);
				result.setMsg("未选择报告类型！");
				return result;
			}
			result.setData(this.settingsService.update(info));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("更新数据发生异常", e);
		}
		return result;
	}
	/**
	 * 删除数据。
	 * @param id
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.PLAN_SETTINGS + ":" + Right.DELETE})
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public Json delete(String id){
		if(logger.isDebugEnabled()) logger.debug("删除数据［"+ id +"］...");
		Json result = new Json();
		try {
			this.settingsService.delete(id.split("\\|"));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("删除数据["+id+"]时发生异常:", e);
		}
		return result;
	}
}