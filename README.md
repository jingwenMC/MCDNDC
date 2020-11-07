# MCDNDC
一个能在MC中实现一种小游戏(不要做挑战)的插件

----------
当前的最新正式版本:1.9.90-BETA  
当前的最新快照版本:暂无快照

## MCDNDC-2.0Beta
到了今天,MCDNDC2.x终于发布了第一个测试版。
目前还有些1.x的功能没有迁移过来，我会尽快迁移，但也请大家多多谅解。
目前MCDNDC处于`BETA`公开测试阶段，可能会有较多BUG，如果你发现了，请随时联系我。

### MCDNDC2.0更新了什么？
 * 更灵活地词库管理
 * 更加模块化的代码
 * 更不易出错的语言和配置文件
 * 更少刷屏的异常捕捉
 * 更易开发的API
 * 还有更多!
 
## 前置插件
```
使用TAB插件显示模式需要:
TAB v2.8.7及以上 - 可以在 https://www.spigotmc.org/resources/57806/ 获取
```

## 插件下载
https://github.com/jingwenMC/MCDNDC/releases

## 插件指令
```text
/dndc:插件主指令. 别名:[/mcdndc,/dnd,/byz]
子命令:
   help              - 打开帮助菜单
   next              - 切换词语
   set <玩家> <分数>  - 设置分数 [需要scoreboard扩展才会起显示作用]
   reload            - 重载配置
   restart           - 重载游戏
   words             - 打开词语编辑器 [需要gui扩展才会起作用]
   extension         - 打开扩展管理器
/wordkeeper:词语保管器. 别名:[/wk,/wkeeper] [需要wordkeeper扩展才会起作用]
子命令:
   set <词语代号> <词语> - 设置代号对应的词语
   get <词语代号>        - 获取代号对应的词语
     注:获取词语指显示在头上,不会直接透露词语
```

## 插件权限

权限 | 说明 | 默认
----|----|----
dndc.restart      | 允许重新加载游戏     |OP
dndc.reload       | 允许重新加载配置文件,需要`dndc.restart` |OP
dndc.play         | 允许进行/dndc next   |玩家
dndc.set          | 允许设置分数         |OP
dndc.keep.add[暂无]     | 允许添加词语到保管器 |玩家
dndc.keep.use[暂无]     | 允许从保管器获取词语 |玩家
dndc.words[暂无]     | 允许使用词语编辑器 |OP  

注:`dndc.reload`权限必须要有`dndc.restart`权限才能起到作用

## 未来计划
以下计划将在`2.0.0`正式版本发布之前完成:
 * 完成扩展管理器
 * 完成1.x的功能迁移
   * internal_scoreboard扩展
   * internal_wordkeeper扩展
   * internal_itemswitch扩展
   * internal_gui扩展
 * 增加原生词语显示的功能
 * 开发者API文档
 * 外部扩展开发SDK

## 开发者API(暂未提供)
目前暂未提供Javadoc以及仓库地址

## 作者的话
我目前只是个Java以及BukkitAPI的初学者，有些代码可能不是很熟练，
有些代码可能不是很好，敬请谅解！（同时欢迎各位dalao提交issue以及PullRequest）  
联系我的一些方式：  
Bilibili:https://space.bilibili.com/289155331  
Email:2313055824@qq.com(本插件问题请不要给我发送邮件，请提交一个issue)  