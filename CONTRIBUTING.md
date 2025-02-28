# 贡献指南

感谢你考虑为 JSON Compare 项目做出贡献！

## 如何贡献

1. Fork 这个仓库
2. 创建你的特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交你的更改 (`git commit -m '添加一些特性'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启一个 Pull Request

## 开发环境设置

1. 克隆项目到本地：
```bash
git clone https://github.com/你的用户名/json-compare.git
cd json-compare
```

2. 安装依赖：
```bash
mvn install
```

3. 运行测试：
```bash
mvn test
```

## 代码风格

- 使用4个空格进行缩进
- 类名使用 UpperCamelCase
- 方法名和变量名使用 lowerCamelCase
- 常量使用 UPPER_SNAKE_CASE
- 所有的公共方法都应该有 JavaDoc 注释

## 提交信息规范

提交信息应该遵循以下格式：
```
<类型>: <描述>

[可选的正文]

[可选的脚注]
```

类型可以是：
- feat: 新功能
- fix: Bug修复
- docs: 文档更改
- style: 不影响代码含义的更改（空格、格式化等）
- refactor: 既不修复bug也不添加特性的代码更改
- perf: 改进性能的代码更改
- test: 添加或修改测试
- chore: 对构建过程或辅助工具的更改

例如：
```
feat: 添加忽略特定字段的功能

- 添加了配置选项来指定需要忽略的字段
- 更新了文档来反映新功能
```

## Pull Request 流程

1. 确保你的 PR 包含以下内容：
   - 清晰的描述
   - 必要的测试
   - 更新的文档
   - 如果添加了新功能，请包含示例

2. PR 会经过以下检查：
   - 代码审查
   - 自动化测试
   - 代码风格检查

3. 一旦所有检查通过并得到批准，你的代码将被合并到主分支

## 报告 Bug

1. 使用 Issue 模板创建新的 bug 报告
2. 包含以下信息：
   - 问题的详细描述
   - 重现步骤
   - 预期行为
   - 实际行为
   - 环境信息
   - 相关的日志输出
   - 可能的解决方案

## 提出新功能

1. 使用 Issue 模板创建新的功能请求
2. 描述新功能解决的问题
3. 提供使用场景
4. 如果可能，提供实现建议

## 行为准则

- 保持友善和专业
- 尊重其他贡献者
- 接受建设性的批评
- 专注于问题本身而不是个人

## 许可证

通过贡献你的代码，你同意你的贡献将在 MIT 许可证下发布。 