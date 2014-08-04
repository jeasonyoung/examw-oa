package com.examw.oa.controllers.plan;

import java.util.List;

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
import com.examw.oa.controllers.org.ModuleConstant;
import com.examw.oa.domain.plan.DeptPlan;
import com.examw.oa.domain.security.Right;
import com.examw.oa.model.plan.DeptPlanInfo;
import com.examw.oa.model.plan.DeptPlanMemberInfo;
import com.examw.oa.service.plan.IDeptPlanMenberService;
import com.examw.oa.service.plan.IDeptPlanService;
/**
 * 部门计划控制器。
 * @author lq.
 * @since 2014-08-01.
 */
@Controller
@RequestMapping(value = "/plan/dept")
public class DeptPlanController {
	private static final Logger logger = Logger.getLogger(DeptPlanController.class);
	//部门计划服务接口。
	@Resource
	private IDeptPlanService deptPlanService;
	@Resource
	private IDeptPlanMenberService menberService;
	/**
	 * 列表页面。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.PLAN_REPORT + ":" + Right.VIEW})
	@RequestMapping(value = {"","/list"}, method = RequestMethod.GET)
	public String list(Model model){
		if(logger.isDebugEnabled()) logger.debug("加载列表页面...");
		
		return "/plan/dept_list";
	}
	/**
	 * 获取编辑页面。
	 * @return
	 * 编辑页面。
	 */
	//@RequiresPermissions({ModuleConstant.ORG_DEPT + ":" + Right.UPDATE})
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String edit(String planId,String ignore,Model model){
		if(logger.isDebugEnabled()) logger.debug("加载编辑页面...");
		model.addAttribute("CURRENT_PARENT_DEPT_ID", planId);
		model.addAttribute("CURRENT_IGNORE", ignore);
		
		model.addAttribute("TYPE_WEEK_VALUE", DeptPlan.TYPE_WEEK);
		model.addAttribute("TYPE_WEEK_NAME", this.deptPlanService.loadTypeName(DeptPlan.TYPE_WEEK));
		
		model.addAttribute("TYPE_MONTH_VALUE", DeptPlan.TYPE_MONTH);
		model.addAttribute("TYPE_MONTH_NAME", this.deptPlanService.loadTypeName(DeptPlan.TYPE_MONTH));
		return "/plan/dept_edit";
	}
	
	/**
	 * 查询数据。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ORG_DEPT + ":" + Right.VIEW})
	@RequestMapping(value="/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<DeptPlanInfo> datagrid(DeptPlanInfo info){
		if(logger.isDebugEnabled()) logger.debug("加载列表数据...");
		return this.deptPlanService.datagrid(info);
	}
	/**
	 * 更新数据。
	 * @param info
	 * 更新源数据。
	 * @return
	 * 更新后数据。
	 */
	@RequiresPermissions({ModuleConstant.ORG_DEPT + ":" + Right.UPDATE})
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@ResponseBody
	public Json update(DeptPlanInfo info){
		if(logger.isDebugEnabled()) logger.debug("更新数据...");
		Json result = new Json();
		try {
			result.setData(this.deptPlanService.update(info));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("更新部门计划数据发生异常", e);
		}
		return result;
	}
	/**
	 * 删除数据。
	 * @param id
	 * 删除条件
	 * @return
	 * 结果
	 */
	@RequiresPermissions({ModuleConstant.ORG_DEPT + ":" + Right.DELETE})
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public Json delete(String id){
		if(logger.isDebugEnabled()) logger.debug("删除数据［"+ id +"］...");
		Json result = new Json();
		try {
			this.deptPlanService.delete(id.split("\\|"));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("删除数据["+id+"]时发生异常:", e);
		}
		return result;
	}
	
	/**
	 * 根据计划ID加载部门计划成员数据列表。
	 * @param deptId
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.PAPERS_PAPER + ":" + Right.VIEW})
	@RequestMapping(value="/{deptId}/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public List<DeptPlanMemberInfo> deptPlanMembers(@PathVariable String deptId){
		if(logger.isDebugEnabled()) logger.debug("加载［"+deptId+"］数据列表...");
		return this.menberService.findDeptPlanMembers(deptId);
	}
	/**
	 * 获取编辑页面。
	 * @return
	 * 编辑页面。
	 */
	//@RequiresPermissions({ModuleConstant.ORG_DEPT + ":" + Right.UPDATE})
	@RequestMapping(value="/menber/edit", method = RequestMethod.GET)
	public String menberEdit(String planDetpId,Model model){
		if(logger.isDebugEnabled()) logger.debug("加载编辑页面...");
		model.addAttribute("CURRENT_PLAN_DEPT_ID", planDetpId);
		return "/plan/menber_edit";
	}
	/**
	 * 获取添加页面。
	 * @return
	 * 添加页面。
	 */
	//@RequiresPermissions({ModuleConstant.ORG_DEPT + ":" + Right.UPDATE})
	@RequestMapping(value="/add/{planDeptId}", method = RequestMethod.GET)
	public String edit(@PathVariable String planDeptId,Model model){
		if(logger.isDebugEnabled()) logger.debug("加载添加页面...");
		model.addAttribute("CURRENT_PLAN_DEPT_ID", planDeptId);
		return "/plan/dept_add";
	}
	/**
	 * 更新数据。
	 * @param info
	 * 更新源数据。
	 * @return
	 * 更新后数据。
	 */
	@RequiresPermissions({ModuleConstant.ORG_DEPT + ":" + Right.UPDATE})
	@RequestMapping(value="/updateMenber", method = RequestMethod.POST)
	@ResponseBody
	public Json updateMenber(String deptPlanId,DeptPlanMemberInfo info){
		if(logger.isDebugEnabled()) logger.debug("更新数据...");
		Json result = new Json();
		try {
			result.setData(this.deptPlanService.updateDeptPlanMember(deptPlanId, info));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("更新部门计划成员数据发生异常", e);
		}
		return result;
	}
}