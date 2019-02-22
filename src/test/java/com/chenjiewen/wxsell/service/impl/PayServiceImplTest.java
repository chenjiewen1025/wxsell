package com.chenjiewen.wxsell.service.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class PayServiceImplTest {

    @Test
    public void create() {
        System.out.println(test1()+"x");
    }

    private static int test1(){
        int temp=1;
        try {
            System.out.println(temp+"y");

        return ++temp;
    }
    catch (Exception e)
    {
        System.out.println(temp);
        return ++temp;
    }
    finally {
            ++temp;
            System.out.println(temp+"z");
        }


    }
}