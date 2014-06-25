package com.examw.oa.controllers.plan;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examw.model.DataGrid;
import com.examw.model.Json;
import com.examw.oa.controllers.security.LogController;
import com.examw.oa.model.plan.SettingsInfo;
import com.examw.oa.service.plan.ISettingsService;
/**
 * 员工报表控制器。
 * @author lq.
 * @since 2014-06-24.
 */
@Controller
@RequestMapping(value = "/plan/settings")
public class SettingsController {
	private static Logger logger = Logger.getLogger(LogController.class);
	/**
	 * 员工报表服务接口。
	 */
	@Resource
	private ISettingsService settingsService;
	/**
	 * 列表页面。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.VIEW})
	@RequestMapping(value = {"","/list"}, method = RequestMethod.GET)
	public String list(Model model){
		return "plan/settings_list";
	}
	/**
	 * 添加页面。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.UPDATE})
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(String deptId,String empId, Model model){
		model.addAttribute("CURRENT_DEPT_ID", StringUtils.isEmpty(deptId) ? "" : deptId);
		model.addAttribute("CURRENT_EMP_ID", StringUtils.isEmpty(empId) ? "" : empId);
		return "plan/settings_edit";
   }
	/**
	 * 查询数据。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.VIEW})
	@RequestMapping(value="/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<SettingsInfo> datagrid(SettingsInfo info){
		return this.settingsService.datagrid(info);
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
	public Json update(SettingsInfo info){
		Json result = new Json();
		try {
			result.setData(this.settingsService.update(info));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("更新部门数据发生异常", e);
		}
		return result;
	}
	/**
	 * 删除数据。
	 * @param id
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.DELETE})
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public Json delete(String id){
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