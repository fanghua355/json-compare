package com.example.jsoncompare.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.example.jsoncompare.model.CompareResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

@Slf4j
@Component
public class JsonComparator {
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 比较两个JSON字符串
     * @param json1 第一个JSON字符串
     * @param json2 第二个JSON字符串
     * @return 比较结果
     */
    public CompareResult compareJson(String json1, String json2) {
        try {
            log.debug("开始解析JSON字符串");
            JsonNode node1 = objectMapper.readTree(json1);
            JsonNode node2 = objectMapper.readTree(json2);
            
            CompareResult result = new CompareResult();
            log.debug("开始比较JSON节点");
            compareNodes("", node1, node2, result);
            return result;
        } catch (Exception e) {
            log.error("JSON比较失败", e);
            throw new RuntimeException("JSON比较失败", e);
        }
    }

    /**
     * 使用反射比较两个对象
     * @param obj1 第一个对象
     * @param obj2 第二个对象
     * @return 比较结果
     */
    public CompareResult compareObjects(Object obj1, Object obj2) {
        log.debug("开始比较对象");
        CompareResult result = new CompareResult();
        if (obj1 == null || obj2 == null) {
            log.debug("对象为null: obj1={}, obj2={}", obj1, obj2);
            result.addDifference("", "一个对象为null", String.valueOf(obj1), String.valueOf(obj2));
            return result;
        }

        if (!obj1.getClass().equals(obj2.getClass())) {
            log.debug("对象类型不同: obj1={}, obj2={}", obj1.getClass().getName(), obj2.getClass().getName());
            result.addDifference("", "对象类型不同", obj1.getClass().getName(), obj2.getClass().getName());
            return result;
        }

        compareObjectsUsingReflection(obj1, obj2, "", result);
        return result;
    }

    private void compareObjectsUsingReflection(Object obj1, Object obj2, String path, CompareResult result) {
        Class<?> clazz = obj1.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value1 = field.get(obj1);
                Object value2 = field.get(obj2);

                String currentPath = path.isEmpty() ? field.getName() : path + "." + field.getName();

                if (!Objects.equals(value1, value2)) {
                    if (value1 != null && value2 != null && !field.getType().isPrimitive() 
                            && !field.getType().equals(String.class)) {
                        log.debug("比较复杂对象字段: {}", currentPath);
                        compareObjectsUsingReflection(value1, value2, currentPath, result);
                    } else {
                        log.debug("发现字段差异: {}，值1={}，值2={}", currentPath, value1, value2);
                        result.addDifference(currentPath, "值不同", 
                            String.valueOf(value1), String.valueOf(value2));
                    }
                }
            } catch (IllegalAccessException e) {
                log.error("反射比较失败: {}", field.getName(), e);
                throw new RuntimeException("反射比较失败", e);
            }
        }
    }

    private void compareNodes(String path, JsonNode node1, JsonNode node2, CompareResult result) {
        if (node1.getNodeType() != node2.getNodeType()) {
            result.addDifference(path, "JSON节点类型不同", 
                node1.getNodeType().toString(), node2.getNodeType().toString());
            return;
        }

        if (node1.isObject()) {
            compareObjectNodes(path, (ObjectNode) node1, (ObjectNode) node2, result);
        } else if (node1.isArray()) {
            compareArrayNodes(path, node1, node2, result);
        } else if (!node1.equals(node2)) {
            result.addDifference(path, "值不同", node1.asText(), node2.asText());
        }
    }

    private void compareObjectNodes(String path, ObjectNode node1, ObjectNode node2, CompareResult result) {
        Set<String> allFields = new HashSet<>();
        node1.fieldNames().forEachRemaining(allFields::add);
        node2.fieldNames().forEachRemaining(allFields::add);

        for (String fieldName : allFields) {
            String currentPath = path.isEmpty() ? fieldName : path + "." + fieldName;
            JsonNode child1 = node1.get(fieldName);
            JsonNode child2 = node2.get(fieldName);

            if (child1 == null) {
                result.addDifference(currentPath, "字段在第一个JSON中不存在", "null", child2.toString());
            } else if (child2 == null) {
                result.addDifference(currentPath, "字段在第二个JSON中不存在", child1.toString(), "null");
            } else {
                compareNodes(currentPath, child1, child2, result);
            }
        }
    }

    private void compareArrayNodes(String path, JsonNode array1, JsonNode array2, CompareResult result) {
        int size1 = array1.size();
        int size2 = array2.size();
        
        if (size1 != size2) {
            result.addDifference(path, "数组长度不同", String.valueOf(size1), String.valueOf(size2));
        }

        int minSize = Math.min(size1, size2);
        for (int i = 0; i < minSize; i++) {
            compareNodes(path + "[" + i + "]", array1.get(i), array2.get(i), result);
        }
    }
} 