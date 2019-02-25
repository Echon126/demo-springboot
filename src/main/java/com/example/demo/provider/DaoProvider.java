package com.example.demo.provider;

import com.example.demo.entity.DataMap;

import java.text.MessageFormat;
import java.util.List;

/**
 * @author admin
 * @date 2019-2-25 14:49
 */
public class DaoProvider {
    public String insertImportExcel(List<DataMap> list) {
        StringBuilder sb = new StringBuilder();
        sb.append(" insert into movie ");
        sb.append(" (mid,fid) ");
        sb.append(" values ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].mid},#'{'list[{0}].fid})");
        for (int i = 0; i < list.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }

        return sb.toString();
    }
}












