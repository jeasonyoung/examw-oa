package com.examw.oa.dao.security.impl;
 
import java.util.List; 

import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.dao.security.IMenuDao;
import com.examw.oa.domain.security.Menu;
/**
 * 菜单数据操作实现类。
 * @author yangyong.
 * @since 2014-04-28.
 */
public class MenuDaoImpl  extends BaseDaoImpl<Menu> implements IMenuDao{

	@Override
	public List<Menu> findMenus() {
		String hql =  "from Menu m  where m.parent = null order by m.orderNo";
		return this.find(hql, null, null, null);
	}
}