package com.examw.oa.service.org.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.model.DataGrid;
import com.examw.oa.dao.org.PositionDao;
import com.examw.oa.domain.org.Position;
import com.examw.oa.domain.security.Right;
import com.examw.oa.domain.security.Role;
import com.examw.oa.model.org.PositionInfo;
import com.examw.oa.model.security.RightInfo;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.org.PositionService;

public class PositionServiceImpl extends BaseDataServiceImpl<Position, PositionInfo> implements PositionService {
	private PositionDao positiondao;
	private Map<Integer, String> positionNameMap;
	public void setPositiondao(PositionDao positiondao) {
		this.positiondao = positiondao;
	}
	
	public void setPositionNameMap(Map<Integer, String> positionNameMap) {
		this.positionNameMap = positionNameMap;
	}

	/*
	 * 查找数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Position> find(PositionInfo info) {
		
		return  this.positiondao.findPosition(info);
	}
	/*
	 * 类型转换。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected PositionInfo changeModel(Position data) {
		if(data == null) return null;
		PositionInfo info = new PositionInfo();
		BeanUtils.copyProperties(data, info);
		return info;
	}
	/*
	 *  统计查询数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#total(java.lang.Object)
	 */
	@Override
	protected Long total(PositionInfo info) {
		return this.positiondao.total(info);
	}
	/*
	 *  加载数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#total(java.lang.Object)
	 */
	@Override
	public PositionInfo update(PositionInfo info) {
		if(info == null || StringUtils.isEmpty(info.getId())) return null;
		boolean isAdded = false;
		Position data =  this.positiondao.load(Position.class, info.getId());
		if(isAdded = (data == null)){
			if(StringUtils.isEmpty(info.getId())){
				info.setId(UUID.randomUUID().toString());
			}
			data = new Position();
		}
		BeanUtils.copyProperties(info, data);
		if(isAdded)this.positiondao.save(data);
		return info;
	}

	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length; i++){
			if(StringUtils.isEmpty(ids[i])) continue;
			Position data = this.positiondao.load(Position.class, ids[i]);
			if(data != null){
				this.positiondao.delete(data);
			}
		}
		
	}
	/*@Override
	public void init() throws Exception {
		//查看
		this.update(new PositionInfo());
		//修改
		this.update(new RightInfo(((Integer)Right.UPDATE).toString(), this.getRightName(Right.UPDATE), Right.UPDATE));
		//删除
		this.update(new RightInfo(((Integer)Right.DELETE).toString(), this.getRightName(Right.DELETE), Right.DELETE));
	}
	public String getPositionName(int position) {
		if(this == null || this.positionNameMap.size() == 0) return null;
		return this.positionNameMap.get(position);
	}
*/

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
