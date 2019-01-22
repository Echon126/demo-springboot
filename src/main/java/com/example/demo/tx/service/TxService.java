package com.example.demo.tx.service;

import com.example.demo.tx.dao.TxMappper;
import com.example.demo.tx.data.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author admin
 * @date 2019-1-22 16:09
 */
@Service
public class TxService {

    @Autowired
    TxMappper txMappper;

    @Transactional
    public void createData() throws Exception {
        this.txMappper.saveM(Repository.createMajorTable());
        this.txMappper.saveA(Repository.createAuxiliaryTable());
    }
}
