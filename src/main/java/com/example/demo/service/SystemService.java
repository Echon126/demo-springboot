package com.example.demo.service;

import com.example.demo.entity.DataMap;
import com.example.demo.entity.ForerunnerCache;
import com.example.demo.mapper.SystemMapper;
import com.example.demo.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
