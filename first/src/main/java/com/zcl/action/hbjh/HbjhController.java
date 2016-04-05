package com.zcl.action.hbjh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcl.dao.BasicDao;
import com.zcl.model.Basic;
import com.zcl.model.User;

@Controller
@RequestMapping("/content/hbjh")
public class HbjhController {

	@Autowired
	BasicDao basicDao;

	@ResponseBody
	@RequestMapping(value="/basic_list")
	public Object list(Integer start, Integer limit){
		Map<String,Integer> parameters = new HashMap<>();
		parameters.put("start", start);
		parameters.put("limit", start+limit);
		Map<String,Object> result = new HashMap<String,Object>();
		List<Basic> basic = basicDao.selectAll(parameters);
		Integer total = basicDao.count();
		models  model = new models();
		model.setTotal(total);
		model.setModels(basic);
		result.put("basics", model);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/person")
	public Object person(Integer start, Integer limit){
		Map<String,Integer> parameters = new HashMap<>();
		parameters.put("start", start);
		parameters.put("limit", start+limit);
		Map<String,Object> result = new HashMap<String,Object>();
		List<Basic> basic = basicDao.selectAll(parameters);
		Integer total = basicDao.count();
		models  model = new models();
		model.setTotal(total);
		model.setModels(basic);
		result.put("basics", model);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/shareholder")
	public Object shareholder(Integer start, Integer limit){
		Map<String,Integer> parameters = new HashMap<>();
		parameters.put("start", start);
		parameters.put("limit", start+limit);
		Map<String,Object> result = new HashMap<String,Object>();
		List<Basic> basic = basicDao.selectAll(parameters);
		Integer total = basicDao.count();
		models  model = new models();
		model.setTotal(total);
		model.setModels(basic);
		result.put("basics", model);
		return result;
	}

}
