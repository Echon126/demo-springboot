package com.example.demo.tx.data;

import com.example.demo.tx.entity.AuxiliaryTable;
import com.example.demo.tx.entity.MajorTable;

/**
 * @author admin
 * @date 2019-1-22 16:20
 */
public class Repository {

    public static MajorTable createMajorTable() {
        MajorTable majorTable = new MajorTable();
        majorTable.setCity(001);
        majorTable.setName("zhangsan");
        majorTable.setSex("man");
        majorTable.setNotes("he is a student");
        return majorTable;
    }

    public static AuxiliaryTable createAuxiliaryTable(){
        AuxiliaryTable auxiliaryTable = new AuxiliaryTable();
        auxiliaryTable.setCity(null);
        auxiliaryTable.setCode(1);

        return auxiliaryTable;
    }
}
