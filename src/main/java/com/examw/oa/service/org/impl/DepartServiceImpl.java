package com.examw.oa.service.org.impl;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import com.examw.oa.dao.org.IDepartDao;
import com.examw.oa.domain.org.Depart;
import com.examw.oa.model.org.DepartInfo;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.org.IDepartService;
import com.examw.oa.service.security.impl.MenuServiceImpl;
/**
 * 部门服务。
 * @author lq
 * @since 2014-06-12.
 */
public class DepartServiceImpl extends BaseDataServiceImpl<Depart, DepartInfo> implements IDepartService {
	private static Logger logger = Logger.getLogger(MenuServiceImpl.class);
	private IDepartDao departdao;
	/**
	 * 设置部门数据接口。
	 * @param 
	 * 部门数据接口。
	 */
	public void setDepartdao(IDepartDao departdao) {
		this.departdao = departdao;
	}
	/*
	 * 查询数据。
	 * @see com.examw.netplatform.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Depart> find(DepartInfo info) {
		return this.departdao.findDepart(info);
	}
	/*
	 * 类型转换。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected DepartInfo changeModel(Depart data) {
		if(data == null) return null;
		DepartInfo info = new DepartInfo();
		BeanUtils.copyProperties(data, info, new String[] {"children"});
		info.setFullName(this.loadFullName(data));
		if(data.getChildren() != null && data.getChildren().size() > 0){
			List<DepartInfo> children = new ArrayList<>();
			for(Depart d : data.getChildren()){
				DepartInfo c = this.changeModel(d);
				if(c != null){
					c.setPid(data.getId());
					children.add(c);
				}
			}
			info.setChildren(children);
		}
		return info;
	}
	/*
	 * 部门全称。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#loadFullName(java.lang.Object)
	 */
	private String loadFullName(Depart data){
		if(data == null) return null;
		if(data.getParent() == null) return data.getName();
		StringBuilder sb = new StringBuilder(data.getName());
		if(data.getParent() != null){
			sb.insert(0, this.loadFullName(data.getParent()) + ">");
		}
		return sb.toString();
	}
	/*
	 *  统计查询数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#total(java.lang.Object)
	 */
	@Override
	protected Long total(DepartInfo info) {
		return this.departdao.total(info);
	}
	/*
	 * 更新数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#update(java.lang.Object)
	 */
	@Override
	public DepartInfo update(DepartInfo info) {
		if(info == null) return null;
		boolean isAdded = false;
		Depart data = this.departdao.load(Depart.class, info.getId());
		if(isAdded = (data == null)){
			if(StringUtils.isEmpty(info.getId())){
				info.setId(UUID.randomUUID().toString());
			}
			info.setCreateTime(new Date());
			
			data = new Depart();
		}
		if(!isAdded)info.setCreateTime(data.getCreateTime());
		BeanUtils.copyProperties(info, data);
		if(!StringUtils.isEmpty(info.getPid()) && (data.getParent() == null || !data.getParent().getId().equalsIgnoreCase(info.getPid()))){
			Depart parent = this.departdao.load(Depart.class, info.getPid());
			if(parent != null){
				if(info.getPid().equals(info.getId())){
					throw new RuntimeException("不能选择相同的机构！");
				}
				data.setParent(parent);
			}
		}
		if(isAdded) this.departdao.save(data);
		return info;
	}
	/*
	 * 删除数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#delete(java.lang.String[])
	 */
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
	/**
	 * 加载部门数据。
	 */
	@Override
	public List<DepartInfo> loadDeparts() {
		List<DepartInfo> results = new ArrayList<>();
		List<Depart> list = this.departdao.findDeparts();
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				DepartInfo info = this.changeModel(list.get(i));
				if(info != null) results.add(info);
			}
		}
		return results;
	}
}