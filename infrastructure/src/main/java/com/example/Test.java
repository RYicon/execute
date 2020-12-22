package com.example;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSON;
import com.example.base.domain.UserInfo;
import com.example.base.json.JsonUtil;
import com.example.base.json.JsonUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Test {

//    public static void main(String[] args) {
//        List<Integer> a = ImmutableList.of(1, 2);
//        for (int i = 0; i < a.size(); i++) {
//            a.get(i);
//            System.out.println(i);
//        }
//
//
//
//        List<String> aa=new ArrayList<String>();
//        aa.add("111");
//        aa.clear();
//        System.out.println("清除后集合"+JsonUtil.toString(aa));
//
//        String str = "1";
//        Integer.valueOf(str);
//        Integer.parseInt(str);
//
//        System.out.println();
//        for (int i = 0; i < 8; i++) {
//            System.out.println(i + "|" + (i + 1));
//        }
//
//
//
//        String date="00:00";
//
//        LocalDateTime parse = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("HH:mm"));
//
//
//    }


    public static void main(String[] args) {
        HashMap<Long, String> map1 = Maps.newHashMap();
        map1.put(1L, "1");
        HashMap<Long, String> map2 = Maps.newHashMap();
        map2.put(1L, "2");
        HashSet<Long> keySet =new HashSet<>();
        keySet.addAll(map1.keySet());
        keySet.addAll(map2.keySet());
        System.out.println(keySet);
        UserInfo userInfo=null;
        Boolean aBoolean = entitySync(userInfo);
        System.out.println(JSON.toJSONString(userInfo));

        UserInfo userInfo1 = new UserInfo();
        userInfo1.setUserId(111);
        userInfo1.setUserName("111");
        userInfo1.setIsMan(true);

        System.out.println(JsonUtil.toJson(JsonUtils.parseObject(JsonUtils.toJson(userInfo1),UserInfo.class)));

    }


    public  static  Boolean entitySync(UserInfo userInfo){

        userInfo= new UserInfo();
        userInfo.setUserId(1111);
        userInfo.setUserName("111");
        return Boolean.FALSE;
    }
}
