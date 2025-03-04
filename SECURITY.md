# 安全策略

## 支持的版本

目前正在接收安全更新的版本：

| 版本  | 支持状态            |
| ----- | ------------------ |
| 1.0.x | :white_check_mark: |
| < 1.0 | :x:               |

## 报告漏洞

如果你发现了安全漏洞，请不要创建公开的 Issue。相反，请：

1. 发送邮件到 [henryfang0822@gmail.com]
2. 描述问题和重现步骤
3. 我们会在24小时内回复

### 邮件内容应包括

- 清晰的问题描述
- 受影响的版本
- 重现步骤
- 可能的解决方案（如果有）
- 你的联系方式

## 安全最佳实践

使用本项目时，请注意以下安全实践：

1. 保持依赖项更新
```bash
mvn versions:display-dependency-updates
```

2. 定期检查安全公告
- GitHub Security Advisories
- CVE 数据库

3. 数据处理安全
- 不要在日志中记录敏感的 JSON 数据
- 使用 HTTPS 进行 API 调用
- 实施适当的访问控制

4. 配置安全
- 不要在代码中硬编码敏感信息
- 使用环境变量或安全的配置管理
- 限制日志中的敏感信息

## 漏洞响应流程

1. 确认
   - 24小时内确认收到报告
   - 评估影响范围

2. 修复
   - 开发修复方案
   - 在私有分支测试

3. 发布
   - 发布安全更新
   - 通知用户升级

4. 披露
   - 发布安全公告
   - 更新文档

## 安全更新

- 安全更新将优先处理
- 将通过 GitHub Security Advisories 发布
- 重要更新将通过 Release Notes 通知

## 第三方依赖

我们定期监控和更新第三方依赖的安全问题：

- 使用 GitHub 的依赖机器人
- 定期运行安全扫描
- 及时更新有安全问题的依赖

## 安全检查清单

- [ ] 所有依赖都是最新的
- [ ] 没有已知的安全漏洞
- [ ] 日志中不包含敏感信息
- [ ] API 使用 HTTPS
- [ ] 输入数据经过验证
- [ ] 错误信息不泄露敏感信息 