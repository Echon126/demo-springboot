package com.example.demo.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsxStreamingView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @author admin
 * @date 2019-2-12 15:01
 */
public class DownFile extends AbstractXlsxStreamingView {
    private String fileName;
    private String[] titles;

    //TODO 需重新进行封装处理，形成统一的方法;
    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String excelName = fileName + ".xls";
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(excelName, "utf-8"));
        response.setContentType("application/ms-excel; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        @SuppressWarnings("unchecked")
        List<ExportMemberVo> list = (List<ExportMemberVo>) map.get("members");
        Sheet sheet = workbook.createSheet("User Detail");
        sheet.setDefaultColumnWidth(30);
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern((short) 1);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        Row header = sheet.createRow(0);

        for (int i = 0; i < titles.length; i++) {
            header.createCell(i).setCellValue(titles[i]);
            header.getCell(i).setCellStyle(style);
        }

        int rowCount = 1;
        for (ExportMemberVo user : list) {
            Row userRow = sheet.createRow(rowCount++);
            userRow.createCell(0).setCellValue(user.getName());
            userRow.createCell(1).setCellValue(user.getGender());
            userRow.createCell(2).setCellValue(user.getPhone());
            userRow.createCell(3).setCellValue(user.getIdCard());
            userRow.createCell(4).setCellValue(user.getBankNo());
        }

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }
}
