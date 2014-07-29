package com.examw.oa.controllers.adm;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examw.aware.IUserAware;
import com.examw.model.DataGrid;
import com.examw.model.Json;
import com.examw.oa.domain.adm.Leave;
import com.examw.oa.domain.security.Right;
import com.examw.oa.model.adm.LeaveInfo;
import com.examw.oa.service.adm.ILeaveApprovalService;
import com.examw.oa.service.adm.ILeaveService;

/**
 * 请假条审批控制器。
 * @author yangyong.
 * @since 2014-07-29.
 */
@Controller
@RequestMapping(value = "/adm/leave/approval")
public class LeaveApprovalController implements IUserAware {
	private static final Logger logger = Logger.getLogger(LeaveApprovalController.class);
	private String current_user_id;
	//注入请假条审批服务接口。
	@Resource
	private ILeaveApprovalService leaveApprovalService;
	//注入请假条服务接口。
	@Resource
	private ILeaveService leaveService;
	/*
	 * 设置当前用户ID。
	 * @see com.examw.aware.IUserAware#setUserId(java.lang.String)
	 */
	@Override
	public void setUserId(String userId) {
		if(logger.isDebugEnabled()) logger.debug("注入当前用户［userId="+ userId +"］...");
		this.current_user_id = userId;
	}
	/*
	 * 设置当前用户姓名。
	 * @see com.examw.aware.IUserAware#setUserName(java.lang.String)
	 */
	@Override
	public void setUserName(String userName) {}
	/*
	 * 设置当前用户昵称。
	 * @see com.examw.aware.IUserAware#setUserNickName(java.lang.String)
	 */
	@Override
	public void setUserNickName(String userNickName){}
	/**
	 * 加载请假条视图页面。
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/view/{id}"}, method = RequestMethod.GET)
	public String view(@PathVariable String id, Model model){
		model.addAttribute("STATUS_APPROVAL_DEPT_NAME", this.leaveService.loadStatusName(Leave.STATUS_APPROVAL_DEPT));
		model.addAttribute("STATUS_APPROVAL_HR_NAME", this.leaveService.loadStatusName(Leave.STATUS_APPROVAL_HR));
		model.addAttribute("STATUS_APPROVAL_BOSS_NAME", this.leaveService.loadStatusName(Leave.STATUS_APPROVAL_BOSS));
		
		model.addAttribute("Leave",this.leaveApprovalService.loadLeave(id));
		return "adm/leave_approval_view";
	}
	/**
	 * 加载请假审批（部门）列表页面。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ADM_LEAVE_DEPT_APPROVAL + ":" + Right.VIEW})
	@RequestMapping(value = {"/dept","/dept/list"}, method = RequestMethod.GET)
	public String deptList(Model model){
		if(logger.isDebugEnabled()) logger.debug("加载请假审批（部门）列表页面...");
		
		model.addAttribute("PER_UPDATE", ModuleConstant.ADM_LEAVE_DEPT_APPROVAL + ":" + Right.UPDATE);
		model.addAttribute("PER_DELETE", ModuleConstant.ADM_LEAVE_DEPT_APPROVAL + ":" + Right.DELETE);
		
		model.addAttribute("CURRENT_STATUS_VALUE", Leave.STATUS_APPROVAL_DEPT);
		model.addAttribute("CURRENT_RESULT_VALUE", Leave.RESULT_POST);
		
		return "adm/leave_approval_dept_list";
	}
	/**
	 * 加载请假审批（部门）列表页面数据。
	 * @param info
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ADM_LEAVE_DEPT_APPROVAL + ":" + Right.VIEW})
	@RequestMapping(value = {"/dept/datagrid"}, method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<LeaveInfo> deptListDataGrid(LeaveInfo info){
		if(logger.isDebugEnabled()) logger.debug("加载请假审批（部门）列表页面数据...");
		info.setDeptId(this.leaveApprovalService.loadDeptIdOfEmployee(this.current_user_id));
		return this.leaveApprovalService.datagrid(info);
	}
	/**
	 * 加载请假审批（部门）审批页面。
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ADM_LEAVE_DEPT_APPROVAL + ":" + Right.UPDATE})
	@RequestMapping(value = {"/dept/edit"}, method = RequestMethod.GET)
	public String deptEdit(String id, Model model){
		if(logger.isDebugEnabled()) logger.debug("加载请假审批（部门）审批页面［id="+ id +"］...");
		return this.loadApprovalEdit(id, model);
	}
	//加载审批页面。
	private String loadApprovalEdit(String id, Model model){
		model.addAttribute("CURRENT_LEAVE_ID", StringUtils.isEmpty(id) ? "" : id);
		
		model.addAttribute("RESULT_AGREE_VALUE", Leave.RESULT_AGREE);
		model.addAttribute("RESULT_AGREE_NAME",  this.leaveService.loadResultName(Leave.RESULT_AGREE));
		model.addAttribute("RESULT_DISAGREE_VALUE", Leave.RESULT_DISAGREE);
		model.addAttribute("RESULT_DISAGREE_NAME", this.leaveService.loadResultName(Leave.RESULT_DISAGREE));
		
		return "adm/leave_approval_edit";
	}
	/**
	 * 加载请假审批（部门）审批。
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ADM_LEAVE_DEPT_APPROVAL + ":" + Right.UPDATE})
	@RequestMapping(value = {"/dept/update"}, method = RequestMethod.POST)
	@ResponseBody
	public Json deptApproval(LeaveInfo info){
		if(logger.isDebugEnabled())logger.debug("请假审批（部门）...");
		return this.updateApproval(info, Leave.STATUS_APPROVAL_DEPT);
	}
	//请假条审批处理。
	private Json updateApproval(LeaveInfo info, Integer status){
		Json result = new Json();
		try {
			info.setCurrentUserId(this.current_user_id);
			info.setStatus(status);
			result.setData(this.leaveApprovalService.update(info));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("更新数据发生异常", e);
		}
		return result;
	}
	
	/**
	 * 加载请假审批（HR）列表页面。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ADM_LEAVE_HR_APPROVAL + ":" + Right.VIEW})
	@RequestMapping(value = {"/hr","/hr/list"}, method = RequestMethod.GET)
	public String hrList(Model model){
		if(logger.isDebugEnabled()) logger.debug("加载请假审批（HR）列表页面...");
		
		model.addAttribute("PER_UPDATE", ModuleConstant.ADM_LEAVE_HR_APPROVAL + ":" + Right.UPDATE);
		model.addAttribute("PER_DELETE", ModuleConstant.ADM_LEAVE_HR_APPROVAL + ":" + Right.DELETE);
		
		model.addAttribute("CURRENT_STATUS_VALUE", Leave.STATUS_APPROVAL_HR);
		model.addAttribute("CURRENT_RESULT_VALUE", Leave.RESULT_APPROVAL);
		
		return "adm/leave_approval_hr_list";
	}
	/**
	 * 加载请假审批（HR）列表页面数据。
	 * @param info
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ADM_LEAVE_HR_APPROVAL + ":" + Right.VIEW})
	@RequestMapping(value = {"/hr/datagrid"}, method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<LeaveInfo> hrListDataGrid(LeaveInfo info){
		if(logger.isDebugEnabled()) logger.debug("加载请假审批（HR）列表页面数据...");
		return this.leaveApprovalService.datagrid(info);
	}
	/**
	 * 加载请假审批（HR）审批页面。
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ADM_LEAVE_HR_APPROVAL + ":" + Right.UPDATE})
	@RequestMapping(value = {"/hr/edit"}, method = RequestMethod.GET)
	public String hrEdit(String id, Model model){
		if(logger.isDebugEnabled()) logger.debug("加载请假审批（HR）审批页面［id="+ id +"］...");
		return this.loadApprovalEdit(id, model);
	}
	/**
	 * 加载请假审批（HR）审批。
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ADM_LEAVE_HR_APPROVAL + ":" + Right.UPDATE})
	@RequestMapping(value = {"/hr/update"}, method = RequestMethod.POST)
	@ResponseBody
	public Json hrApproval(LeaveInfo info){
		if(logger.isDebugEnabled())logger.debug("请假审批（HR）...");
		return this.updateApproval(info, Leave.STATUS_APPROVAL_HR);
	}
	
	/**
	 * 加载请假审批（总经理）列表页面。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ADM_LEAVE_BOSS_APPROVAL + ":" + Right.VIEW})
	@RequestMapping(value = {"/boss","/boss/list"}, method = RequestMethod.GET)
	public String bossList(Model model){
		if(logger.isDebugEnabled()) logger.debug("加载请假审批（总经理）列表页面...");
		
		model.addAttribute("PER_UPDATE", ModuleConstant.ADM_LEAVE_BOSS_APPROVAL + ":" + Right.UPDATE);
		model.addAttribute("PER_DELETE", ModuleConstant.ADM_LEAVE_BOSS_APPROVAL + ":" + Right.DELETE);
		
		model.addAttribute("CURRENT_STATUS_VALUE", Leave.STATUS_APPROVAL_BOSS);
		model.addAttribute("CURRENT_RESULT_VALUE", Leave.RESULT_APPROVAL);
		
		return "adm/leave_approval_boss_list";
	}
	/**
	 * 加载请假审批（总经理）列表页面数据。
	 * @param info
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ADM_LEAVE_BOSS_APPROVAL + ":" + Right.VIEW})
	@RequestMapping(value = {"/boss/datagrid"}, method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<LeaveInfo> bossListDataGrid(LeaveInfo info){
		if(logger.isDebugEnabled()) logger.debug("加载请假审批（总经理）列表页面数据...");
		return this.leaveApprovalService.datagrid(info);
	}
	/**
	 * 加载请假审批（总经理）审批页面。
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ADM_LEAVE_HR_APPROVAL + ":" + Right.UPDATE})
	@RequestMapping(value = {"/boss/edit"}, method = RequestMethod.GET)
	public String bossEdit(String id, Model model){
		if(logger.isDebugEnabled()) logger.debug("加载请假审批（总经理）审批页面［id="+ id +"］...");
		return this.loadApprovalEdit(id, model);
	}
	/**
	 * 加载请假审批（总经理）审批。
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ADM_LEAVE_BOSS_APPROVAL + ":" + Right.UPDATE})
	@RequestMapping(value = {"/boss/update"}, method = RequestMethod.POST)
	@ResponseBody
	public Json bossApproval(LeaveInfo info){
		if(logger.isDebugEnabled())logger.debug("请假审批（总经理）...");
		return this.updateApproval(info, Leave.STATUS_APPROVAL_BOSS);
	}
}