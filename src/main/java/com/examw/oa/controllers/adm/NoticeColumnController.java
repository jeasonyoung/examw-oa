package com.examw.oa.controllers.adm;

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
import com.examw.oa.domain.security.Right;
import com.examw.oa.model.adm.NoticeColumnInfo;
import com.examw.oa.service.adm.INoticeColumnService;
/**
 * 栏目设置控制器。
 * @author lq.
 * @since 2014-07-14.
 */
@Controller
@RequestMapping(value = "/adm/column")
public class NoticeColumnController {
	private static final Logger logger = Logger.getLogger(NoticeColumnController.class);
	//注入栏目服务接口。
	@Resource
	private INoticeColumnService noticeColumnService;
	/**
	 * 列表页面。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ADM_COLUMN + ":" + Right.VIEW})
	@RequestMapping(value={"","/list"}, method = RequestMethod.GET)
	public String list(Model model){
		if(logger.isDebugEnabled())logger.debug("加载列表页面...");
		model.addAttribute("PER_UPDATE", ModuleConstant.ADM_COLUMN + ":" + Right.UPDATE);
		model.addAttribute("PER_DELETE", ModuleConstant.ADM_COLUMN + ":" + Right.DELETE);
		return "adm/notice_column_list";
	}
	/**
	 * 编辑页面。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ADM_COLUMN + ":" + Right.UPDATE})
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String edit(String ignore,Model model){
		if(logger.isDebugEnabled())logger.debug("加载编辑页面...");
		model.addAttribute("CURRENT_IGNORE", StringUtils.isEmpty(ignore) ? "" : ignore);
		return "adm/notice_column_edit";
	}
	/**
	 * 查询数据。
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ADM_COLUMN + ":" + Right.VIEW})
	@RequestMapping(value="/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<NoticeColumnInfo> datagrid(NoticeColumnInfo info){
		if(logger.isDebugEnabled()) logger.debug("加载列表数据...");
		return this.noticeColumnService.datagrid(info);
	}
	/**
	 * 更新数据。
	 * @param info
	 * 更新源数据。
	 * @return
	 * 更新后数据。
	 */
	@RequiresPermissions({ModuleConstant.ADM_COLUMN + ":" + Right.UPDATE})
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@ResponseBody
	public Json update(NoticeColumnInfo info){
		if(logger.isDebugEnabled())logger.debug("更新数据...");
		Json result = new Json();
		try {
			result.setData(this.noticeColumnService.update(info));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("更新数据发生异常", e);
		}
		return result;
	}
	/**
	 * 删除数据。
	 * @param id
	 * @return
	 */
	@RequiresPermissions({ModuleConstant.ADM_COLUMN + ":" + Right.DELETE})
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public Json delete(String id){
		if(logger.isDebugEnabled()) logger.debug("删除数据［"+ id +"］...");
		Json result = new Json();
		try {
			this.noticeColumnService.delete(id.split("\\|"));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("删除数据["+id+"]时发生异常:", e);
		}
		return result;
	}
	/**
	 * 栏目树结构数据。
	 * @return
	 */
	@RequestMapping(value = "/tree", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<TreeNode> tree(String ignore){
		if(logger.isDebugEnabled()) logger.debug("加载部门树结构数据［ignore="+ ignore+"］...");
		return this.noticeColumnService.loadNoticeColumn(ignore);
	}
}