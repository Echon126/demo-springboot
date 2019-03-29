package com.example.demo.utils.cas;

import java.util.concurrent.BlockingQueue;

/**
 * @author admin
 * @date 2019-3-19 11:28
 */
public class Seller implements Runnable {
    private BlockingQueue orderQueue;
    private boolean shutdownRequest = false;
    private static int id;

    public Seller(BlockingQueue orderQueue) {
        this.orderQueue = orderQueue;
    }

    @Override
    public void run() {
        while (shutdownRequest == false) {
            Integer quantity = (int) (Math.random() * 100);
            try {
                orderQueue.put(quantity);
                System.out.println("Sell order by" + Thread.currentThread().getName() + ": " + quantity);
            } catch (InterruptedException e) {
                e.printStackTrace();
                shutdownRequest = true;
            }
        }
    }
}
