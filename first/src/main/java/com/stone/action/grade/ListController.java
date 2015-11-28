//package com.stone.action.grade;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.stone.mapper.GradeDao;
//import com.stone.model.Grade;
//
//@Controller
//@RequestMapping("/content/grade")
//public class ListController {
//
//	@Autowired
//	GradeDao gradeDao;
//
//	@RequestMapping(value="/list")
//	@ResponseBody
//	public Object list(){
//		List<Grade> grades = new ArrayList<Grade>();
//		Map<String,Object> result = new HashMap<String,Object>();
//		try{
//			grades = gradeDao.selectAll();
//			result.put("grades", grades);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return result;
//	}
//
//}
