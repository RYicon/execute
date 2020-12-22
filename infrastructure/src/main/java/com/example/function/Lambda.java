package com.example.function;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Lambda {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        System.out.println(JSON.toJSONString(getFunction().apply(integers)));;

        List<List<String>>list=Arrays.asList(Arrays.asList("111"),Arrays.asList("222"),Arrays.asList("333"));
        Optional<List<String>> reduce = list.stream().reduce((a, b) -> {
            ArrayList<String> objects = Lists.newArrayList();
            objects.addAll(a);
            objects.addAll(b);
            return objects;
        });
        System.out.println(JSON.toJSONString(reduce.get()));

        list=null;
        List<List<String>> list1 = Optional.ofNullable(list).orElse(null);
        System.out.println(list1);
    }

    static Function<List<Integer>, Object> getFunction() {
        return integer->integer;
    }
}
