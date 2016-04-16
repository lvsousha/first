package cn.main;

import java.util.List;
import java.util.Map;

import cn.zcl.action.poi.POIUtil;

public class ReadXlsByPoi {

	public static void main(String[] args) {
        // 对读取Excel表格标题测试
		String sourceFileName = "d:\\workspace_NJ\\潜在客户分析数据.xls";
		POIUtil poiUtil = new POIUtil();
		List<String> titles = poiUtil.readExcelTitle(sourceFileName);
		System.out.println("获得Excel表格的标题:");
		for (String title : titles) {
		    System.out.print(title+"--");
		}

		// 对读取Excel表格内容测试
		Map<Integer, List<String>> map = poiUtil.readExcelContent(sourceFileName);
		System.out.println("获得Excel表格的内容:");
		for (int i = 1; i <= 3; i++) {
			for(String content : map.get(i)){
				System.out.print(content+"--");
			};
		}
    }

}
