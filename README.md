# 图片批量合成工具

> 说明：
>
> 此工具用于图片的批量合成，可以将导入的数据源和与之关联的素材图片根据模板文件进行批量合成，减少重复工作，提高出图效率。

## 一、环境

### 1. 开发环境

在执行构建前，请确保下述开发环境已成功搭建，并正确配置好环境变量：

- Java JDK 8u241
- Maven 3.6.1

在进行构建后，通过运行`app`目录下面的`cn.powerinv.sssj.app.MainApplication`类，可以启动本项目并查看运行效果。

需要注意的是，每次更新代码后都要`先执行一遍 mvn install`之后再运行，否则你的改动可能不会生效。

### 2. 运行环境

本项目的正常运行至少需要以下环境的支持：

- Java JRE 8u241

## 二、构建图片批量合成工具

本项目已配置了相关maven插件，可以直接在项目的根目录使用命令`mvn install`来进行一键式构建。

## 三、目录结构

本项目的目录结构及相关说明如下图所示：

```text
────┬─<CD_HOME>
    │
    │  LICENSE 版权及开源协议
    │  README.md 项目自述文件
    │
    ├─app 主程序模块
    │  └─src
    │     ├─main
    │     │  ├─java 主程序源码
    │     │  └─resources 主程序的相关资源文件
    │     │      ├─images
    │     │      ├─style
    │     │      │  └─theme 颜色主题
    │     │      └─template 窗口结构定义
    │     └─test 主程序单元测试代码
    │
    ├─docs
    │      Whats_New.md 更新说明文档
    │
    ├─plugins 扩展程序模块
    │  └─CdDatasourcePluginPackage 数据源相关扩展程序
    │
    └─sdk 扩展程序接口定义模块
```

## 四、相关文档

### 1. 项目描述文档

### 2. 更新说明

详见[更新说明文档](./docs/Whats_New.md)

## 版权说明

该项目签署了MIT授权许可，详情请参阅 [LICENSE](./LICENSE) 文件

原作者：[Cambridge James](https://github.com/cambridgejames)

如果正在使用过程中遇到问题，请提交 Issues 至 [GitHub](https://github.com/cambridgejames/ImagesDesigner/issues)
