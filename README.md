# 碧蓝航线wiki机器人

## 简介

基于[**mirai框架**](https://github.com/mamoe/mirai)的qq机器人，目前功能如下：

- 搜索[碧蓝航线wiki](https://wiki.biligame.com/blhx)数据
  - 舰娘基本属性、出处、语音、皮肤列表、科技点、评价、配装
  - 装备基本属性、出处
- 模拟大建

## 声明：一切开发旨在学习，请勿用于非法用途

- 本项目是完全免费且开放源代码的软件，仅供学习和娱乐用途使用
- 本项目的资料完全来自于[碧蓝航线wiki](https://wiki.biligame.com/blhx)，不保证数据的准确性和时效性
- 本项目的模拟大建功能仅供参考，不代表实际概率，~~勿上头~~

## 插件启用

- [安装Mirai Console](https://github.com/mamoe/mirai/blob/dev/docs/UserManual.md)
- 下载本项目最新的[jar包](https://gitee.com/arisaka-iris/blhx-wiki-mirai-plugin/releases)，放入Mirai-Console根目录下的plugin文件夹中
- 下载[数据文件](https://gitee.com/arisaka-iris/blhx-wiki-mirai-plugin/tree/master/data)放入Mirai-Console根目录下的data文件夹中
- 启动Mirai-Console，在Mirai-Console里登录

## Bot指令

此bot功能仅在群聊中开放，注意**指令用空格分开**

[] 代表该种类中的一个实例，()代表可以省略

| 群聊指令           | 描述                                             | 示例             |
| ------------------ | ------------------------------------------------ | ---------------- |
| wiki [舰娘]        | 查询舰娘基本属性                                 | wiki 拉菲        |
| wiki [舰娘] 出处   | 查询舰娘所有的出处                               | wiki 拉菲 出处   |
| wiki [舰娘] 皮肤   | 查询舰娘所有的皮肤                               | wiki 拉菲 皮肤   |
| wiki [舰娘] [语音] | 查询舰娘对应类型的语音，如果有多条则随机发送一条 | wiki 拉菲 誓约   |
| wiki [舰娘] 科技点 | 查询舰娘所提供的舰队科技                         | wiki 拉菲 科技点 |
| wiki [舰娘] 配装   | 查询舰娘的配装推荐                               | wiki 拉菲 配装   |
| wiki [舰娘] 评价   | 查询wiki大佬们对此舰娘的评价                     | wiki 拉菲 评价   |
|                    |                                                  |                  |
| wiki [装备]        | 查询装备的基本属性                               | wiki 533         |
| wiki [装备] 出处   | 查询装备的所有出处                               | wiki 533 出处    |
|                    |                                                  |                  |
| wiki 大建(轻池)    | 模拟轻池大建                                     | wiki 大建        |
| wiki 大建列表      | 查询可以模拟大建的池子                           | wiki 大建列表    |
| wiki 大建[池子]    | 模拟对应池子大建                                 | wiki 大建胡滕    |
