package com.example.demo.utils.cas;

import java.util.concurrent.BlockingQueue;

/**
 * @author admin
 * @date 2019-3-19 11:32
 */
public class Buyer implements Runnable {
    private BlockingQueue orderQueue;
    private boolean shutdownRequest = false;

    public Buyer(BlockingQueue orderQueue) {
        this.orderQueue = orderQueue;
    }

    @Override
    public void run() {
        while (shutdownRequest == false) {
            try {
                Integer quantity = (Integer) orderQueue.take();
                System.out.println("Buy order by " + Thread.currentThread().getName() + ": " + quantity);
            } catch (InterruptedException e) {
                e.printStackTrace();
                shutdownRequest = true;
            }
        }
    }
}
