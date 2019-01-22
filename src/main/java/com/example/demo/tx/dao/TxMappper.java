package com.example.demo.tx.dao;

import com.example.demo.tx.entity.AuxiliaryTable;
import com.example.demo.tx.entity.MajorTable;
import com.example.demo.tx.provider.TxProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author admin
 * @date 2019-1-22 16:49
 */
@Mapper
public interface TxMappper {

    @InsertProvider(type = TxProvider.class, method = "saveM")
    void saveM(MajorTable majorTable) throws Exception;


    @InsertProvider(type = TxProvider.class, method = "saveA")
    void saveA(AuxiliaryTable auxiliaryTable) throws Exception;
}
