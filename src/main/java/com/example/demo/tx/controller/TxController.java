package com.example.demo.tx.controller;

import com.example.demo.tx.service.TxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 * @date 2019-1-23 16:22
 */
@RestController
public class TxController {

    @Autowired
    TxService txService;

    @GetMapping("/api/tx")
    public void testTx() throws Exception {
        txService.createData();
    }
}
