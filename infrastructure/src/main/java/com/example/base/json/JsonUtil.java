package com.example.base.json;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bl_xu
 * @date 2018-06-06
 */
public final class JsonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonUtil() {
        // 工具类不可实例化
    }

    static {
        // 设计转换器转化规则-空字符串转换为NULL
        MAPPER.configure(
                DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,
                true);

        // 忽略不识别字段
        MAPPER.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * java对象转换成json字符串
     *
     * @param object 对象
     * @return json字符串
     */
    public static String toJson(Object object) {
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
     * 将json字符串转换成java对象
     *
     * @param jsonStr     json字符串
     * @param objectClass 指定的对象类
     * @return 指定的对象
     */
    public static <T> T toObject(String jsonStr, Class<T> objectClass) throws IOException {

        return MAPPER.readValue(jsonStr, objectClass);

    }

    /**
     * json 转 List<T>
     */
    public static <T> List<T> json2List(String draft, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = getCollectionType(clazz);
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(draft, javaType);
        } catch (IOException e) {

        }
        return null;
    }

    private static JavaType getCollectionType(Class<?>... elementClasses) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.getTypeFactory().constructParametricType(ArrayList.class, elementClasses);
    }

    private static ObjectMapper mapper = new ObjectMapper();

    public static String toString(Object obj) {
        String json = null;
        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {

        }
        return json;
    }


    public static <T> T toTypeReference(String jsonStr, TypeReference typeReference) {
        try {
            return mapper.readValue(jsonStr, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
