package com.examw.oa.controllers.adm;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

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
import com.examw.oa.domain.adm.Leave;
import com.examw.oa.domain.security.Right;
import com.examw.oa.model.adm.LeaveInfo;
import com.examw.oa.service.adm.ILeaveService;
/**
 * 我要请假信息控制器。
 * @author lq.
 * @since 2014-07-15.
 */
@Controller
@RequestMapping(value = "/adm/leave")
public class LeaveController implements IUserAware {
	private static final Logger logger = Logger.getLogger(LeaveController.class);
	private String current_user_id;
	//注入请假服务接口。
	@Resource
	private ILeaveService leaveService;
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
	public void setUserName(String userName){}
	/*
	 * 设置当前用户昵称。
	 * @see com.examw.aware.IUserAware#setUserNickName(java.lang.String)
	 */
	@Override
	public void setUserNickName(String userNickName){}
	/**
	 * 列表页面。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ADM_LEAVE + ":" + Right.VIEW})
	@RequestMapping(value = {"","/list"}, method = RequestMethod.GET)
	public String list(Model model){
		if(logger.isDebugEnabled())logger.debug("加载列表页面...");
		
		model.addAttribute("PER_UPDATE", ModuleConstant.ADM_LEAVE + ":" + Right.UPDATE);
		model.addAttribute("PER_DELETE", ModuleConstant.ADM_LEAVE + ":" + Right.DELETE);
		
		model.addAttribute("RESULT_POST", Leave.RESULT_POST);
		
		return "adm/leave_list";
	}
	/**
	 * 编辑页面。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ADM_LEAVE + ":" + Right.UPDATE})
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model){
		if(logger.isDebugEnabled())logger.debug("加载编辑页面...");
		model.addAttribute("SUP_NO_VALUE", Leave.SUP_NO);
		model.addAttribute("Leave", this.leaveService.loadLeaveEmployee(this.current_user_id));
		model.addAttribute("STATUS_APPROVAL_DEPT_NAME", this.leaveService.loadStatusName(Leave.STATUS_APPROVAL_DEPT));
		model.addAttribute("STATUS_APPROVAL_HR_NAME", this.leaveService.loadStatusName(Leave.STATUS_APPROVAL_HR));
		model.addAttribute("STATUS_APPROVAL_BOSS_NAME", this.leaveService.loadStatusName(Leave.STATUS_APPROVAL_BOSS));
		
		Map<String, String> typeMap = new TreeMap<String, String>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {return new Integer(o1) - new Integer(o2);}
		});
		typeMap.put(Leave.TYPE_VACATION.toString(), this.leaveService.loadTypeName(Leave.TYPE_VACATION));
		typeMap.put(Leave.TYPE_COMPA.toString(), this.leaveService.loadTypeName(Leave.TYPE_COMPA));
		typeMap.put(Leave.TYPE_OTHER.toString(), this.leaveService.loadTypeName(Leave.TYPE_OTHER));
		typeMap.put(Leave.TYPE_SICK_PROVE.toString(), this.leaveService.loadTypeName(Leave.TYPE_SICK_PROVE));
		typeMap.put(Leave.TYPE_SICK_NONPROVE.toString(), this.leaveService.loadTypeName(Leave.TYPE_SICK_NONPROVE));
		model.addAttribute("LEAVE_TYPES", typeMap);
		
		return "adm/leave_edit";
   }
	/**
	 * 查询数据。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ADM_LEAVE + ":" + Right.VIEW})
	@RequestMapping(value="/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<LeaveInfo> datagrid(LeaveInfo info){
		if(logger.isDebugEnabled())logger.debug("加载列表数据...");
		info.setCurrentUserId(this.current_user_id);
		//info.setStatus(Leave.STATUS_APPROVAL_DEPT);
		return this.leaveService.datagrid(info);
	}
	/**
	 * 更新数据。
	 * @param info
	 * 更新源数据。
	 * @return
	 * 更新后数据。
	 */
	@RequiresPermissions({ModuleConstant.ADM_LEAVE + ":" + Right.UPDATE})
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@ResponseBody
	public Json update(LeaveInfo info){
		if(logger.isDebugEnabled())logger.debug("更新数据...");
		Json result = new Json();
		try {
			info.setCurrentUserId(this.current_user_id);
			if(info.getStartTime().getTime() - info.getEndTime().getTime() > 0){
				result.setMsg("开始时间应早于结束时间！");
				result.setSuccess(false);
				return result;
			}
			if(info.getSup() == null) info.setSup(Leave.SUP_YES);
			if(info.getSup() == Leave.SUP_YES && info.getSupTime() == null){
				result.setMsg("应输入补班时间！!");
				result.setSuccess(false);
				return result;
			}
			result.setData(this.leaveService.update(info));
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
	@RequiresPermissions({ModuleConstant.ADM_LEAVE + ":" + Right.DELETE})
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public Json delete(String id){
		if(logger.isDebugEnabled()) logger.debug("删除数据［"+ id +"］...");
		Json result = new Json();
		try {
			this.leaveService.delete(id.split("\\|"));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("删除数据["+id+"]时发生异常:", e);
		}
		return result;
	}
}