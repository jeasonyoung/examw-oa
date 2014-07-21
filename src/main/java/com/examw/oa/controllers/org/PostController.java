package com.examw.oa.controllers.org;

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
import com.examw.model.TreeNode;
import com.examw.oa.domain.security.Right;
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
	private static final Logger logger = Logger.getLogger(PostController.class);
	//岗位信息服务。
	@Resource
	private IPostService postService;
	/**
	 * 列表页面。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ORG_POST + ":" + Right.VIEW})
	@RequestMapping(value = {"","/list"}, method = RequestMethod.GET)
	public String list(Model model){
		if(logger.isDebugEnabled()) logger.debug("加载列表页面...");
		model.addAttribute("PER_UPDATE", ModuleConstant.ORG_POST + ":" + Right.UPDATE);
		model.addAttribute("PER_DELETE", ModuleConstant.ORG_POST + ":" + Right.DELETE);
		return "org/post_list";
	}
	/**
	 * 编辑页面。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ORG_POST + ":" + Right.UPDATE})
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(String deptId,String postId, Model model){
		if(logger.isDebugEnabled()) logger.debug("加载编辑页面...");
		model.addAttribute("CURRENT_DEPT_ID", deptId);
		model.addAttribute("CURRENT_POST_ID", postId);
		return "org/post_edit";
	}
	/**
	 * 查询数据。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ORG_POST + ":" + Right.VIEW})
	@RequestMapping(value="/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<PostInfo> datagrid(PostInfo info){
		if(logger.isDebugEnabled()) logger.debug("加载列表数据...");
		return this.postService.datagrid(info);
	}
	/**
	 * 更新数据。
	 * @param info
	 * 更新源数据。
	 * @return
	 * 更新后数据。
	 */
	@RequiresPermissions({ModuleConstant.ORG_POST + ":" + Right.UPDATE})
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@ResponseBody
	public Json update(PostInfo info){
		if(logger.isDebugEnabled()) logger.debug("更新数据...");
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
	@RequiresPermissions({ModuleConstant.ORG_POST + ":" + Right.DELETE})
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public Json delete(String id){
		if(logger.isDebugEnabled()) logger.debug("删除数据［"+ id +"］...");
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
	 * 加载部门下的岗位树.
	 * @param deptId
	 * @param ignore
	 * @return
	 */
	@RequestMapping(value="/tree/{deptId}", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<TreeNode> tree(@PathVariable String deptId,String ignore){
		if(logger.isDebugEnabled()) logger.debug("加载部门［"+deptId+"］下的岗位［ignore="+ignore+"］树...");
		return this.postService.loadPosts(deptId, ignore);
	}
}