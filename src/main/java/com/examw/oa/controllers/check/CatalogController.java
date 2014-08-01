package com.examw.oa.controllers.check;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examw.model.DataGrid;
import com.examw.model.Json;
import com.examw.oa.model.check.CatalogInfo;
import com.examw.oa.service.check.ICatalogService;
/**
 * 奖惩类别设置信息控制器。
 * @author lq.
 * @since 2014-07-30.
 */
@Controller
@RequestMapping(value = "/check/catalog")
public class CatalogController {
	private static final Logger logger = Logger.getLogger(CatalogController.class);
	//奖惩类别设置服务。
	@Resource
	private ICatalogService catalogService;
	/**
	 * 列表页面。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.ADM_LEAVE + ":" + Right.VIEW})
	@RequestMapping(value = {"","/list"}, method = RequestMethod.GET)
	public String list(Model model){
		if(logger.isDebugEnabled())logger.debug("加载列表页面...");
		return "check/catalog_list";
	}
	/**
	 * 编辑页面。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.ADM_LEAVE + ":" + Right.UPDATE})
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model){
		if(logger.isDebugEnabled())logger.debug("加载编辑页面...");
		
		return "check/catalog_edit";
	}
	/**
	 * 加载最大的代码。
	 * @return
	 */
	@RequestMapping(value="/code", method=RequestMethod.GET)
	@ResponseBody
	public String[] loadMaxCode(){
		Integer max = this.catalogService.loadMaxCode();
		if(max == null) max = 0;
		return new String[]{ String.format("%02d", max+1)};
	}
	/**
	 * 查询数据。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.ORG_POST + ":" + Right.VIEW})
	@RequestMapping(value="/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<CatalogInfo> datagrid(CatalogInfo info){
		if(logger.isDebugEnabled()) logger.debug("加载列表数据...");
		return this.catalogService.datagrid(info);
	}
	/**
	 * 更新数据。
	 * @param info
	 * 更新源数据。
	 * @return
	 * 更新后数据。
	 */
	//@RequiresPermissions({ModuleConstant.ORG_POST + ":" + Right.UPDATE})
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@ResponseBody
	public Json update(CatalogInfo info){
		if(logger.isDebugEnabled()) logger.debug("更新数据...");
		Json result = new Json();
		try {
			result.setData(this.catalogService.update(info));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("更新奖惩类别数据发生异常", e);
		}
		return result;
	}
	/**
	 * 删除数据。
	 * @param id
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.ORG_POST + ":" + Right.DELETE})
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public Json delete(String id){
		if(logger.isDebugEnabled()) logger.debug("删除数据［"+ id +"］...");
		Json result = new Json();
		try {
			this.catalogService.delete(id.split("\\|"));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("删除数据["+id+"]时发生异常:", e);
		}
		return result;
	}
	/**
	 * 加载全部数据。
	 * @return
	 */
	@RequestMapping(value = "/all", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<CatalogInfo> all(){
		return this.catalogService.datagrid(new CatalogInfo(){
			private static final long serialVersionUID = 1L;
			@Override
			public String getSort(){return "name";}
			@Override
			public String getOrder(){return "asc";}
		}).getRows();
	}
}