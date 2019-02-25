package com.example.demo.service;

import com.example.demo.entity.DataMap;
import com.example.demo.entity.ForerunnerCache;
import com.example.demo.mapper.SystemMapper;
import com.example.demo.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author admin
 * @date 2019-2-18 14:27
 */
@Service
public class SystemService {
    @Autowired
    SystemMapper systemMapper;

    public String systemUser() {
        List<DataMap> list = this.systemMapper.systemUser();
        String str = ExcelUtil.exportExcelInDataMap(ForerunnerCache.getConfig("systemUser"), list);
        return str;
    }

    public List<Map<String, Object>> systemUserData() {
        return this.systemMapper.systemUserData();
    }

    public String systemUserExcel(DataMap ddmm) throws Exception {
        List<DataMap> list = ExcelUtil.importExcel(ddmm, ForerunnerCache.getConfig("deleteBymidAndfid").getFields());
        List errorlist = new ArrayList();
        for (DataMap map : list) {
            //TODO 数据校验可以抽离，创建相应接口或者抽象类，
            if (null != map.get("mid") && !"".equals(map.get("mid").toString())) {
                errorlist.add(map.getValue("rowIndex"));
                System.out.println("校验错误信息");
                continue;

            }
            //TODO 数据库入库可以批量插入，值提交一次事务，提高入口效率。
            System.out.println("----------开始插入数据---------");
            this.systemMapper.insetMovie(map);
        }
        return "";
    }


}
