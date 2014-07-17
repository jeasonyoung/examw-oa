package com.examw.oa.controllers.adm;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examw.model.DataGrid;
import com.examw.model.Json;
import com.examw.oa.domain.adm.LeaveApproval;
import com.examw.oa.model.adm.LeaveApprovalInfo;
import com.examw.oa.service.adm.ILeaveApprovalService;
/**
 * 请假审批控制器。
 * @author lq.
 * @since 2014-07-17.
 */
@Controller
@RequestMapping(value = "/adm/approval")
public class LeaveApprovalController {
	private static Logger logger = Logger.getLogger(NoticeColumnController.class);
	/**
	 *栏目信息服务。
	 */
	@Resource
	private ILeaveApprovalService approvalService;
	/**
	 * 列表页面。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.VIEW})
	@RequestMapping(value = {"","/list"}, method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("TYPE_LEADER_VALUE",LeaveApproval.TYPE_LEADER);
		model.addAttribute("TYPE_LEADER_NAME", this.approvalService.loadTypeName(LeaveApproval.TYPE_LEADER));
		
		model.addAttribute("TYPE_ADM_VALUE",LeaveApproval.TYPE_ADM);
		model.addAttribute("TYPE_ADM_NAME", this.approvalService.loadTypeName(LeaveApproval.TYPE_ADM));
		
		model.addAttribute("TYPE_BOSS_VALUE",LeaveApproval.TYPE_BOSS);
		model.addAttribute("TYPE_BOSS_NAME", this.approvalService.loadTypeName(LeaveApproval.TYPE_BOSS));
		
		model.addAttribute("STATUS_AGREE_VALUE",LeaveApproval.STATUS_AGREE);
		model.addAttribute("STATUS_AGREE_NAME", this.approvalService.loadStatusName(LeaveApproval.STATUS_AGREE));
		
		model.addAttribute("STATUS_DISAGREE_VALUE",LeaveApproval.STATUS_DISAGREE);
		model.addAttribute("STATUS_DISAGREE_NAME", this.approvalService.loadStatusName(LeaveApproval.STATUS_DISAGREE));
		return "adm/approval_list";
	}
	/**
	 * 添加页面。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.UPDATE})
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model){
		model.addAttribute("TYPE_LEADER_NAME", this.approvalService.loadTypeName(LeaveApproval.TYPE_LEADER));
		model.addAttribute("TYPE_ADM_NAME",this.approvalService.loadTypeName(LeaveApproval.TYPE_ADM));
		model.addAttribute("TYPE_BOSS_NAME", this.approvalService.loadTypeName(LeaveApproval.TYPE_BOSS));
		
		
		model.addAttribute("STATUS_AGREE_NAME", this.approvalService.loadStatusName(LeaveApproval.STATUS_AGREE));
		model.addAttribute("STATUS_DISAGREE_NAME", this.approvalService.loadStatusName(LeaveApproval.STATUS_DISAGREE));
		
		return "adm/approval_edit";
   }
	/**
	 * 查询数据。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.VIEW})
	@RequestMapping(value="/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<LeaveApprovalInfo> datagrid(LeaveApprovalInfo info){
		return this.approvalService.datagrid(info);
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
	public Json update(LeaveApprovalInfo info){
		Json result = new Json();
		try {
			result.setData(this.approvalService.update(info));
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
			this.approvalService.delete(id.split("\\|"));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("删除数据["+id+"]时发生异常:", e);
		}
		return result;
	}
}