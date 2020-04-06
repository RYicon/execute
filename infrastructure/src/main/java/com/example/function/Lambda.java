package com.example.function;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Lambda {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        System.out.println(JSON.toJSONString(getFunction().apply(integers)));;
        System.out.println();
    }

    static Function<List<Integer>, Object> getFunction() {
        return integer->integer;
    }
}
