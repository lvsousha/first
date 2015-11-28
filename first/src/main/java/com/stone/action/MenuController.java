//package com.stone.action;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.stone.mapper.SchoolDao;
//import com.stone.service.TreeService;
//
//@Controller
//@RequestMapping("/content/example")
//public class MenuController {
//
//	@Autowired
//	TreeService treeService;
//
//	@Autowired
//	SchoolDao schoolDao;
//
//
//	@RequestMapping(value="/getTree", produces = "text/html;charset=UTF-8")
//	@ResponseBody
//	public Object getTree(int parent){
//		Map<String,Object> result = new HashMap<String,Object>();
//		result.put("children", treeService.loadTrees(parent));
//		return result;
//	}
//
//	@RequestMapping(value="/list", produces = "text/html;charset=UTF-8")
//	@ResponseBody
//	public Object list(){
//		Map<String,Object> result = new HashMap<String,Object>();
//		result.put("school", schoolDao.selectAll());
//		return result;
//	}
//
//}
