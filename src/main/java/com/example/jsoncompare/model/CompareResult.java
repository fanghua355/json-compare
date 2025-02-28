package com.example.jsoncompare.model;

import java.util.ArrayList;
import java.util.List;

public class CompareResult {
    private List<Difference> differences;
    private boolean isEqual;

    public CompareResult() {
        this.differences = new ArrayList<>();
        this.isEqual = true;
    }

    public void addDifference(String path, String type, String value1, String value2) {
        differences.add(new Difference(path, type, value1, value2));
        this.isEqual = false;
    }

    public List<Difference> getDifferences() {
        return differences;
    }

    public boolean isEqual() {
        return isEqual;
    }

    public static class Difference {
        private String path;
        private String type;
        private String value1;
        private String value2;

        public Difference(String path, String type, String value1, String value2) {
            this.path = path;
            this.type = type;
            this.value1 = value1;
            this.value2 = value2;
        }

        public String getPath() {
            return path;
        }

        public String getType() {
            return type;
        }

        public String getValue1() {
            return value1;
        }

        public String getValue2() {
            return value2;
        }

        @Override
        public String toString() {
            return String.format("路径: %s, 差异类型: %s, 值1: %s, 值2: %s", 
                path, type, value1, value2);
        }
    }
} 