package com.examw.oa.service.plan.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.examw.oa.dao.plan.IDeptPlanMenberDao;
import com.examw.oa.domain.plan.DeptPlanMember;
import com.examw.oa.model.plan.DeptPlanMemberInfo;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.plan.IDeptPlanMenberService;

public class DetpPlanMenberServiceImpl extends BaseDataServiceImpl<DeptPlanMember, DeptPlanMemberInfo> implements IDeptPlanMenberService {
	private static final Logger logger = Logger.getLogger(DetpPlanMenberServiceImpl.class);
	private IDeptPlanMenberDao deptPlanMenberDao;
	public void setDeptPlanMenberDao(IDeptPlanMenberDao deptPlanMenberDao) {
		this.deptPlanMenberDao = deptPlanMenberDao;
	}

	@Override
	protected List<DeptPlanMember> find(DeptPlanMemberInfo info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected DeptPlanMemberInfo changeModel(DeptPlanMember data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Long total(DeptPlanMemberInfo info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeptPlanMemberInfo update(DeptPlanMemberInfo info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String[] ids) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<DeptPlanMemberInfo> findDeptPlanMembers(String deptId) {
		if(logger.isDebugEnabled())logger.debug("查询部门计划［"+deptId+"］下的部门计划成员数据集合...");
		List<DeptPlanMemberInfo> list = new ArrayList<>();
		List<DeptPlanMember> menber = this.deptPlanMenberDao.loedDeptPlanMembers(deptId);
		if(menber != null && menber.size() > 0){
			for(DeptPlanMember data: menber){
				DeptPlanMemberInfo info = this.changeModel(data);
				if(info != null) list.add(info);
			}
		}
		return list;
	}
}