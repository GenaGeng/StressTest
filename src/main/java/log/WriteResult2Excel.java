package log;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import static java.io.File.separator;

/**
 * @author GN
 * @description
 * @date 2020/10/24
 */
public class WriteResult2Excel {

    private static final int ROWS = 33;
    private static final String[] MEASURES = {"GenerateTime","ExecuteTime","FTime","FMeasure"};

    //记录生成时间
    public void writeResult(String measure,String excelName,String programName,String numOfTC,List<Integer> GT,List<Long> ET,List<Long> NUM){

        String path = "F:\\CMT\\result" + separator + excelName + ".xlsx";
        Workbook workbook = getworkBook(path);
        XSSFCellStyle cellStyle = getCellStyle(workbook);
        XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);

        //获得文件当前写入的行数
        int currentRows = sheet.getLastRowNum();

        if (currentRows != 0){
            currentRows++;
        }

        //写第一列
        if (currentRows<33) {
            for (int i = 0; i < ROWS; i++) {
                //设置列宽
                sheet.setColumnWidth(i, 5000);

                Row tempRow = sheet.createRow(currentRows + i);
                tempRow.setHeightInPoints(30);
                Cell tempCell = tempRow.createCell(0);
                if (i == 0) {
                    tempCell.setCellValue("Program " + programName + ", numberOfTC = " + String.valueOf(numOfTC));
                    tempCell.setCellStyle(cellStyle);
                } else if (i == 1) {
                    tempCell.setCellValue("Measure");
                    tempCell.setCellStyle(cellStyle);
                } else if (i == 2) {
                    tempCell.setCellValue("均值：");
                    tempCell.setCellStyle(cellStyle);
                } else {
                    tempCell.setCellValue("repeate-time = " + String.valueOf(i - 2));
                    tempCell.setCellStyle(cellStyle);
                }
            }
        }
        currentRows++;

        //第二行的第二列和第三列、第四列
        if (currentRows<33) {
            for (int i = 0; i < ROWS; i++) {
                Row tempRow = sheet.getRow(currentRows + i);
                if (i == 0) {
                    for (int j = 0; j < MEASURES.length; j++) {
                        Cell tempCell = tempRow.createCell(j + 1);
                        tempCell.setCellValue(MEASURES[j]);
                        tempCell.setCellStyle(cellStyle);
                    }
                }
            }
        }

        if (measure.equals("GT")){
            for (int i=3;i<ROWS;i++){
                Row tempRow = sheet.getRow(i);
                Cell tempCell = tempRow.createCell(1);
                tempCell.setCellValue(GT.get(i-3));
            }
        }
        else if (measure.equals("ET")){
            for (int i=3;i<ROWS;i++){
                Row tempRow = sheet.getRow(i);
                Cell tempCell = tempRow.createCell(2);
                tempCell.setCellValue(ET.get(i-3));
            }
        }else if (measure.equals("FTime")){
            for (int i=3;i<ROWS;i++){
                Row tempRow = sheet.getRow(i);
                Cell tempCell = tempRow.createCell(3);
                tempCell.setCellValue(GT.get(i-3)+ET.get(i-3));
            }
        }else if (measure.equals("FMeasure")){
            for (int i=3;i<ROWS;i++){
                Row tempRow = sheet.getRow(i);
                Cell tempCell = tempRow.createCell(4);
                tempCell.setCellValue(NUM.get(i-3));
            }
        }

        writeContent(workbook, path);
    }

    private Workbook getworkBook(String path){
        File file = new File(path);
        XSSFWorkbook workbook = null;
        FileInputStream fis = null;
        if (!file.exists()){
            workbook = new XSSFWorkbook();
            workbook.createSheet();
        }else {
            try{
                fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return workbook;
    }

    private void writeContent(Workbook workbook, String path){
        File file = new File(path);
        FileOutputStream fos = null;

        try{
            fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 设置单元格的格式
     * @param workbook
     * @return
     */
    private XSSFCellStyle getCellStyle(Workbook workbook){
        XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setFillForegroundColor((short)9);
        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setFontHeightInPoints((short) 9);
        font.setFontName(XSSFFont.DEFAULT_FONT_NAME);
        cellStyle.setFont(font);
        cellStyle.setBorderBottom(BorderStyle.THIN); // 下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);// 左边框
        cellStyle.setBorderTop(BorderStyle.THIN);// 上边框
        cellStyle.setBorderRight(BorderStyle.THIN);// 右边框
        return cellStyle;
    }


//    public static void main(String[] args){
//        List<Integer> ET = new ArrayList<>();
//        List<Integer> GT = new ArrayList<>();
//        List<Integer> NUM = new ArrayList<>();
//        for (int i=0;i<30;i++){
//            GT.add(i);
//            ET.add(i);
//            NUM.add(i);
//        }
//        WriteResult2Excel writeResult2Excel = new WriteResult2Excel();
//        writeResult2Excel.writeResult("GT","5TC4Account","Account",String.valueOf(5),GT,ET,NUM);
//        writeResult2Excel.writeResult("ET","5TC4Account","Account",String.valueOf(5),GT,ET,NUM);
//        writeResult2Excel.writeResult("FTime","5TC4Account","Account",String.valueOf(5),GT,ET,NUM);
//        writeResult2Excel.writeResult("FMeasure","5TC4Account","Account",String.valueOf(5),null,null,NUM);
//    }
}
