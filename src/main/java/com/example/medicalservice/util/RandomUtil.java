package com.example.medicalservice.util;

import java.util.Random;

public class RandomUtil {
    /**
     * 生成指定位数的随机数
     * @param length
     * @return
     */
    public static Integer getRandom(int length){
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return Integer.valueOf(val);
    }
}
