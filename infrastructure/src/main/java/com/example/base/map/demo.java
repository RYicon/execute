package com.example.base.map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class demo {
    public static void main(String[] args) {
        Map<Long, List<String>> map = Maps.newHashMap();
        Map<Long, List<String>> map1 = Maps.newConcurrentMap();
        map1.put(1L, Arrays.asList("a"));
        map.putAll(map1);

        Map<Long, List<String>> map2 = Maps.newConcurrentMap();
        map2.put(1L, Arrays.asList("b"));
        map.putAll(map2);
        System.out.println(JSON.toJSONString(map));


        HashBasedTable<String, String, String> result = HashBasedTable.create();
        result.put("a", "b", "c");
        result.put("d", "e", "f");
        String value = result.get("d", "e");
        Map<String, String> rowGetKv = result.row("a");
        Map<String, String> columnGetKv = result.column("b");
        Map<String, Map<String, String>> columnMap = result.columnMap();
        Map<String, Map<String, String>> rowMap = result.rowMap();
        System.out.println("value结果为---" + value);
        System.out.println("rowGetKv结果为---" + JSON.toJSONString(rowGetKv));
        System.out.println("columnGetKv结果为---" + columnGetKv);
        System.out.println("columnMap结果为---" + columnMap);
        System.out.println("rowMap结果为---" + rowMap);

        System.out.println(result.row("c"));;


//        value结果为---f
//        rowGetKv结果为---{b=c}
//        columnGetKv结果为---{a=c}
//        columnMap结果为---{b={a=c}, e={d=f}}
//        rowMap结果为---{a={b=c}, d={e=f}}

    }
}
