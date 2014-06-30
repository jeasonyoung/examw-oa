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
import com.examw.oa.domain.plan.Business;
import com.examw.oa.model.plan.BusinessInfo;
import com.examw.oa.service.plan.IBusinessService;
/**
 * 业务系统控制器。
 * @author lq.
 * @since 2014-06-23.
 */
@Controller
@RequestMapping(value = "/plan/business")
public class BusinessController {
	private static Logger logger = Logger.getLogger(LogController.class);
	/**
	 *业务系统服务。
	 */
	@Resource
	private IBusinessService businessService;
	/**
	 * 获取列表页面。
	 * @return
	 * 列表页面
	 */
	@RequestMapping(value={"","/list"}, method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("STATUS_STOP_VALUE",Business.STATUS_STOP);
		model.addAttribute("STATUS_STOP_NAME", this.businessService.loadStatusName(Business.STATUS_STOP));
		
		model.addAttribute("STATUS_NORMAL_VALUE",Business.STATUS_NORMAL);
		model.addAttribute("STATUS_NORMAL_NAME", this.businessService.loadStatusName(Business.STATUS_NORMAL));
		
		model.addAttribute("STATUS_TEST_VALUE",Business.STATUS_TEST);
		model.addAttribute("STATUS_TEST_NAME", this.businessService.loadStatusName(Business.STATUS_TEST));
		return "plan/business_list";
	}
	/**
	 * 获取编辑页面。
	 * @return
	 * 编辑页面。
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_ROLE + ":" + Right.UPDATE})
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String edit(Model model){
		model.addAttribute("STATUS_STOP_NAME", this.businessService.loadStatusName(Business.STATUS_STOP));
		model.addAttribute("STATUS_NORMAL_NAME", this.businessService.loadStatusName(Business.STATUS_NORMAL));
		model.addAttribute("STATUS_TEST_NAME", this.businessService.loadStatusName(Business.STATUS_TEST));
		return "plan/business_edit";
	}
	/**
	 * 查询数据。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_RIGHT + ":" + Right.VIEW})
	@RequestMapping(value="/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<BusinessInfo> datagrid(BusinessInfo info){
		return this.businessService.datagrid(info);
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
	public Json update(BusinessInfo info){
		Json result = new Json();
		try {
			result.setData(this.businessService.update(info));
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
			this.businessService.delete(id.split("\\|"));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("删除数据["+id+"]时发生异常:", e);
		}
		return result;
	}
}