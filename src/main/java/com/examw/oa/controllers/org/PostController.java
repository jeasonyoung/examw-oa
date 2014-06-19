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
import com.examw.oa.controllers.security.MenuController;
import com.examw.oa.model.org.PostInfo;
import com.examw.oa.service.org.IDepartService;
import com.examw.oa.service.org.IPostService;
/**
 * 岗位信息控制器。
 * @author lq.
 * @since 2014-06-13.
 */
@Controller
@RequestMapping(value = "/org/post")
public class PostController {
	private static Logger logger = Logger.getLogger(MenuController.class);
	/**
	 * 岗位。
	 */
	@Resource
	private IPostService postservice;
	/**
	 * 部门。
	 */
	@Resource
	private IDepartService departservice;
	/**
	 * 列表页面。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.VIEW})
	@RequestMapping(value = {"","/list"}, method = RequestMethod.GET)
	public String list(Model model){
		//model.addAttribute("PER_UPDATE", ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.UPDATE);
		//model.addAttribute("PER_DELETE", ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.DELETE);
		return "org/post_list";
	}
	/**
	 * 添加页面。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.UPDATE})
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String edit(String ignore,String deptId, Model model){
		model.addAttribute("CURRENT_DEPT_ID", StringUtils.isEmpty(deptId) ? "" : deptId);
		model.addAttribute("CURRENT_IGNORE", StringUtils.isEmpty(ignore) ? "" : ignore);
		return "org/post_edit";
	}
	/**
	 * 查询数据。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.VIEW})
	@RequestMapping(value="/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<PostInfo> datagrid(PostInfo info){
		return this.postservice.datagrid(info);
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
	public Json update(PostInfo info){
		Json result = new Json();
		try {
			if(StringUtils.isEmpty(info.getDeptId())){
				result.setSuccess(false);
				result.setMsg("未获取部门ID数据！");
				return result;
			}
			result.setData(this.postservice.update(info));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("更新岗位数据发生异常", e);
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
			this.postservice.delete(id.split("\\|"));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("删除数据["+id+"]时发生异常:", e);
		}
		return result;
	}
}