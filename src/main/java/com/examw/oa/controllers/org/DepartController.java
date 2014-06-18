package com.examw.oa.controllers.org;

import java.util.ArrayList;
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
import com.examw.model.TreeNode;
import com.examw.oa.controllers.security.MenuController;
import com.examw.oa.model.org.DepartInfo;
import com.examw.oa.service.org.IDepartService;

/**
 * 部门信息控制器。
 * @author lq.
 * @since 2014-06-11.
 */
@Controller
@RequestMapping(value = "/org/depart")
public class DepartController {
	private static Logger logger = Logger.getLogger(MenuController.class);
	@Resource
	private IDepartService departservice;
	/**
	 * 获取列表页面。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_ROLE + ":" + Right.VIEW})
	@RequestMapping(value={"","/list"}, method = RequestMethod.GET)
	public String list(Model model){
		//model.addAttribute("PER_UPDATE", ModuleConstant.SECURITY_ROLE + ":" + Right.UPDATE);
		//model.addAttribute("PER_DELETE", ModuleConstant.SECURITY_ROLE + ":" + Right.DELETE);
		return "org/depart_list";
	}
	
	/**
	 * 获取编辑页面。
	 * @return
	 * 编辑页面。
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_ROLE + ":" + Right.UPDATE})
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String edit(String Ids,Model model){
		model.addAttribute("Ids", StringUtils.isEmpty(Ids) ? "" : Ids);
		model.addAttribute("departs", this.departservice.datagrid(new DepartInfo(){
			private static final long serialVersionUID = 1L;
			@Override
			public Integer getPage(){return null;}
			@Override
			public Integer getRows(){return null;}
		}).getRows());
		return "org/depart_edit";
	}
	/**
	 * 查询数据。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_ROLE + ":" + Right.VIEW})
	@RequestMapping(value="/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<DepartInfo> datagrid(DepartInfo info){
		return this.departservice.datagrid(info);
	}
	/**
	 * 更新数据。
	 * @param info
	 * 更新源数据。
	 * @return
	 * 更新后数据。
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_ROLE + ":" + Right.UPDATE})
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@ResponseBody
	public Json update(DepartInfo info){
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
	//@RequiresPermissions({ModuleConstant.SECURITY_ROLE + ":" + Right.DELETE})
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public Json delete(String id){
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
	 * 菜单树结构数据。
	 * @return
	 */
	@RequestMapping(value = "/tree", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public synchronized List<TreeNode> tree(){
		List<TreeNode> result = new ArrayList<>();
		List<DepartInfo> list = this.departservice.loadDeparts();
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				TreeNode tv = this.createTreeNode(list.get(i));
				if(tv != null){
					result.add(tv);
				}
			}
		}
		return result;
	}
	private TreeNode createTreeNode(DepartInfo info){
		if(info == null) return null;
		TreeNode tv = new TreeNode();
		tv.setId(info.getId());
		tv.setText(info.getName());
		if(info.getChildren() != null && info.getChildren().size() > 0){
			List<TreeNode> childs = new ArrayList<>();
			 for(DepartInfo m : info.getChildren()){
				  TreeNode node = this.createTreeNode(m);
				  if(node != null) childs.add(node);
			 }
			 tv.setChildren(childs);
		}
		return tv;
	}
}