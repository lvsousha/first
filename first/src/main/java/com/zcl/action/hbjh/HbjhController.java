package com.zcl.action.hbjh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcl.dao.BasicDao;
import com.zcl.dao.PersonDao;
import com.zcl.dao.ShareholderDao;
import com.zcl.model.Basic;
import com.zcl.model.Person;
import com.zcl.model.Shareholder;

@Controller
@RequestMapping("/content/hbjh")
public class HbjhController {

	@Autowired
	BasicDao basicDao;
	@Autowired
	PersonDao personDao;
	@Autowired
	ShareholderDao shareholderDao;

	@ResponseBody
	@RequestMapping(value="/basic_list")
	public Object list(Integer start, Integer limit){
		Map<String,Integer> parameters = new HashMap<>();
		parameters.put("start", start);
		parameters.put("limit", start+limit);
		Map<String,Object> result = new HashMap<String,Object>();
		List<Basic> basics = basicDao.selectAll(parameters);
		Integer total = basicDao.count();
		models  model = new models();
		model.setTotal(total);
		model.setBasics(basics);
		result.put("model", model);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/person")
	public Object person(String orderno){
		Map<String,String> parameters = new HashMap<>();
		parameters.put("orderno", orderno);
		Map<String,Object> result = new HashMap<String,Object>();
		List<Person> persons = personDao.selectPersonByOrderno(parameters);
		Integer total = personDao.countPersonByOrderno(parameters);
		models  model = new models();
		model.setTotal(total);
		model.setPersons(persons);
		result.put("model", model);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/shareholder")
	public Object shareholder(String orderno){
		Map<String,String> parameters = new HashMap<>();
		parameters.put("orderno", orderno);
		Map<String,Object> result = new HashMap<String,Object>();
		List<Shareholder> shareholders = shareholderDao.selectShareholderByOrderno(parameters);
		Integer total = shareholderDao.countShareholderByOrderno(parameters);
		models  model = new models();
		model.setTotal(total);
		model.setShareholders(shareholders);
		result.put("model", model);
		return result;
	}

}
