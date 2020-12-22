package com.example.base.json;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.ConfigFeature;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * json 操作工具类，封装一层，也方便替换底层实现，统一用jackson替换fastjson
 *
 * @author wj.weng
 * @date 2018/12/20
 */
public class JsonUtils {


    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonUtils() {
        // 工具类不可实例化
    }

    static {
        // 设计转换器转化规则-空字符串转换为NULL
        MAPPER.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

        // 忽略不识别字段
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 大小写不明感
        MAPPER.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES ,true);

        //将json中的浮点数解析成BigDecimal对象
        MAPPER.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);

    }

    /**
     * java对象转换成json字符串
     *
     * @param object 对象
     * @return json字符串
     */
    public static String toJson(Object object) {
        if(object == null){
            return null;
        }
        if (object instanceof String) {
            return object.toString();
        }
        try {
            return MAPPER.writeValueAsString(object);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * java对象转换成json字符串
     *
     * @param object 对象
     * @return json字符串
     */
    public static String toJsonString(Object object) {
        return toJson(object);
    }

    /**
     * java对象转换成json字符串
     *
     * @param object 对象
     * @return json字符串
     */
    public static String toJSONString(Object object) {
        return toJson(object);
    }

    /**
     * 重复，历史兼容，以后用toJson方法
     * @param obj 对象
     * @return json字符串
     */
    public static String toString(Object obj) {
        return toJson(obj);
    }

    /**
     * 将json字符串转换成java对象
     *
     * @param jsonStr     json字符串
     * @param objectClass 对象类
     * @return 对象
     */
    public static <T> T toObject(String jsonStr, Class<T> objectClass) {
        try {
            return MAPPER.readValue(jsonStr, objectClass);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 将json字符串转换为java对象
     * @param jsonStr   json字符串
     * @param typeReference
     * @return
     */
    public static <T> T toObject(String jsonStr, TypeReference<T> typeReference) {
        try {
            return MAPPER.readValue(jsonStr, typeReference);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     *
     * @param jsonStr   json字符串
     * @param type      类型参数Type
     * @return
     */
    public static <T> T toObject(String jsonStr, Type type){
        JavaType javaType = MAPPER.getTypeFactory().constructType(type);
        try {
            return MAPPER.readValue(jsonStr, javaType);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 将json字符串转换成java对象
     *
     * @param jsonStr     json字符串
     * @param objectClass 对象类
     * @return 对象
     */
    public static <T> T parseObject(String jsonStr, Class<T> objectClass) {
        try {
            return MAPPER.readValue(jsonStr, objectClass);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * json 转 List<T>
     */
    public static <T> List<T> json2List(String draft, Class<T> clazz) {
        JavaType javaType = getCollectionType(clazz);
        try {
            return MAPPER.readValue(draft, javaType);
        } catch (IOException e) {

        }
        return null;
    }

    /**
     * 只适用于通配符可变参数，使用时需要手动指定返回结果类型
     */
    public static <T> List<T> jsonTwoList(String draft, Class<?>... elementClasses) {
        JavaType javaType = getCollectionType(elementClasses);
        try {
            return MAPPER.readValue(draft, javaType);
        } catch (IOException e) {

        }
        return null;
    }

    /**
     * json 转 List<T>
     */
    public static <T> List<T> json2List(String draft, Class<T> clazz, Map<ConfigFeature, Boolean> configMap) {
        JavaType javaType = getCollectionType(clazz);
        try {
            if (configMap != null && configMap.size() > 0) {
                configMap.forEach((k, v) -> {
                    if (k instanceof MapperFeature) {
                        MAPPER.configure((MapperFeature) k, v);
                        return;
                    }
                    if (k instanceof DeserializationFeature) {
                        MAPPER.configure((DeserializationFeature) k, v);
                        return;
                    }
                    if (k instanceof SerializationFeature) {
                        MAPPER.configure((SerializationFeature) k, v);
                        return;
                    }
                });
            }
            return MAPPER.readValue(draft, javaType);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 转换成列表
     */
    public static <T> List<T> parseToList(String jsonStr, Class<T> objClass) {
        return json2List(jsonStr, objClass);
    }

    private static JavaType getCollectionType(Class<?>... elementClasses) {
        return MAPPER.getTypeFactory().constructParametricType(ArrayList.class, elementClasses);
    }


}
