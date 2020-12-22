package com.example.base.json;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonTest {
    public static void main(String[] args) throws IOException {


        //Map<Map>

        Map<Integer, Map<Integer, Map<Integer, String>>> datePriceType2 = ImmutableMap.of(2020, ImmutableMap.of(9, ImmutableMap.of(20, "换")));

        Map<Integer, Map<Integer, Map<Integer, String>>> yearPriceMap = JsonUtil.toObject(JsonUtil.toString(datePriceType2), HashMap.class);

        System.out.println(JsonUtil.toString(yearPriceMap));

        //List<Map>

        List<Map<Integer, Map<Integer, Map<Integer, String>>>> mapList = ImmutableList.of(datePriceType2, datePriceType2, datePriceType2);

        String s = JsonUtil.toString(mapList);


        ObjectMapper mapper = new ObjectMapper();

        List<HashMap> beanList = mapper.readValue(s, new TypeReference<List<HashMap>>() {});

        System.out.println(JsonUtil.toString(beanList));


        //List<List>

        List<List<String >> list=ImmutableList.of(ImmutableList.of("1"),ImmutableList.of("2"));
        ObjectMapper mapper2 = new ObjectMapper();

        List<List<String>>  listListType = mapper2.readValue(JsonUtil.toString(list), new TypeReference<List<List<String>>>() {});
        System.out.println(JsonUtil.toString(JsonUtil.toTypeReference(JsonUtil.toString(list),new TypeReference<List<List<String>>>() {})));


        ;
    }
}
