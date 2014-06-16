package com.examw.oa.service.org.impl;

import java.util.List;
import java.util.UUID;





import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.org.IDepartDao;
import com.examw.oa.dao.org.IPostDao;
import com.examw.oa.domain.org.Depart;
import com.examw.oa.domain.org.Post;

import com.examw.oa.model.org.PostInfo;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.org.IPostService;
/**
 * 岗位信息。
 * @author lq
 * @since 2014-06-13.
 */
public class PostServiceImpl extends BaseDataServiceImpl<Post, PostInfo> implements IPostService {
	private IPostDao postdao;
	private IDepartDao departdao;
	/**
	 * 设置岗位信息数据接口。
	 * @param postdao
	 * 岗位信息数据接口。
	 */
	public void setPostdao(IPostDao postdao) {
		this.postdao = postdao;
	}
	/**
	 * 设置部门数据接口。
	 * @param departdao
	 * 部门数据接口。
	 */
	public void setDepartdao(IDepartDao departdao) {
		this.departdao = departdao;
	}
	/*
	 * 查询所有。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#delete(java.lang.String[])
	 */
	@Override
	protected List<Post> find(PostInfo info) {
		
		return this.postdao.findPosts(info);
	}
	
	/*
	 * 类型转换。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#delete(java.lang.String[])
	 */
	@Override
	protected PostInfo changeModel(Post data) {
		if(data == null) return null;
		PostInfo info = new PostInfo();
		info.setId(data.getId());
		info.setCode(data.getCode());
		info.setName(data.getName());
		info.setDeptId(data.getDepart().getId());
		info.setDepartName(data.getDepart().getName());
		return info;
	}
	/*
	 * 查询总数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#delete(java.lang.String[])
	 */
	
	@Override
	protected Long total(PostInfo info) {
		return this.postdao.total(info);
	}
	/*
	 * 更新数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#delete(java.lang.String[])
	 */
	@Override
	public PostInfo update(PostInfo info) {
		if(info == null) return null;
		boolean isAdded = false;
		Post data = StringUtils.isEmpty(info.getId()) ? null : this.postdao.load(Post.class, info.getId());
		if(isAdded = (data == null)){
			if(StringUtils.isEmpty(info.getId())) {
				info.setId(UUID.randomUUID().toString());
			}
			data = new Post();
		}
		BeanUtils.copyProperties(info, data);
		if(!StringUtils.isEmpty(info.getDeptId()) && (data.getDepart() == null || !data.getDepart().getId().equalsIgnoreCase(info.getDeptId()))){
			Depart depart = this.departdao.load(Depart.class, info.getDeptId());
			if(depart != null) {
				if(depart.getChildren() != null && depart.getChildren().size() > 0){
					throw new RuntimeException("必须！");
				}
				data.setDepart(depart);
				info.setDepartName(depart.getName());
			}
		}
		if(isAdded) this.postdao.save(data);
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
			Post data = this.postdao.load(Post.class, ids[i]);
			if(data != null) this.postdao.delete(data);
		}
		
	}
}