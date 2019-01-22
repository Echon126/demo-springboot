package com.example.demo.tx.provider;

import com.example.demo.tx.entity.AuxiliaryTable;
import com.example.demo.tx.entity.MajorTable;
import org.apache.ibatis.jdbc.SQL;


/**
 * @author admin
 * @date 2019-1-22 16:41
 */
public class TxProvider {

    public String saveM(final MajorTable majorTable) {
        return new SQL() {{
            INSERT_INTO("t0001")
                    .INTO_COLUMNS("name,sex,city,notes")
                    .INTO_VALUES("#{name},#{sex},#{city},#{notes}");
        }}.toString();
    }



    public String saveA(final AuxiliaryTable auxiliaryTable) {
        return new SQL() {{
            INSERT_INTO("t0002")
                    .INTO_COLUMNS("city,code")
                    .INTO_VALUES("#{city},#{code}");
        }}.toString();
    }
}
