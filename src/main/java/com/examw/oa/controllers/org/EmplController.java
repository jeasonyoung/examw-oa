package com.examw.oa.controllers.org;

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
import com.examw.oa.controllers.security.MenuRightController;
import com.examw.oa.model.org.DepartInfo;
import com.examw.oa.model.org.EmplInfo;
import com.examw.oa.service.org.IDepartService;
import com.examw.oa.service.org.IEmplService;
import com.examw.oa.service.org.IPostService;
import com.examw.oa.service.org.IRankService;

/**
 * 员工信息控制器。
 * @author lq.
 * @since 2014-06-16.
 */
@Controller
@RequestMapping(value = "/org/empl")
public class EmplController {
	private static Logger logger = Logger.getLogger(MenuRightController.class);
	@Resource
	private IDepartService departservice;
	/**
	 *岗位信息服务。
	 */
	@Resource
	private IPostService postservice;
	/**
	 * 等级信息服务。
	 */
	@Resource
	private IRankService rankservice;
	/**
	 * 员工信息服务。
	 */
	@Resource
	private IEmplService emplservice;
	/**
	 * 列表页面。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.VIEW})
	@RequestMapping(value = {"","/list"}, method = RequestMethod.GET)
	public String list(Model model){
		return "org/empl_list";
	}
	/**
	 * 添加页面。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.UPDATE})
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(String departId,String postId,String ignore,Model model){
		 if(!StringUtils.isEmpty(postId)){
				DepartInfo info = this.postservice.loadDept(postId);
				if(info != null) departId = info.getId();
			}
		 	model.addAttribute("CURRENT_DEPT_ID", StringUtils.isEmpty(departId) ? "" : departId);
		 	model.addAttribute("CURRENT_IGNORE", StringUtils.isEmpty(ignore) ? "" : ignore);
		 	model.addAttribute("CURRENT_POST_ID", StringUtils.isEmpty(postId) ? "" : postId);
		 return "org/empl_edit";
   }
	/**
	 * 查询数据。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.VIEW})
	@RequestMapping(value="/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<EmplInfo> datagrid(EmplInfo info){
		return this.emplservice.datagrid(info);
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
	public Json update(EmplInfo info){
		Json result = new Json();
		try {
			
			result.setData(this.emplservice.update(info));
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
			this.emplservice.delete(id.split("\\|"));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("删除数据["+id+"]时发生异常:", e);
		}
		return result;
	}
}