package com.example.demo.utils;

import java.math.BigDecimal;

/**
 * @author admin
 * @date 2019-3-18 15:42
 */
public class MathUtils<T extends Number> {

    public static <T> BigDecimal convertTime(T originalData){
        return convertTime(originalData, 3);
    }

    public static <T> BigDecimal convertTime(T originalData, int power){
        BigDecimal divisor = new BigDecimal(Math.pow(10, power));
        BigDecimal responseTimeBigDecimal = new BigDecimal(originalData.toString()).divide(divisor);
        responseTimeBigDecimal = responseTimeBigDecimal.setScale(power, BigDecimal.ROUND_HALF_UP);
        return responseTimeBigDecimal;
    }
}
