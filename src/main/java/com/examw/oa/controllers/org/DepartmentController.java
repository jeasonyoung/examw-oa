package com.examw.oa.controllers.org;

import java.util.List;

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
import com.examw.model.TreeNode;
import com.examw.oa.controllers.security.MenuController;
import com.examw.oa.domain.security.Right;
import com.examw.oa.model.org.DepartmentInfo;
import com.examw.oa.service.org.IDepartmentService;
/**
 * 部门信息控制器。
 * @author lq.
 * @since 2014-06-11.
 */
@Controller
@RequestMapping(value = "/org/dept")
public class DepartmentController {
	private static final Logger logger = Logger.getLogger(MenuController.class);
	//部门信息服务。
	@Resource
	private IDepartmentService departservice;
	/**
	 * 获取列表页面。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ORG_DEPT + ":" + Right.VIEW})
	@RequestMapping(value={"","/list"}, method = RequestMethod.GET)
	public String list(Model model){
		if(logger.isDebugEnabled()) logger.debug("加载列表页面...");
		model.addAttribute("PER_UPDATE", ModuleConstant.ORG_DEPT + ":" + Right.UPDATE);
		model.addAttribute("PER_DELETE", ModuleConstant.ORG_DEPT + ":" + Right.DELETE);
		return "org/dept_list";
	}
	/**
	 * 获取编辑页面。
	 * @return
	 * 编辑页面。
	 */
	@RequiresPermissions({ModuleConstant.ORG_DEPT + ":" + Right.UPDATE})
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String edit(String pid,String ignore,Model model){
		if(logger.isDebugEnabled()) logger.debug("加载编辑页面...");
		model.addAttribute("CURRENT_PARENT_DEPT_ID", pid);
		model.addAttribute("CURRENT_IGNORE", StringUtils.isEmpty(ignore) ? "" : ignore);
		return "org/dept_edit";
	}
	/**
	 * 查询数据。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ORG_DEPT + ":" + Right.VIEW})
	@RequestMapping(value="/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<DepartmentInfo> datagrid(DepartmentInfo info){
		if(logger.isDebugEnabled()) logger.debug("加载列表数据...");
		return this.departservice.datagrid(info);
	}
	/**
	 * 更新数据。
	 * @param info
	 * 更新源数据。
	 * @return
	 * 更新后数据。
	 */
	@RequiresPermissions({ModuleConstant.ORG_DEPT + ":" + Right.UPDATE})
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@ResponseBody
	public Json update(DepartmentInfo info){
		if(logger.isDebugEnabled()) logger.debug("更新数据...");
		Json result = new Json();
		try {
			result.setData(this.departservice.update(info));
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
	@RequiresPermissions({ModuleConstant.ORG_DEPT + ":" + Right.DELETE})
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public Json delete(String id){
		if(logger.isDebugEnabled()) logger.debug("删除数据［"+ id +"］...");
		Json result = new Json();
		try {
			this.departservice.delete(id.split("\\|"));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("删除数据["+id+"]时发生异常:", e);
		}
		return result;
	}
	/**
	 * 部门树结构数据。
	 * @return
	 */
	@RequestMapping(value = "/tree", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<TreeNode> tree(String ignore){
		if(logger.isDebugEnabled()) logger.debug("加载部门树结构数据［ignore="+ ignore+"］...");
		return this.departservice.loadDepartments(ignore);
	}
//	/**
//	 * 加载全部部门数据。
//	 * @return
//	 */
//	@RequestMapping(value="/all", method = RequestMethod.POST)
//	@ResponseBody
//	public List<DepartmentInfo> all(){
//		if(logger.isDebugEnabled()) logger.debug("加载全部部门数据...");
//		return this.departservice.datagrid(new DepartmentInfo(){
//			private static final long serialVersionUID = 1L;
//			@Override
//			public String getSort(){return "orderNo";}
//			@Override
//			public String getOrder(){return "asc";}
//		}).getRows();
//	}
}