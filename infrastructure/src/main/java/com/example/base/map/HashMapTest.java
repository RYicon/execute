package com.example.base.map;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args) {
        Map<Integer ,String> map=new HashMap<>(2,1000);

        for (int i = 0; i < 100; i++) {
            map.putIfAbsent(i,String.valueOf(i));
        }


    }

}
