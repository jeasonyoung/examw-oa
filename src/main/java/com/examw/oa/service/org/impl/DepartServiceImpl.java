package com.examw.oa.service.org.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;




import com.examw.oa.dao.org.IDepartDao;
import com.examw.oa.domain.org.Depart;

import com.examw.oa.model.org.DepartInfo;

import com.examw.oa.service.org.IDepartService;
import com.examw.oa.service.security.impl.MenuServiceImpl;
/**
 * 部门服务。
 * @author lq
 * @since 2014-06-11.
 */
public class DepartServiceImpl implements IDepartService {
	private static Logger logger = Logger.getLogger(MenuServiceImpl.class);
	private IDepartDao departdao;
	//private static Map<String, ModuleSystem> mapSystemCache = Collections.synchronizedMap(new HashMap<String,ModuleSystem>());
	/**
	 * 设置部门数据接口。
	 * @param menuDao
	 * 部门数据接口。
	 */
	public void setDepartdao(IDepartDao departdao) {
		this.departdao = departdao;
	}
	//加载部门数据
	@Override
	public List<DepartInfo> loadDepart() {
		List<DepartInfo> results = new ArrayList<>();
		List<Depart> list = this.departdao.findDepart();
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				DepartInfo info = this.changeModel(list.get(i));
				if(info != null) results.add(info);
			}
		}
		return results;
	}
	//类型转换
	protected DepartInfo changeModel(Depart data) {
		if(data == null) return null;
		DepartInfo info = new DepartInfo();
		BeanUtils.copyProperties(data, info);
		return info;
	}
	//更新数据
	@Override
	public DepartInfo update(DepartInfo info) {
		if(info == null) return null;
		Depart data = StringUtils.isEmpty(info.getId()) ? null : this.departdao.load(Depart.class, info.getId());
		if((data == null)){
			if(StringUtils.isEmpty(info.getId())){
				info.setId(UUID.randomUUID().toString());
			}
			data = new Depart();
		}
		BeanUtils.copyProperties(info, data);
		return info;
	}
	//删除部门
	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length; i++){
			Depart data = this.departdao.load(Depart.class, ids[i]);
			if(data != null && (data.getChildren() == null || data.getChildren().size() == 0)){
				this.departdao.delete(data); 
				logger.info("删除数据:" + data.getName());
			}
		}
	}
	//数据初始化
	@Override
	public void init() throws Exception {
		logger.info("开始初始化菜单数据...");
		String msg = null;
		DepartInfo depart = (DepartInfo) this.loadDepart();
		if(depart == null || depart.getPid() == null || depart.getChildren().size() == 0){
			logger.info(msg = "菜单文件中没有系统信息或菜单数据信息！");
			throw new Exception(msg);
		}
		logger.info("初始化完成！");
		
	}
	
	
	
}
