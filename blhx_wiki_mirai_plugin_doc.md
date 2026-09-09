# 碧蓝航线 (blhx) Wiki Mirai 插件项目详尽文档

## 1. 项目概述
`blhx-wiki-mirai-plugin` 是基于 `mirai-console` 框架开发的 Kotlin 机器人插件。它的主要功能是提供对《碧蓝航线》Wiki 游戏数据的查询（包括舰娘属性、装备、建造时间、节奏榜、关卡信息、建造模拟等），并将查询结果渲染为图片或文字在 QQ 群中发送。项目基于 Kotlin JvmPlugin 规范架构开发，实现了对 Bilibili Wiki 高度定制的渲染呈现。

---

## 2. 目录结构
主要代码均位于 `src/main/kotlin/` (包路径：`org.iris.wiki`)：

- **`Wiki.kt`** - 插件的入口类，负责注册命令、事件监听、加载配置和启动更新资源过程。
- **`Listener.kt`** - 全局事件监听器，拦截聊天信息并使用正则切分过滤路由到相应的查询逻辑。
- **`Checker.kt`** - 权限校验与缓存前置校验器，用于处理请求预拦截（例如直接识别特定指令并处理，如群管）。
- **`action/`** 目录 - 功能逻辑模块汇总：
    - `Draw.kt`: 抽卡系统、十连建造模拟逻辑（处理普通池及活动池不同概率计算）。
    - `Question.kt`: 答题系统。
- **`command/`** 目录 - Mirai控制台命令和管理员配置指令：
    - `ReplyCommand.kt`: 自动回复功能支持，允许群里自定义基于图文混合匹配的快速自动问答字典。
    - `WikiConfigCommand.kt`: 热更新与动态加载配置文件后台命令。
- **`config/`** 目录 - 机器人的配置和资源缓存加载管理：
    - `AliasConfig.kt`: QQ用户习惯俗称/绰号到官方词条的字典转换配置库。
    - 其它还包括 `WikiConfig`, `CommonConfig`, `FinalString`(记录静态长文本), `AutoReplyConfig` 等机制。
- **`data/`** 目录 - 对应 Bwiki 解析结果的数据建模容器类模型（例如 `ShipAttrData.kt` 包含星级、战力参数， `StageData.kt` 包含关卡掉落信息等，它们都会实现转换核心基类 `Data`）。
- **`paint/component`** 目录 - **(核心组件)** 负责将各个抓取来的 `data` 数据结构最终渲染为漂亮的一体式海报、图片并排版的核心绘图组件。里面包含诸多独立的绘图节点，例如 `ShipAttrComponent.kt`、`SpecialEquipComponent.kt`。
- **`utils/`** 目录 - 系统常用工具类函数库，包含：
    - `ParserUtils.kt`: Jsoup 爬虫与页面核心解析机制，主要用于根据不同节点清洗和抽取 Wiki HTML 数据。
    - `HttpUtils.kt`: 网络请求工具封装。
    - `DrawUtils.kt` / `ImageUtils.kt`: java.awt 绘图函数及底层图片IO操作封装。
    - `UpdateUtils.kt`: 抓站数据热更新器。
    - `MessageBuildUtils.kt`: 适配到 Mirai 本地 API 发送图文结构的转发工厂机制。

---

## 3. 核心类及方法分析

### 3.1 插件主入口启动阶段
#### `Wiki.kt (object : KotlinPlugin)`
- **主要作用**: Mirai 机器人核心启动挂载点。
- **生命周期核心方法**: 
  - `onEnable()`: 先加载各核心模块所在的配置实例（`AliasConfig`, `CommandConfig` 等）；注册订阅 `Listener` 以及 `QuestionListener`；然后发布指令组件模块如 `WikiConfigCommand` 与 `ReplyCommand`；最后拉起异步协程 `UpdateUtils.updateAll()` 实现后台开机拉取并同步远程端各舰娘名录与常用属性基础。
  - `onDisable()`: 反注册指令、归还运行资源。

### 3.2 消息请求响应路由层
#### `Listener.kt (internal object)`
- **主要作用**: 在全局信道(GlobalEventChannel)对发来的所有消息做指令鉴定。
- **核心方法**:
  - `subscribe()`: 订阅群聊普通发言 `GroupMessageEvent` 和 特殊交互 `NudgeEvent` (戳一戳)。按空格正则表达式将用户输入肢解为最大长度为 3 或 5 的数组，提取首字判定是否属于 `Wiki` 指令空间。
  - `phraseCommand(msg, sender)`: 对话推断匹配器。用于那些没有用正规语法但输入有效别名名字的情况进行模糊修复或猜解词条（单词或者双词组合推测算法）。
  - `wiki(commandList, sender, searchAgain)`: 获取标准化参数的路由核心函数。它进行一次 `Checker.check`，然后进入 Bwiki 页面利用 `ParserUtils.parse` 解析 HTML。最终将抓取的 `Result` 交给 `MessageBuildUtils.build` 并将其回传群内。

### 3.3 数据解析与抓取 (页面清洗引擎)
#### `utils/ParserUtils.kt`
- **主要作用**: 利用 Jsoup 解析从指定网络（如 Bilibili 碧蓝航线 Wiki）获取的 HTML Document，基于其特定的页面节点 ID 分流出特定的词条处理分支模块。
- **核心方法**:
  - `parse(data, commandList)`: Root级解析开关。能分辨"搜索模糊页""舰娘图鉴""鱼雷/设备""舰队排行一图流""建造时间"等数十种特定网页结构。
  - `parseShip(doc, commandList)`: 深入舰娘页分发。判定获取原图、获取科技点数据、属性评价面板或者是语音包。
  - `parseLevel / parseTable`: 对带有关卡掉落或图文表格信息的页面抽帧成对应的图片 URL 列表。
  - `search(commandList)`: 这是应对 Bwiki 搜索的模糊修正算法（例如玩家混用中英文时对库里收进来的 NAME_LIST 进行切割正则表达式多项验证以修正搜索）。

### 3.4 交互功能 (动作逻辑设计)
#### `action/Draw.kt`
- **主要作用**: 实现游戏内的抽卡/模拟建造功能。
- **核心方法**:
  - `draw(type)`: 常驻卡池（轻型、重型、特型）。根据内建概率区间随机抽取 `DrawUtils.getRarity()` 和名字序列。
  - `drawActive(pool)`: 模拟带限定概率加权（UP）活动池。需要获取UP内 UR 概率，SSR 概率及相应占比和兜底算法来分配。结束后会将它们注入给 `DrawResultComponent` 输出成品建造界面位图。

### 3.5 自动回复命令集 
#### `command/ReplyCommand.kt`
- **主要作用**: 机器人管理员通过后台或者聊天窗注册一些带图片的互动 QA 句子来定制机器人的交互能力。
- **子指令 (@SubCommand)**: 
  - `set (name, replies)`: 初始化词条。将输入内容遍历为 PlainText 或提取图片并调用 `ImageUtil.getImage` 与 `ImageIO.write` 在设定的服务器缓存目录持久化为 png 素材供以后匹配。
  - `reset (name)` / `delete (name)`: 更新重载、清除包含正则表达式 `regex("${command}_[^_.].png")` 图片素材的相关词条字典。

### 3.6 UI渲染与表现底层 (Paint Component)
`paint/component` 是该项目内较为厚重的图形代码包装容器，功能非常类似 Web 环境中的 Canvas 与节点渲染机制：
- `Component.kt`: 基座抽象组件，包含 `draw()` 与 `init()` 生命期。
- `ShipAttrComponent.kt` / `EquipAttrComponent.kt` : 对于解析回来的装备或舰娘等复杂的数据，通过 G2D（`Graphics2D`对象模型）完成背景绘制、框体边界裁切、数值居中排版、贴图处理，以至于将单纯的数据变成一张能直接发出的华丽海报图片。
- 最终都会交到拥有类似 `BufferImagesData` 功能的对象上作为文件帧或者图片流回传。

### 3.7 模型 (Data Models)
`data` 目录下拥有继承自顶层多态 `Data` 的各类模型基类：
- **继承体系**：包含 `ShipAttrData / ShipData` 舰船三维和图鉴，`StageData` 关卡属性。
- 每一个对应的数据对象自身会带有 `parse(doc, commandList)` 的封装抽象接口处理从 ParserUtils 下放而来的 HTML 对象流。

---

## 4. 机制与使用交互链路

### 指标流转生命周期
1. **触发与过滤**: 用户发送 `wiki 独角兽 属性`。
2. **事件激活**: `Listener.subscribe()` 处理字符流变为 `[wiki, 独角兽, 属性]`。
3. **词本别名映射**: `AliasConfig` 检测是否符合黑手党词典如 “妹妹” -> `独角兽`。
4. **HTML 请求并解析解析**: 组装 HttpLink 被 `HttpUtils.get` 获取，内容转交给 `ParserUtils` 作为 Document处理发现其包含 “舰娘图鉴”。
5. **抽取填槽**: `parseShip` 指挥 HTML 中的火力/防空等标签填凑生成 `ShipAttrData` 对象实例。
6. **制图合并**: 数据结构送到 `ShipAttrComponent` ，按照计算好的排版宽高生成成图对象流 ( `BufferedImage` )。
7. **QQ回传**: `MessageBuildUtils` 拆解为 `Image/ExternalImage` 发送到原信源 QQ 群内并显示。 

### 扩展特性模块简述
- **戳一戳机制**: 依托于 `NudgeEvent` 动作，`WikiConfig` 可以指定双击头像动作直接映射成预备的一句特定交互口令(例如：wiki 随机舰娘)。
- **持久化静态映射**: Bwiki 大部分信息静态化，所以 `UpdateUtils` 利用启动定时器主动爬取缓存一份本地版数据，防止高并发下对原站产生的限流。
