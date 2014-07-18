package com.examw.oa.controllers.org;

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
import com.examw.oa.model.org.PostInfo;
import com.examw.oa.service.org.IPostService;
/**
 * 岗位信息控制器。
 * @author lq.
 * @since 2014-06-13.
 */
@Controller
@RequestMapping(value = "/org/post")
public class PostController {
	private static Logger logger = Logger.getLogger(PostController.class);
	/**
	 * 岗位信息服务。
	 */
	@Resource
	private IPostService postService;
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
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(String deptId, Model model){
		model.addAttribute("CURRENT_DEPT_ID", StringUtils.isEmpty(deptId) ? "" : deptId);
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
		return this.postService.datagrid(info);
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
			result.setData(this.postService.update(info));
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
			this.postService.delete(id.split("\\|"));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("删除数据["+id+"]时发生异常:", e);
		}
		return result;
	}
	/**
	 * 返回部门下的所有岗位
	 * @return
	 */
	@RequestMapping(value={"/all"}, method = RequestMethod.POST)
	@ResponseBody
	public List<PostInfo> all(String deptId){
		 return this.postService.loadPosts(deptId);
	}
	/**
	 * 返回部门下的所有员工
	 * @return
	 */
	@RequestMapping(value={"/alls"}, method = RequestMethod.POST)
	@ResponseBody
	public List<PostInfo> alls(String emplId){
		 return this.postService.loadPost(emplId);
	}
}