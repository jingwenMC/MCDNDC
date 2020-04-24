# MCDNDC
MineCraftDoNotDoChallenge  
当前版本:v0.2.0(Alpha)  
本插件的源码使用GPL-3.0进行授权，具体条款已在LICENSE文件写出  

## 插件功能
一个能在MC中实现一种小游戏(不要做挑战)的插件

## 前置插件
```
TAB 最新版本 - 可以在 https://www.spigotmc.org/resources/57806/ 获取
```

## 插件指令
```text
/dndc(/dnd,/byz) - 插件主命令
子命令:
  reload  - 重载配置文件
  restart - 重新加载游戏
  next    - 从词库抽取词语
  set     - 设置分数
  help    - 帮助页面
```

## 插件权限
```text
dndc.restart - 允许重新加载游戏
dndc.reload  - 允许重新加载配置文件
dndc.play    - 允许进行游玩
```

## 配置文件
```yaml
####################
#MCDNDC Config File
#作者:jingwenMC
#开源许可:GPLv3
#版本:v0.1.2
####################

#Name:配置文件版本
#Note:此项用于配置文件结构版本的确认。为防止出错，请不要自行更改
#Default: 2
#Updated:v0.1
config_version: 2

#Name:选择语言
#Note:在1.0版本前没有任何作用
#Defaut: 'zh_CN'
#WillUpdate:v1.0
lang: 'zh_CN'

#Name:重启时是否重置玩家的TAG状态(前缀)
#Note:设置为false将在重启时不会清除玩家当前的TAG状态(前缀)
#Default: true
#Updated:v0.1.1
reset_tag_on_restart: true

#Name:当玩家抽取词语时显示上一个词语的类型
#Note:当玩家抽取词语时显示上一个词语(即玩家之前抽取的词语)的类型，有3种：
#     broadcast:向服务器内所有玩家发送信息
#     message:只向抽取的玩家发送信息
#     none:不显示
#Default: 'broadcast'
#Updated:v0.1.1
notify_player_after_next_word: 'broadcast'

#Name:词库列表
#Note:请按照第一次释放配置时的格式填写,避免出错
#Default:
#  - 'Word1'
#  - 'Word2'
#  - 'Word3'
#Updated:v0.1
words:
  - 'Word1'
  - 'Word2'
  - 'Word3'
```

## 未来计划
计划加入自动裁判功能

## 作者的话
我目前只是个Java以及BukkitAPI的初学者，有些代码可能不是很熟练，
有些代码可能不是很好，敬请谅解！（同时欢迎各位dalao提交issue以及PullRequest）  
联系我的一些方式：  
Bilibili:https://space.bilibili.com/289155331  
Email:2313055824@qq.com(本插件问题请不要给我发送邮件，请提交一个issue)  
_ps.我可以进行一些**基本**的插件定制,有意可联系我。_