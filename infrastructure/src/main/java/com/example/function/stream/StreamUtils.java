package com.example.function.stream;

import com.alibaba.fastjson.JSON;
import com.example.base.domain.UserInfo;
import com.google.common.collect.ImmutableList;


import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamUtils {
    public static void main(String[] args) {
        List<String> list = ImmutableList.of("d", "d", "a");
        System.out.println(JSON.toJSONString(distinctByKey(list, s -> s)));

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(0);
        userInfo.setUserName("小明");
        List<UserInfo> list2 = ImmutableList.of(userInfo, userInfo, userInfo);
        distinctByKey(list2,UserInfo::getUserName);
    }

    public static <T> List<T> distinctByKey(Collection<T> list, Function<? super T, ?> keyExtractor) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        return list.stream()
                .filter(Objects::nonNull)
                .filter(distinctByKey(keyExtractor))
                .collect(Collectors.toList());
    }

    /**
     * 去重 Predicate
     * 利用set add 相同参数时返回 false
     *
     * @param keyExtractor super表示元素下届  只能set无法get
     *                     https://www.zhihu.com/question/20400700
     *                     PECS（Producer Extends Consumer Super）原则
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey( Function<? super T, ?> keyExtractor) {
        final Set<Object> seen = new HashSet<>();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
