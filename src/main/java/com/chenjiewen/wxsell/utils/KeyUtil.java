package com.chenjiewen.wxsell.utils;

import java.util.Random;

public class KeyUtil {

    //生成唯一主键
    public static synchronized String genUniqueKey()
    {
        Random random = new Random();

        Integer number = random.nextInt(9000000)+1000000;

        return System.currentTimeMillis()+String.valueOf(number);
    }
}
