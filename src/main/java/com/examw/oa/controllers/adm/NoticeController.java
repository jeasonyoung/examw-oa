package com.examw.oa.controllers.adm;

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
import com.examw.oa.domain.adm.Notice;
import com.examw.oa.model.adm.NoticeInfo;
import com.examw.oa.service.adm.INoticeService;
/**
 * 通告信息控制器。
 * @author lq.
 * @since 2014-07-15.
 */
@Controller
@RequestMapping(value = "/adm/notice")
public class NoticeController {
	private static Logger logger = Logger.getLogger(NoticeController.class);
	/**
	 * 通告服务接口。
	 */
	@Resource
	private INoticeService noticeService;
	/**
	 * 列表页面。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.VIEW})
	@RequestMapping(value = {"","/list"}, method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("TYPE_NOTICE_VALUE",Notice.TYPE_NOTICE);
		model.addAttribute("TYPE_NOTICE_NAME", this.noticeService.loadTypeName(Notice.TYPE_NOTICE));
		model.addAttribute("TYPE_RULES_VALUE",Notice.TYPE_RULES);
		model.addAttribute("TYPE_RULES_NAME", this.noticeService.loadTypeName(Notice.TYPE_RULES));
		return "adm/not_list";
	}
	/**
	 * 添加页面。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.UPDATE})
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(String columnId, Model model){
		model.addAttribute("CURRENT_COLUMN_ID", StringUtils.isEmpty(columnId) ? "" : columnId);

		model.addAttribute("TYPE_NOTICE_NAME", this.noticeService.loadTypeName(Notice.TYPE_NOTICE));
		model.addAttribute("TYPE_RULES_NAME", this.noticeService.loadTypeName(Notice.TYPE_RULES));
	
		return "adm/not_edit";
   }
	/**
	 * 查询数据。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_MENU_RIGHT + ":" + Right.VIEW})
	@RequestMapping(value="/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<NoticeInfo> datagrid(NoticeInfo info){
		return this.noticeService.datagrid(info);
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
	public Json update(NoticeInfo info){
		Json result = new Json();
		try {
			result.setData(this.noticeService.update(info));
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
			this.noticeService.delete(id.split("\\|"));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("删除数据["+id+"]时发生异常:", e);
		}
		return result;
	}
}