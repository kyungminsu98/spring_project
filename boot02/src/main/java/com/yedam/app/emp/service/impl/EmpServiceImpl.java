package com.yedam.app.emp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Service // AOP가 적용될 유일한 Bean
public class EmpServiceImpl implements EmpService{
	// 기능 상 연결될 매퍼 추가
	@Autowired
	EmpMapper empMapper;
	
//	@Autowired
//	DeptMapper deptMapper;
	
	@Override
	public List<EmpVO> empList() {
		return empMapper.selectEmpAll();
	}

	@Override
	public EmpVO empInfo(EmpVO empVO) {
		return empMapper.selectEmpInfo(empVO);
	}

	@Override
	public int empInsert(EmpVO empVO) {
		int result = empMapper.insertEmpInfo(empVO);
		return result == 1 ? empVO.getEmployeeId() : -1;
	}

	@Override
	public Map<String, Object> empUpdate(EmpVO empVO) {
		Map<String, Object> map = new HashMap<>();
		boolean inSuccessed = false;
		int result = empMapper.updateEmpInfo(empVO.getEmployeeId(), empVO);
		if (result == 1) {
			inSuccessed = true;
		}
		map.put("result", inSuccessed);
		map.put("target", empVO);
		/*{
		 * 	"result" : true,
		 * 	"target" : {employeeId : 100, lastName : "King", ....}
		 * 
		 * }*/
		return map;
	}
	@Override
	public Map<String, Object> empDelete(EmpVO empVO) {
		Map<String, Object> map = new HashMap<>();
		
		int result = empMapper.deleteEmpInfo(empVO.getEmployeeId());
		if(result == 1) {
			map.put("employeeId", empVO.getEmployeeId());
		}
		return map;
	}
	
}
