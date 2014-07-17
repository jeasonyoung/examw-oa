package com.examw.oa.controllers.adm;


import java.util.List;

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
import com.examw.oa.domain.adm.Leave;
import com.examw.oa.model.adm.LeaveInfo;
import com.examw.oa.service.adm.ILeaveService;
/**
 * 请假控制器。
 * @author lq.
 * @since 2014-07-15.
 */
@Controller
@RequestMapping(value = "/adm/leave")
public class LeaveController {
	private static Logger logger = Logger.getLogger(NoticeColumnController.class);
	/**
	 *栏目信息服务。
	 */
	@Resource
	private ILeaveService leaveService;
	/**
	 * 列表页面。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.VIEW})
	@RequestMapping(value = {"","/list"}, method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("TYPE_VACATION_VALUE",Leave.TYPE_VACATION);
		model.addAttribute("TYPE_VACATION_NAME", this.leaveService.loadTypeName(Leave.TYPE_VACATION));
		
		model.addAttribute("TYPE_COMPA_VALUE",Leave.TYPE_COMPA);
		model.addAttribute("TYPE_COMPA_NAME", this.leaveService.loadTypeName(Leave.TYPE_COMPA));
		
		model.addAttribute("TYPE_OTHER_VALUE",Leave.TYPE_OTHER);
		model.addAttribute("TYPE_OTHER_NAME", this.leaveService.loadTypeName(Leave.TYPE_OTHER));
		
		model.addAttribute("TYPE_SICK_PROVE_VALUE",Leave.TYPE_SICK_PROVE);
		model.addAttribute("TYPE_SICK_PROVE_NAME", this.leaveService.loadTypeName(Leave.TYPE_SICK_PROVE));
		
		model.addAttribute("TYPE_SICK_NONPROVE_VALUE",Leave.TYPE_SICK_NONPROVE);
		model.addAttribute("TYPE_SICK_NONPROVE_NAME", this.leaveService.loadTypeName(Leave.TYPE_SICK_NONPROVE));
		return "adm/leave_list";
	}
	/**
	 * 添加页面。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.UPDATE})
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(String deptId,String sEmpId, Model model){
		model.addAttribute("CURRENT_DEPT_ID", StringUtils.isEmpty(deptId) ? "" : deptId);
		model.addAttribute("CURRENT_SEMPL_ID", StringUtils.isEmpty(sEmpId) ? "" : sEmpId);

		
		model.addAttribute("TYPE_VACATION_NAME", this.leaveService.loadTypeName(Leave.TYPE_VACATION));
		model.addAttribute("TYPE_COMPA_NAME", this.leaveService.loadTypeName(Leave.TYPE_COMPA));
		model.addAttribute("TYPE_OTHER_NAME", this.leaveService.loadTypeName(Leave.TYPE_OTHER));
		model.addAttribute("TYPE_SICK_PROVE_NAME", this.leaveService.loadTypeName(Leave.TYPE_SICK_PROVE));
		model.addAttribute("TYPE_SICK_NONPROVE", this.leaveService.loadTypeName(Leave.TYPE_SICK_NONPROVE));
		
		return "adm/leave_edit";
   }
	/**
	 * 查询数据。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.VIEW})
	@RequestMapping(value="/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<LeaveInfo> datagrid(LeaveInfo info){
		return this.leaveService.datagrid(info);
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
	public Json update(LeaveInfo info){
		Json result = new Json();
		try {
			result.setData(this.leaveService.update(info));
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
			this.leaveService.delete(id.split("\\|"));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("删除数据["+id+"]时发生异常:", e);
		}
		return result;
	}
	/**
	 * 员工数据。
	 * @return
	 */
	@RequestMapping(value="/all", method = RequestMethod.POST)
	@ResponseBody
	public List<LeaveInfo> all(){
		return this.leaveService.datagrid(new LeaveInfo(){
			private static final long serialVersionUID = 1L;
			@Override
			public String getSort(){return "createTime";}
			@Override
			public String getOrder(){return "asc";}
		}).getRows();
	}
}