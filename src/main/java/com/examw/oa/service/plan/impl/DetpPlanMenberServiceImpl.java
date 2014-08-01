package com.examw.oa.service.plan.impl;

import java.util.List;
import com.examw.oa.domain.plan.DeptPlanMember;
import com.examw.oa.model.plan.DeptPlanMemberInfo;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.plan.IDeptPlanMenberService;

public class DetpPlanMenberServiceImpl extends BaseDataServiceImpl<DeptPlanMember, DeptPlanMemberInfo> implements IDeptPlanMenberService {
	//private IDeptPlanMenberDao deptPlanMenberDao;
//	public void setDeptPlanMenberDao(IDeptPlanMenberDao deptPlanMenberDao) {
//		this.deptPlanMenberDao = deptPlanMenberDao;
//	}

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

}
