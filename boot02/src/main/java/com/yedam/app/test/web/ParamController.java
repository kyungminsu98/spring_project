package com.yedam.app.test.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpVO;

@Controller
public class ParamController {
	// QueryString(질의 문자열) : key=value&key=value&...
	// method는 상관없음
	// Content-type : application/x-www-form-urlencode

	// QueryString => 커맨드객체
	@RequestMapping(path = "comobj", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String commandObject(EmpVO empVO) {
		String result = "";
		result += "path : / comobj\n";
		result += "\t employee_id : " + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		return result;
	}

	// QueryString => @RequestParam : 기본타입, 단일값
	@RequestMapping(path = "reqparm", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String requestParam(@RequestParam Integer employeeId, // 필수
			String lastName, // 생략가능
			@RequestParam(name = "message", defaultValue = "No message", required = true) String msg) {
		String result = "";
		result += "path : / reqparm";
		result += "\n\t employee_id : " + employeeId;
		result += "\n\t last_name : " + lastName;
		result += "\n\t messsage : " + msg;
		return result;
	}

	// @PathVariable : 경로에 값을 포함하는 방식, 단일 값
	// Method는 상관없음
	// Content-Type도 상관없음
	@RequestMapping("pathvar/{id}.{page}")
	@ResponseBody
	public String pathVar(@PathVariable String id, @PathVariable int page) {
		String result = "";
		result += "path : / pathvar";
		result += "\n\t id : " + id;
		result += "\n\t page : " + page;
		return result;
	}

	@RequestMapping("json")
	@ResponseBody
	public String json(@RequestBody EmpVO empVO) {
		String result = "";
		result += "path : / json";
		result += "\n\t employee_id : " + empVO.getEmployeeId();
		result += "\n\t last_name : " + empVO.getLastName();
		return result;
	}

	// @RequestBody : JSON 포맷, 배열 or 객체
	// Method : POST, PUT
	// Content-type : application/json
	@PostMapping("resbodyList")
	@ResponseBody
	public String requestBodyList(@RequestBody List<EmpVO> list) {
		String result = "path : / restbody \n";
		for (EmpVO empVO : list) {
			result += "\t employee_id : " + empVO.getEmployeeId();
			result += "\t last_name : " + empVO.getLastName();
			result += "\n";
		}
		return result;

	}
}