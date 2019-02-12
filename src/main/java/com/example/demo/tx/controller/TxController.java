package com.example.demo.tx.controller;

import com.example.demo.tx.service.TxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 * @date 2019-1-23 16:22
 */
@RestController
public class TxController {
    private static final Logger logger = LoggerFactory.getLogger(TxController.class);

    @Autowired
    TxService txService;

    @GetMapping("/api/tx")
    public String testTx() {
        try {
            txService.createData();
        } catch (Exception e) {
            logger.info("创建数据仓库时发生异常................." + e.getMessage());
            e.printStackTrace();
        }
        return "执行成功";
    }
}
