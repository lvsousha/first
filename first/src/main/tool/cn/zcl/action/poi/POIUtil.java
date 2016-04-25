package cn.zcl.action.poi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class POIUtil {

	
	
	public void createExcel(String fileName){
		String outputFile = fileName; 
		try {
	            // 创建新的Excel 工作簿
	            HSSFWorkbook workbook = new HSSFWorkbook();
	            // 在Excel工作簿中建一工作表，其名为缺省值
	            // 如要新建一名为"效益指标"的工作表，其语句为：
	            // HSSFSheet sheet = workbook.createSheet("效益指标");
	            HSSFSheet sheet = workbook.createSheet("通过POI添加的工作表");
	            // 在索引0的位置创建行（最顶端的行）
	            HSSFRow row = sheet.createRow(0);
	            // 在索引0的位置创建单元格（左上端）
	            HSSFCell cell = row.createCell(0);
	            // 定义单元格为字符串类型
	            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	            // 设置该单元格字体格式
	            HSSFFont font = workbook.createFont();// 新建字体格式
	            font.setColor(HSSFFont.COLOR_RED);// 设置字体颜色为红色，注：HSSFFont中只有红色，其他颜色请用HSSFColor，下文中有举例
	            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 设置字体格式为粗体
	            font.setFontName("楷体");// 设置字体格式为楷体
	            HSSFCellStyle cellStyle = workbook.createCellStyle();// 新建单元格样式
	            cellStyle.setFont(font);// 将字体样式应用于单元格样式
	            cell.setCellStyle(cellStyle);// 将单元格样式应用于单元格
	            // 在单元格中输入一些内容
	            cell.setCellValue("增加值1");

	            // 设置列宽
	            sheet.setColumnWidth(1, 3766); // 第一个参数代表列id(从0开始),第2个参数代表宽度值 参考
	                                            // ："2012-08-10"的宽度为2500
	            // 在索引1的位置创建行（最顶端的第二行）
	            HSSFRow row2 = sheet.createRow(1);
	            // 在索引1的位置创建单元格（左上端第二行第二格）
	            HSSFCell cell2 = row2.createCell(1);
	            // 定义单元格为整数类型
	            cell2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	            // 设置该单元格字体格式(更多设置样式的方法请自行百度，参考地址：http://www.cnblogs.com/zhenmingliu/archive/2012/04/25/2469396.html)
	            HSSFFont font2 = workbook.createFont();// 新建字体格式
	            font2.setColor(HSSFColor.BLUE.index);// 设置字体颜色为蓝色，格式HSSFColor.XXX(颜色英文).index(转short类型)
	            font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);// 设置字体格式为粗体
	            font2.setItalic(true);// 设置字体格式为斜体 true为使用斜体，默认flase
	            font2.setFontHeightInPoints((short) 16);// 设置字体大小
	            font2.setFontName("华文彩云");// 设置字体格式为华文彩云
	            HSSFCellStyle cellStyle2 = workbook.createCellStyle();// 新建单元格样式
	            cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 设置单元格居中属性
	            cellStyle2.setFont(font2);// 将字体样式应用于单元格样式
	            cell2.setCellStyle(cellStyle2);// 将单元格样式应用于单元格
	            // 在单元格中输入一些内容
	            cell2.setCellValue("增加值2");

	            // 新建一输出文件流
	            FileOutputStream fOut = new FileOutputStream(outputFile);
	            // 把相应的Excel 工作簿存盘
	            workbook.write(fOut);
	            fOut.flush();
	            // 操作结束，关闭文件
	            fOut.close();
	            System.out.println("文件生成成功");
	        } catch (Exception e) {
	            System.out.println("文件生成失败： " + e);
	        }

	}
	
	/**
	 * 读取Excel标题头内容
	 * @param sourceFileName
	 * @return List 
	 */
    public List<String> readExcelTitle(String sourceFileName) {
    	POIFSFileSystem fs;
        HSSFWorkbook wb;
        HSSFSheet sheet;
        HSSFRow row;
        List<String> title = new ArrayList<>();
    	try {
        	InputStream is = new FileInputStream(sourceFileName);
        	fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
            sheet = wb.getSheetAt(0);
            row = sheet.getRow(0);       
            int colNum = row.getPhysicalNumberOfCells();// 标题总列数
            for (int i = 0; i < colNum; i++) {
                title.add(getCellFormatValue(row.getCell(i)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return title;
    }

    /**
     * 读取Excel数据内容
     * @param sourceFileName
     * @return Map 包含单元格数据内容的Map对象
     */
    public Map<Integer, List<String>> readExcelContent(String sourceFileName) {
    	POIFSFileSystem fs;
        HSSFWorkbook wb;
        HSSFSheet sheet;
        HSSFRow row;
        Map<Integer, List<String>> contents = new HashMap<>();
        try {
        	InputStream is = new FileInputStream(sourceFileName);
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
            sheet = wb.getSheetAt(0);    
            int rowNum = sheet.getLastRowNum();// 得到总行数
            row = sheet.getRow(0);
            int colNum = row.getPhysicalNumberOfCells();      
            for (int i = 1; i <= rowNum; i++) {// 正文内容应该从第二行开始,第一行为表头的标题
                List<String> content = new ArrayList<>();
            	row = sheet.getRow(i);
                for(int j=0;j<colNum;j++){
                	content.add(getCellFormatValue(row.getCell(j)).trim());
                }
                contents.put(i, content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contents;
    }

    /**
     * 根据HSSFCell类型设置数据
     * @param cell
     * @return
     */
    private String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {          
            switch (cell.getCellType()) {// 判断当前Cell的Type            
            case HSSFCell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
            case HSSFCell.CELL_TYPE_FORMULA: {               
                if (HSSFDateUtil.isCellDateFormatted(cell)) {// 判断当前的cell是否为Date
                    // 如果是Date类型则，转化为Data格式                   
                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                    //cellvalue = cell.getDateCellValue().toLocaleString();                    
                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellvalue = sdf.format(date);                    
                }else{ // 如果是纯数字                  
                    cellvalue = String.valueOf(cell.getNumericCellValue());// 取得当前Cell的数值
                }
                break;
            }           
            case HSSFCell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING                
                cellvalue = cell.getRichStringCellValue().getString();// 取得当前的Cell字符串
                break;           
            default:// 默认的Cell值
                cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }	
}
