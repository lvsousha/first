package com.stone.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stone.common.Tree;
import com.zcl.dao.MenuDao;
import com.zcl.model.Menu;

@Controller
@RequestMapping("/content/menu")
public class MenuController {

	@Autowired
	MenuDao menuDao;


	@RequestMapping(value="/getTree", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Object getTree(){
		Map<String,Object> result = new HashMap<String,Object>();
		List<Menu> menus = menuDao.selectAll();
		List<Tree> children = new ArrayList<Tree>();
		for(Menu m : menus){
			Tree leaf = Tree.leaf(m.getMenuname(), m);
			children.add(leaf);
		}
		result.put("children", children);
		return result;
	}


}
