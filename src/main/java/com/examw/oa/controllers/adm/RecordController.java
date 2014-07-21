package com.examw.oa.controllers.adm;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examw.model.DataGrid;
import com.examw.oa.domain.adm.LeaveApproval;
import com.examw.oa.model.adm.LeaveApprovalInfo;
import com.examw.oa.service.adm.ILeaveApprovalService;
/**
 * 请假审记录批控制器。
 * @author lq.
 * @since 2014-07-21.
 */
@Controller
@RequestMapping(value = "/adm/record")
public class RecordController {
	//private static Logger logger = Logger.getLogger(RecordController.class);
	/**
	 *请假审批服务。
	 */
	@Resource
	private ILeaveApprovalService approvalService;
	/**
	 * 获取列表页面。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_ROLE + ":" + Right.VIEW})
	@RequestMapping(value={"","/list"}, method = RequestMethod.GET)
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
		
		model.addAttribute("typeMap",this.approvalService.getTypeMap());
		model.addAttribute("statusMap",this.approvalService.getStatusMap());
		
		return "adm/record_list";
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
	 * 添加页面。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.UPDATE})
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit( Model model){

		model.addAttribute("TYPE_ADM_NAME", this.approvalService.loadTypeName(LeaveApproval.TYPE_ADM));
		model.addAttribute("TYPE_LEADER_NAME", this.approvalService.loadTypeName(LeaveApproval.TYPE_LEADER));
		model.addAttribute("TYPE_BOSS_NAME", this.approvalService.loadTypeName(LeaveApproval.TYPE_BOSS));
		
		model.addAttribute("STATUS_AGREE_NAME", this.approvalService.loadStatusName(LeaveApproval.STATUS_AGREE));
		model.addAttribute("STATUS_DISAGREE_NAME", this.approvalService.loadStatusName(LeaveApproval.STATUS_DISAGREE));
		return "adm/record_edit";
   }
}
