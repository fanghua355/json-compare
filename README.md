# JSON比较工具

这是一个基于Spring Boot的JSON比较工具，可以比较两个JSON字符串的差异，并详细指出不同之处。

## 功能特点

- 支持复杂的JSON结构比较
- 使用Java反射机制进行对象比较
- 详细的差异报告
- RESTful API接口
- 完整的日志记录
- 支持中文

## 技术栈

- Spring Boot 2.7.9
- Jackson
- Logback
- Lombok

## 快速开始

### 环境要求

- JDK 1.8+
- Maven 3.6+

### 构建和运行

1. 克隆项目
```bash
git clone https://github.com/你的用户名/json-compare.git
cd json-compare
```

2. 构建项目
```bash
mvn clean install
```

3. 运行项目
```bash
mvn spring-boot:run
```

### API使用

发送POST请求到 `http://localhost:8080/api/json/compare`

请求体格式：
```json
{
    "json1": "第一个JSON字符串",
    "json2": "第二个JSON字符串"
}
```

响应示例：
```json
{
    "differences": [
        {
            "path": "name",
            "type": "值不同",
            "value1": "张三",
            "value2": "李四"
        }
    ],
    "equal": false
}
```

## 配置说明

### 日志配置

日志配置文件位于 `src/main/resources/logback-spring.xml`

- 默认日志级别：INFO
- 应用日志级别：DEBUG
- 日志存储位置：./logs/
- 日志保留时间：30天

## 贡献指南

欢迎提交 Issue 和 Pull Request

## 许可证

MIT License 