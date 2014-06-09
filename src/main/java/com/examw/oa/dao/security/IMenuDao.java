package com.examw.oa.dao.security;

import java.util.List;

import com.examw.oa.dao.IBaseDao;
import com.examw.oa.domain.security.Menu;

/**
 * 菜单数据操作接口。
 * @author yangyong.
 *	@since 2014-04-28.
 */
public interface IMenuDao extends IBaseDao<Menu> {
	/**
	 * 查询菜单数据。
	 * @return
	 * 结果数据。
	 */
	List<Menu> findMenus();
}