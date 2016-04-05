package com.zcl.action.hbjh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stone.model.User;
import com.zcl.dao.BasicDao;
import com.zcl.model.Basic;

@Controller
@RequestMapping("/content/hbjh")
public class HbjhController {

	@Autowired
	BasicDao basicDao;

	@ResponseBody
	@RequestMapping(value="/basic_list")
	public Object list(User user){
		Map<String,Object> result = new HashMap<String,Object>();
		List<Basic> basics = basicDao.selectAll();
		result.put("basics", basics);
		return result;
	}

}
