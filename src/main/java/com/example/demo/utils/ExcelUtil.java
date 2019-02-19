package com.example.demo.utils;

import com.example.demo.entity.DataMap;
import com.example.demo.entity.ForerunnerConfig;
import com.example.demo.entity.ForerunnerField;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * @author admin
 * @date 2019-2-18 11:43
 */
public class ExcelUtil {
    private final static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    public static String exportExcelInDataMap(ForerunnerConfig forerunnerConfig, List<DataMap> list) {
        String url = forerunnerConfig.getClass().getClassLoader().getResource("").getPath();
        String title = forerunnerConfig.getName() + "--" + new Date().getTime() + ".xls";
        Map<String, ForerunnerField> fieldMap = forerunnerConfig.getFields();
        String realUrl = url + title;
        OutputStream out = null;
        long f = new Date().getTime();
        try {
            // 声明一个工作薄
            Workbook workbook = new HSSFWorkbook();
            // 生成一个表格
            Sheet sheet = workbook.createSheet(title);
            if (list == null || list.size() == 0) {
                String cellValue = "导出数据为空";
                out = getErrorExcel(realUrl, workbook, sheet, cellValue);
                return realUrl;
            }
            if (fieldMap == null) {
                String cellValue = "配置参数参数异常,请联系管理员!";
                out = getErrorExcel(realUrl, workbook, sheet, cellValue);
                return realUrl;
            }
            if (list.size() > 66535) {
                String cellValue = "最大导出数据量为66535条,请修改筛选查询条件!";
                out = getErrorExcel(realUrl, workbook, sheet, cellValue);
                return realUrl;
            }

            //标题的列map
            TreeMap<Integer, ForerunnerField> treeMap = new TreeMap<Integer, ForerunnerField>();
            //内容对应单元格map
            Map<String, Integer> hashMap = new HashMap<String, Integer>();
            //单元格的宽度
            List<Integer> exportFieldWidth = new ArrayList<Integer>();
            Iterator<Map.Entry<String, ForerunnerField>> iter = fieldMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, ForerunnerField> field = (Map.Entry<String, ForerunnerField>) iter.next();
                if (field.getValue().getExSort() == null) {
                    continue;
                }
                //添加标题的列宽
                treeMap.put(field.getValue().getExSort(), field.getValue());
                //内容对应map
                hashMap.put(field.getKey(), field.getValue().getExSort());
                // 添加到需要导出的字段的
                exportFieldWidth.add(15);
            }

            // 产生表格标题行
            int index = 0;
            Row row = sheet.createRow(index);
            Iterator<Map.Entry<Integer, ForerunnerField>> itTree = treeMap.entrySet().iterator();
            while (itTree.hasNext()) {
                Map.Entry<Integer, ForerunnerField> entryTree = (Map.Entry<Integer, ForerunnerField>) itTree.next();
                Cell cell = row.createCell(entryTree.getKey());
                RichTextString text = new HSSFRichTextString(entryTree.getValue().getExAlias() == null ? entryTree.getValue().getAlias() : entryTree.getValue().getExAlias());
                cell.setCellValue(text);
            }
            //设置每行的列宽
            for (int i = 0; i < exportFieldWidth.size(); i++) {
                //256=65280/255
                sheet.setColumnWidth(i, 256 * exportFieldWidth.get(i));
            }
            // 循环插入剩下的集合
            for (DataMap dm : list) {
                // 从第二行开始写，第一行是标题
                index++;
                row = sheet.createRow(index);
                Iterator<Map.Entry<String, Object>> it = dm.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
                    if (hashMap.containsKey(entry.getKey() + "")) {
                        Cell cell = row.createCell(hashMap.get(entry.getKey() + ""));
                        cell.setCellValue(entry.getValue().toString());
                    }

                }
            }
            out = new FileOutputStream(realUrl);
            workbook.write(out);
            out.flush();
            out.close();
            long e = new Date().getTime();
            System.out.println("共" + (e - f) + "毫秒完成");


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null)
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return realUrl;
    }


    private static OutputStream getErrorExcel(String readlUrl,
                                              Workbook workBook,
                                              Sheet sheet,
                                              String cellValue) throws IOException {
        OutputStream out;
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(cellValue);
        out = new FileOutputStream(readlUrl);
        workBook.write(out);
        out.flush();
        out.close();
        return out;

    }
}
















