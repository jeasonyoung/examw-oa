package com.examw.oa.controllers.org;

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
import com.examw.oa.domain.org.Employee;
import com.examw.oa.domain.security.Right;
import com.examw.oa.model.org.EmployeeInfo; 
import com.examw.oa.service.org.IEmployeeService; 
/**
 * 员工信息控制器。
 * @author lq.
 * @since 2014-06-16.
 */
@Controller
@RequestMapping(value = "/org/emp")
public class EmployeeController {
	private static final Logger logger = Logger.getLogger(EmployeeController.class);
	//员工服务接口。
	@Resource
	private IEmployeeService employeeService;
	/**
	 * 列表页面。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ORG_EMPLOYEE + ":" + Right.VIEW})
	@RequestMapping(value = {"","/list"}, method = RequestMethod.GET)
	public String list(Model model){
		if(logger.isDebugEnabled())logger.debug("加载列表页面...");
		model.addAttribute("PER_UPDATE", ModuleConstant.ORG_EMPLOYEE + ":" + Right.UPDATE);
		model.addAttribute("PER_DELETE", ModuleConstant.ORG_EMPLOYEE + ":" + Right.DELETE);
		
		return "org/emp_list";
	}
	/**
	 * 编辑页面。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ORG_EMPLOYEE + ":" + Right.UPDATE})
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(String deptId,String postId, Model model){
		if(logger.isDebugEnabled()) logger.debug("加载编辑页面...");
		model.addAttribute("CURRENT_DEPT_ID", StringUtils.isEmpty(deptId) ? "" : deptId);
		model.addAttribute("CURRENT_POST_ID", StringUtils.isEmpty(postId) ? "" : postId);

		model.addAttribute("GENDER_MALE_VALUE", Employee.GENDER_MALE);
		model.addAttribute("GENDER_MALE_NAME", this.employeeService.loadGenderName(Employee.GENDER_MALE));
		model.addAttribute("GENDER_FEMALE_VALUE", Employee.GENDER_FEMALE);
		model.addAttribute("GENDER_FEMALE_NAME", this.employeeService.loadGenderName(Employee.GENDER_FEMALE));
		
		model.addAttribute("STATUS_OUT_VALUE", Employee.STATUS_OUT);
		model.addAttribute("STATUS_OUT_NAME", this.employeeService.loadStatusName(Employee.STATUS_OUT));
		model.addAttribute("STATUS_ON_VALUE", Employee.STATUS_ON);
		model.addAttribute("STATUS_ON_NAME", this.employeeService.loadStatusName(Employee.STATUS_ON));
		model.addAttribute("STATUS_LIMIT_VALUE", Employee.STATUS_LIMIT);
		model.addAttribute("STATUS_LIMIT_NAME", this.employeeService.loadStatusName(Employee.STATUS_LIMIT));
		
		return "org/emp_edit";
   }
	/**
	 * 查询数据。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ORG_EMPLOYEE + ":" + Right.VIEW})
	@RequestMapping(value="/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<EmployeeInfo> datagrid(EmployeeInfo info){
		if(logger.isDebugEnabled()) logger.debug("加载列表数据...");
		return this.employeeService.datagrid(info);
	}
	/**
	 * 更新数据。
	 * @param info
	 * 更新源数据。
	 * @return
	 * 更新后数据。
	 */
	@RequiresPermissions({ModuleConstant.ORG_EMPLOYEE + ":" + Right.UPDATE})
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@ResponseBody
	public Json update(EmployeeInfo info){
		if(logger.isDebugEnabled()) logger.debug("更新数据...");
		Json result = new Json();
		try {
			result.setData(this.employeeService.update(info));
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
	@RequiresPermissions({ModuleConstant.ORG_EMPLOYEE + ":" + Right.DELETE})
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public Json delete(String id){
		if(logger.isDebugEnabled()) logger.debug("删除数据［"+ id +"］...");
		Json result = new Json();
		try {
			this.employeeService.delete(id.split("\\|"));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("删除数据["+id+"]时发生异常:", e);
		}
		return result;
	}
//	/**
//	 * 员工数据。
//	 * @return
//	 */
//	@RequestMapping(value="/alls", method = RequestMethod.POST)
//	@ResponseBody
//	public List<EmployeeInfo> alls(){
//		return this.employeeService.datagrid(new EmployeeInfo(){
//			private static final long serialVersionUID = 1L;
//			@Override
//			public String getSort(){return "name";}
//			@Override
//			public String getOrder(){return "asc";}
//		}).getRows();
//	}
//	/**
//	 * 返回部门下的所有员工
//	 * @return
//	 */
//	@RequestMapping(value={"/all"}, method = RequestMethod.POST)
//	@ResponseBody
//	public List<EmployeeInfo> all(String deptId){
//		 return this.employeeService.loadEmployee(deptId);
//	}
}