package org.relaxation.common.utills;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * GhPOIUtils class
 *
 * @date 2019-12-12
 * 类功能描述 (alt + enter -> add to custom tags)
 */
public class GhPOIUtils {

    private static final Logger logger= LoggerFactory.getLogger(GhPOIUtils.class);
    private final static String xls = "xls";
    private final static String xlsx = "xlsx";

     public static void checkFile(MultipartFile file) throws IOException{
         //判断文件是否存在
         if(null == file){
             logger.error("文件不存在！");
             throw new FileNotFoundException("文件不存在！");
         }
         //获得文件名
         String fileName = file.getOriginalFilename();
         //判断文件是否是excel文件
         if(!fileName.endsWith(xls) && !fileName.endsWith(xlsx)){
             logger.error(fileName + "不是excel文件");
             throw new IOException(fileName + "不是excel文件");
         }
     }

     public static Workbook getWorkBook(MultipartFile file) {
         //获得文件名
         String fileName = file.getOriginalFilename();
         //创建Workbook工作薄对象，表示整个excel
         Workbook workbook = null;
         try {
             //获取excel文件的io流
             InputStream is = file.getInputStream();
             //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if(fileName.endsWith(xls)){
                 //2003
                 workbook = new HSSFWorkbook(is);
             }else if(fileName.endsWith(xlsx)){
                 //2007
                 workbook = new XSSFWorkbook(is);
             }
         } catch (IOException e) {
             logger.info(e.getMessage());
         }
        return workbook;
     }

     public static String getCellValue(Cell cell){
         String cellValue = "";
         if(cell == null){
             return cellValue;
         }
         //把数字当成String来读，避免出现1读成1.0的情况
/*         if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
             cell.setCellType(Cell.CELL_TYPE_STRING);
         }*/
         //判断数据的类型
         switch (cell.getCellType()){
             case Cell.CELL_TYPE_NUMERIC: //数字
                if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                    SimpleDateFormat sdf = null;
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                    sdf = new SimpleDateFormat("HH:mm");
                    } else {// 日期
                    sdf = new SimpleDateFormat("yyyy/MM/dd");
                    }
                    Date date = cell.getDateCellValue();
                    cellValue = sdf.format(date);
                } else if (cell.getCellStyle().getDataFormat() == 58) {
                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    double value = cell.getNumericCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
                    cellValue = sdf.format(date);
                } else {
                    double value = cell.getNumericCellValue();
                    CellStyle style = cell.getCellStyle();
                    DecimalFormat format = new DecimalFormat();
                    String temp = style.getDataFormatString();
                    // 单元格设置成常规
                    if (temp.equals("General")) {
                    format.applyPattern("#");
                    }
                    cellValue = format.format(value);
                }
/*                 cellValue = String.valueOf(cell.getNumericCellValue());*/
                 break;
             case Cell.CELL_TYPE_STRING: //字符串
                 cellValue = String.valueOf(cell.getStringCellValue());
                 break;
             case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                 break;
             case Cell.CELL_TYPE_FORMULA: //公式
                 cellValue = String.valueOf(cell.getCellFormula());
                 break;
             case Cell.CELL_TYPE_BLANK: //空值
                 cellValue = "";
                 break;
             case Cell.CELL_TYPE_ERROR: //故障
                 cellValue = "非法字符";
                 break;
             default:
                 cellValue = "未知类型";
                 break;
         }
         cellValue = cellValue.trim();
         if(cellValue.startsWith("'")){
             cellValue = cellValue.substring(1,cellValue.length());
         }
         if(cellValue.startsWith("'")){
             cellValue = cellValue.substring(1,cellValue.length());
         }
         return cellValue;
     }

     /**
      * 读入excel文件，解析后返回
      * @param file
      */
     public static List<String[]> readExcel(MultipartFile file) throws IOException{
         //检查文件
         checkFile(file);
         //获得Workbook工作薄对象
         Workbook workbook = getWorkBook(file);
         //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
         List<String[]> list = new ArrayList<String[]>();
         if(workbook != null){
             //for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
                 //获得当前sheet工作表
                 Sheet sheet = workbook.getSheetAt(0);
/*                 if(sheet == null){
                     continue;
                 }*/
                 //获得当前sheet的开始行
                 int firstRowNum  = sheet.getFirstRowNum();
                 //获得当前sheet的结束行
                 int lastRowNum = sheet.getLastRowNum();
                 //行的开始列
                 int firstCellNum=0;
                 //行的列数
                 int lastCellNum=0;
                 //循环所有行
                 for(int rowNum = firstRowNum;rowNum <= lastRowNum;rowNum++){
                     //获得当前行
                     Row row = sheet.getRow(rowNum);
                     if(row == null){
                         continue;
                     }
                     if(rowNum == firstRowNum){
                         firstCellNum = row.getFirstCellNum();

                         lastCellNum = row.getPhysicalNumberOfCells();
                     }

                     String[] cells = new String[lastCellNum+1];
                     //循环当前行
                     for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){
                         Cell cell = row.getCell(cellNum);
                         cells[cellNum] = getCellValue(cell);
                     }
                     list.add(cells);
                 }
             //}
            workbook.close();
         }
         return list;
     }

}
