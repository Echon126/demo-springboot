package com.example.demo.controller;

import com.example.demo.utils.DownFile;
import com.example.demo.utils.ExportMemberVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author admin
 * @date 2019-2-12 15:09
 */
@Controller
public class DownFileController {
    @RequestMapping(value = "/down", method = RequestMethod.GET)
    public ModelAndView download(){

        List<ExportMemberVo> list = new ArrayList<ExportMemberVo>();
        for (int i = 0; i < 5; i++) {
            ExportMemberVo exportMemberVo = new ExportMemberVo();
            exportMemberVo.setName("Kent" + i);
            @SuppressWarnings("unchecked")
            int gender = ThreadLocalRandom.current().nextInt(0, 2);
            exportMemberVo.setGender(gender);
            exportMemberVo.setPhone("182xxxxxxxx");
            exportMemberVo.setBankNo("建设银行");
            list.add(exportMemberVo);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("members", list);
        DownFile excelView = new DownFile();
        excelView.setFileName("魅力城市xxxxxx");
        return new ModelAndView(excelView, map);
    }
}
