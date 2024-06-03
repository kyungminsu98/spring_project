package com.yedam.app.dept.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.dept.mapper.DeptMapper;
import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;

@Service // AOP가 적용될 유일한 Bean
public class DeptServiceImpl implements DeptService{
	// 기능 상 연결될 매퍼 추가
	@Autowired
	DeptMapper deptMapper;
		
	@Override
	public List<DeptVO> deptList() {
		return deptMapper.selectDeptAll();
	}

	@Override
	public DeptVO deptInfo(DeptVO deptVO) {
		return deptMapper.selectDeptInfo(deptVO);
	}

	@Override
	public int deptInsert(DeptVO deptVO) {
		int result = deptMapper.insertDeptInfo(deptVO);
		return result == 1 ? deptVO.getDepartmentId() : -1;
	}

	@Override
	public Map<String, Object> deptUpdate(DeptVO deptVO) {
		Map<String, Object> map = new HashMap<>();
		boolean inSuccessed = false;
		int result = deptMapper.updateDeptInfo(deptVO.getDepartmentId(), deptVO);
		if (result == 1) {
			inSuccessed = true;
		}
		map.put("result", inSuccessed);
		map.put("target", deptVO);
		/*{
		 * 	"result" : true,
		 * 	"target" : {employeeId : 100, lastName : "King", ....}
		 * 
		 * }*/
		return map;
	}
	@Override
	public Map<String, Object> deptDelete(DeptVO deptVO) {
		Map<String, Object> map = new HashMap<>();
		
		int result = deptMapper.deleteDeptInfo(deptVO.getDepartmentId());
		if(result == 1) {
			map.put("departmentId", deptVO.getDepartmentId());
		}
		return map;
	}
	
}

