package com.examw.oa.controllers.org;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examw.model.DataGrid;
import com.examw.model.Json;
import com.examw.oa.controllers.security.LogController;
import com.examw.oa.domain.security.Right;
import com.examw.oa.model.org.RankInfo;
import com.examw.oa.service.org.IRankService;

/**
 * 等级信息控制器。
 * @author lq.
 * @since 2014-06-11.
 */
@Controller
@RequestMapping(value = "/org/rank")
public class RankController {
	private static final Logger logger = Logger.getLogger(LogController.class);
	//等级信息服务。
	@Resource
	private IRankService rankservice;
	/**
	 * 获取列表页面。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ORG_RANK + ":" + Right.UPDATE})
	@RequestMapping(value={"","/list"}, method = RequestMethod.GET)
	public String list(Model model){
		if(logger.isDebugEnabled()) logger.debug("加载列表页面...");
		model.addAttribute("PER_UPDATE", ModuleConstant.ORG_RANK + ":" + Right.UPDATE);
		model.addAttribute("PER_DELETE", ModuleConstant.ORG_RANK + ":" + Right.DELETE);
		return "org/rank_list";
	}
	/**
	 * 获取编辑页面。
	 * @return
	 * 编辑页面。
	 */
	@RequiresPermissions({ModuleConstant.ORG_RANK + ":" + Right.UPDATE})
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String edit(){
		if(logger.isDebugEnabled()) logger.debug("加载编辑页面...");
		return "org/rank_edit";
	}
	/**
	 * 查询数据。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ORG_RANK + ":" + Right.VIEW})
	@RequestMapping(value="/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<RankInfo> datagrid(RankInfo info){
		if(logger.isDebugEnabled()) logger.debug("加载列表数据...");
		return this.rankservice.datagrid(info);
	}
	/**
	 * 更新数据。
	 * @param info
	 * 更新源数据。
	 * @return
	 * 更新后数据。
	 */
	@RequiresPermissions({ModuleConstant.ORG_RANK + ":" + Right.UPDATE})
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@ResponseBody
	public Json update(RankInfo info){
		if(logger.isDebugEnabled()) logger.debug("更新数据...");
		Json result = new Json();
		try {
			result.setData(this.rankservice.update(info));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("更新等级数据发生异常", e);
		}
		return result;
	}
	/**
	 * 岗位级别数据。
	 * @return
	 */
	@RequestMapping(value="/all", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<RankInfo> all(){
		return this.rankservice.datagrid(new RankInfo(){
			private static final long serialVersionUID = 1L;
			@Override
			public String getSort(){return "code";}
			@Override
			public String getOrder(){return "asc";}
		}).getRows();
	}
	/**
	 * 删除数据。
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public Json delete(String id){
		if(logger.isDebugEnabled()) logger.debug("删除数据［"+ id +"］..");
		Json result = new Json();
		try {
			this.rankservice.delete(id.split("\\|"));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("删除数据["+id+"]时发生异常:", e);
		}
		return result;
	}
}