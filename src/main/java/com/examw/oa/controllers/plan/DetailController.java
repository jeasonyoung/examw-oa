package com.examw.oa.controllers.plan;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examw.model.DataGrid;
import com.examw.model.Json;
import com.examw.oa.controllers.security.LogController;
import com.examw.oa.domain.plan.Detail;
import com.examw.oa.model.plan.DetailInfo;
import com.examw.oa.service.plan.IDetailService;

/**
 * 业务系统控制器。
 * @author lq.
 * @since 2014-07-02.
 */
@Controller
@RequestMapping(value = "/plan/detail")
public class DetailController {
	private static Logger logger = Logger.getLogger(LogController.class);
	@Resource
	private IDetailService detailService;
	/**
	 * 获取列表页面。
	 * @return
	 * 列表页面
	 */
	@RequestMapping(value={"","/list"}, method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("TYPE_PLAN_VALUE",Detail.TYPE_PLAN);
		model.addAttribute("TYPE_PLAN_NAME", this.detailService.loadTypeName(Detail.TYPE_PLAN));
		
		model.addAttribute("TYPE_SUMMARY_VALUE",Detail.TYPE_SUMMARY);
		model.addAttribute("TYPE_SUMMARY_NAME", this.detailService.loadTypeName(Detail.TYPE_SUMMARY));
		
		model.addAttribute("TYPE_SUPPORT_VALUE",Detail.TYPE_SUPPORT);
		model.addAttribute("TYPE_SUPPORT_NAME", this.detailService.loadTypeName(Detail.TYPE_SUPPORT));
		
		model.addAttribute("TYPE_SUGGESTIONS_VALUE",Detail.TYPE_SUGGESTIONS);
		model.addAttribute("TYPE_SUGGESTIONS_NAME", this.detailService.loadTypeName(Detail.TYPE_SUGGESTIONS));
		return "plan/detail_list";
	}
	/**
	 * 获取编辑页面。
	 * @return
	 * 编辑页面。
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_ROLE + ":" + Right.UPDATE})
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String edit(Model model){
		return "plan/detail_edit";
	}
	/**
	 * 查询数据。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_RIGHT + ":" + Right.VIEW})
	@RequestMapping(value="/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<DetailInfo> datagrid(DetailInfo info){
		return this.detailService.datagrid(info);
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
	public Json update(DetailInfo info){
		Json result = new Json();
		try {
			result.setData(this.detailService.update(info));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("更新业务系统数据发生异常", e);
		}
		return result;
	}
	/**
	 * 删除数据。
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public Json delete(String id){
		Json result = new Json();
		try {
			this.detailService.delete(id.split("\\|"));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("删除数据["+id+"]时发生异常:", e);
		}
		return result;
	}
}