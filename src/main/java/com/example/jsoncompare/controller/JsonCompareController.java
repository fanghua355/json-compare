package com.example.jsoncompare.controller;

import com.example.jsoncompare.model.CompareResult;
import com.example.jsoncompare.utils.JsonComparator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/json")
public class JsonCompareController {

    @Autowired
    private JsonComparator jsonComparator;

    @PostMapping("/compare")
    public CompareResult compareJson(@RequestBody JsonCompareRequest request) {
        log.info("接收到JSON比较请求");
        log.debug("JSON1: {}", request.getJson1());
        log.debug("JSON2: {}", request.getJson2());
        
        CompareResult result = jsonComparator.compareJson(request.getJson1(), request.getJson2());
        
        log.info("JSON比较完成，发现 {} 处差异", result.getDifferences().size());
        if (!result.isEqual()) {
            log.debug("差异详情: {}", result.getDifferences());
        }
        
        return result;
    }

    public static class JsonCompareRequest {
        private String json1;
        private String json2;

        public String getJson1() {
            return json1;
        }

        public void setJson1(String json1) {
            this.json1 = json1;
        }

        public String getJson2() {
            return json2;
        }

        public void setJson2(String json2) {
            this.json2 = json2;
        }
    }
} 