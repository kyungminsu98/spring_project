package com.yedam.app.test.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;
import com.yedam.app.emp.service.EmpVO;

@Controller
public class DeptController {

    @Autowired
    DeptService deptService;

    // GET: 전체조회
    @GetMapping("deptList")
    public String deptList(Model model) {
        List<DeptVO> list = deptService.deptList();
        model.addAttribute("deptList", list);
        return "dept/list";
    }

    // GET: 단건조회
    @GetMapping("deptInfo")
    public String deptInfo(DeptVO deptVO, Model model) {
        DeptVO findVO = deptService.deptInfo(deptVO);
        model.addAttribute("deptInfo", findVO);
        return "dept/info";
    }

    // GET: 등록폼
    @GetMapping("deptInsert")
	public String deptInsertForm(Model model) {
		model.addAttribute("deptVO", new DeptVO());
		return "dept/insert";
	}

    // POST: 등록 처리
    @PostMapping("deptInsert")
    public String deptInsertProcess(DeptVO deptVO) {
        int did = deptService.deptInsert(deptVO);
        return did > -1 ? "redirect:deptInfo?departmentId=" + did : "deptList";
    }

    // GET: 수정폼
    @PostMapping("deptUpdateForm")
    public String deptUpdateForm(Integer departmentId, Model model) {
        DeptVO deptVO = new DeptVO();
        deptVO.setDepartmentId(departmentId);
        DeptVO findVO = deptService.deptInfo(deptVO);
        model.addAttribute("deptInfo", findVO);
        return "dept/update";
    }

    // POST: 수정 처리
    @PostMapping("deptUpdate") // URL 변경
    @ResponseBody
    public Map<String, Object> deptUpdateAJAX(DeptVO deptVO) {
        return deptService.deptUpdate(deptVO);
    }

    // GET: 삭제 처리
    @GetMapping("deptDelete")
    public String deptDelete(DeptVO deptVO) {
        deptService.deptDelete(deptVO);
        return "redirect:deptList";
    }
}
