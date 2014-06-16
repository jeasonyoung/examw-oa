package com.examw.oa.service.org.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.org.IDepartDao;
import com.examw.oa.dao.org.IEmplDao;
import com.examw.oa.dao.org.IPostDao;
import com.examw.oa.dao.org.IRankDao;
import com.examw.oa.domain.org.Depart;
import com.examw.oa.domain.org.Empl;
import com.examw.oa.domain.org.Post;
import com.examw.oa.domain.org.Rank;
import com.examw.oa.model.org.EmplInfo;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.org.IEmplService;
/**
 * 员工信息服务接口。
 * @author lq.
 * @since 2014-06-16.
 */
public class EmplServiceImpl extends BaseDataServiceImpl<Empl,EmplInfo> implements IEmplService {
	private IEmplDao empldao;
	private IDepartDao departdao;
	private IPostDao postdao;
	private IRankDao rankdao;
	/**
	 * 设置员工信息数据接口。
	 * @param empldao
	 * 员工数据接口。
	 */
	public void setEmpldao(IEmplDao empldao) {
		this.empldao = empldao;
	}
	/**
	 * 设置部门数据接口。
	 * @param departdao
	 * 部门数据接口。
	 */
	public void setDepartdao(IDepartDao departdao) {
		this.departdao = departdao;
	}
	/**
	 * 设置岗位数据接口。
	 * @param menuRightDao
	 * 岗位数据接口。
	 */
	public void setPostdao(IPostDao postdao) {
		this.postdao = postdao;
	}
	/**
	 * 设置等级数据接口。
	 * @param menuRightDao
	 * 等级数据接口。
	 */
	public void setRankdao(IRankDao rankdao) {
		this.rankdao = rankdao;
	}
	/*
	 * 查询数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Empl> find(EmplInfo info) {
		return this.empldao.findEmpls(info);
	}
	/*
     * 数据类型转换。
     * @see com.examw.oa.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
     */
	@Override
	protected EmplInfo changeModel(Empl data) {
		if(data == null) return null;
		EmplInfo info = new EmplInfo();
		info.setId(data.getId());
		info.setCode(data.getCode());
		info.setName(data.getName());
		info.setGender(data.getGender());
		info.setPhone(data.getPhone());
		info.setDepartId(data.getDepart().getId());
		info.setDepartName(data.getDepart().getName());
		info.setPostId(data.getPost().getId());
		info.setPostName(data.getPost().getName());
		info.setRankId(data.getRank().getId());
		info.setRankName(data.getRank().getName());
		info.setEmail(data.getEmail());
		info.setIdCard(data.getIdCard());
		info.setBrithday(data.getBrithday());
		info.setJoinTime(data.getJoinTime());
		info.setPassword(data.getPassword());
		info.setStatus(data.getStatus());
		return info;
	}
	/*
     * 查询数据总数。
     * @see com.examw.oa.service.impl.BaseDataServiceImpl#total(java.lang.Object)
     */
	@Override
	protected Long total(EmplInfo info) {
		return this.empldao.total(info);
	}
	/*
	 * 更新数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#update(java.lang.Object)
	 */
	@Override
	public EmplInfo update(EmplInfo info) {
		if(info == null) return null;
		boolean isAdded = false;
		Empl data =  this.empldao.load(info);
		if(isAdded = (data == null)){
			info.setId(UUID.randomUUID().toString());
		}
		info.setCreateTime(new Date());
		data = new Empl();
		data.setId(info.getId());
		if(!isAdded)info.setCreateTime(data.getCreateTime());
		BeanUtils.copyProperties(info, data);
		if(!StringUtils.isEmpty(info.getDepartId()) && (data.getDepart() == null || !data.getDepart().getId().equalsIgnoreCase(info.getDepartId()))){
			Depart depart = this.departdao.load(Depart.class, info.getDepartId());
			if(depart != null) {
				if(depart.getChildren() != null && depart.getChildren().size() > 0){
					throw new RuntimeException("必须！");
				}
				data.setDepart(depart);
				info.setDepartName(depart.getName());
			}
		}
		
		if(!StringUtils.isEmpty(info.getPostId()) && (data.getPost() == null || !data.getPost().getId().equalsIgnoreCase(info.getPostId()))){
			Post post = this.postdao.load(Post.class, info.getPostId());
			if(post != null){
				data.setPost(post);
				info.setPostName(post.getName());
			}
		}
		if(!StringUtils.isEmpty(info.getRankId()) && (data.getRank() == null || !data.getRank().getId().equalsIgnoreCase(info.getRankId()))){
			Rank rank = this.rankdao.load(Rank.class, info.getRankId());
			if(rank != null){
				data.setRank(rank);
				info.setRankName(rank.getName());
			}
		}
		if(StringUtils.isEmpty(info.getDepartName()) && data.getDepart() != null){
			info.setDepartName(data.getDepart().getName());
		}
		
		if(StringUtils.isEmpty(info.getPostName()) && data.getPost() != null){
			info.setPostName(data.getPost().getName());
		}
		if(StringUtils.isEmpty(info.getRankName()) && data.getRank() != null){
			info.setRankName(data.getRank().getName());
		}
		if(isAdded)this.empldao.save(data);
		
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
			Empl data = this.empldao.load(Empl.class, ids[i]);
			if(data != null) this.empldao.delete(data);
		}	
	}
}