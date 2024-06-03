package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.dept.mapper.DeptMapper;
import com.yedam.app.dept.service.DeptVO;
import com.yedam.app.emp.mapper.EmpMapper;

@SpringBootTest
class Boot02ApplicationTests {

	@Autowired
	EmpMapper empMapper;

	@Autowired
	DeptMapper deptMapper;
	
//	//@Test
//	public void empList() {
//		List<EmpVO> list = empMapper.selectEmpAll();
//		assertTrue(!list.isEmpty());
//	}
	@Test
	public void deptList() {
		List<DeptVO> list = deptMapper.selectDeptAll();
		assertTrue(!list.isEmpty());
	}
//	//@Test
//	public void empInfo() {
//		EmpVO empVO = new EmpVO();
//		empVO.setEmployeeId(100);
//		
//		EmpVO findVO = empMapper.selectEmpInfo(empVO);
//		assertEquals(findVO.getLastName(), "King");
//	}
//	
//	//@Test
//	public void empInsert() {
//		EmpVO empVO = new EmpVO();
//		empVO.setLastName("Hong");
//		empVO.setEmail("kdHong@google.com");
//		empVO.setJobId("IT_PROG");
//		
//		int result = empMapper.insertEmpInfo(empVO);
//		System.out.println(empVO.getEmployeeId());
//		assertEquals(result, 1);
//	}
//	//@Test
//	public void empUpdate() {
//		// 1) 대상 조회
//		EmpVO empVO = new EmpVO();
//		empVO.setEmployeeId(208);
//		
//		EmpVO findVO = empMapper.selectEmpInfo(empVO);
//		System.out.println(findVO);
//		// 2) 정보수정
//		findVO.setLastName("Kang");
//		int result = empMapper.updateEmpInfo(findVO.getEmployeeId(), findVO);
//				assertEquals(result, 1);
//	}
//	//@Test
//	public void empDelete(){
//		int result = empMapper.deleteEmpInfo(555);
//		assertEquals(result, 1);
//	}
}
