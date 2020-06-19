# MCDNDC
MineCraftDoNotDoChallenge  
当前版本:v0.2.0(Snapshot)|v0.1.1(Alpha)  
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

权限 | 说明 | 默认
----|----|----
dndc.restart | 允许重新加载游戏 |OP
dndc.reload  | 允许重新加载配置文件 |OP
dndc.play    | 允许进行/dndc next |玩家
dndc.set     | 允许设置分数 |OP


## 配置文件
```yaml
####################
#MCDNDC Config File
#Config Language: 简体中文/Chinese(Simplified)
#You Can Download More Language At https://github.com/jingwenMC/MCDNDC/tree/master/langs
####################
#作者:jingwenMC
#开源许可:GPLv3
#版本:v1.0.0
####################

#Name:配置文件版本
#Note:此项用于配置文件结构版本的确认。为防止出错，请不要自行更改
#Default: 4
#Updated:v0.1
config_version: 4

#Name:选择语言
#Note:取决于lang.yml的设定
#Defaut: 'zh_CN'
#Updated:v1.0.0
lang: 'zh_CN'

#Name:重启时是否重置玩家的TAG状态(前缀)
#Note:设置为false将在重启时不会清除玩家当前的TAG状态(前缀)
#Default: true
#Updated:v0.1.1
reset_tag_on_restart: true

#Name:重启时是否重置玩家的分数(TAB后缀)
#Note:设置为false将在重启时不会重置玩家的分数(TAB后缀)
#Default: true
#Updated:v0.2.0
reset_score_on_restart: true

#Name:是否切换时增长分数
#Note:是否在切换词语的时候增长分数
#Default: true
#Updated:v0.2.0
add_point: true

#Name:计分板位置
#Note:计分板的显示位置,请从[SIDEBAR,BELOW_NAME,PLAYER_LIST]中填写一个
#  SIDEBAR:侧边栏
#  BELOW_NAME:名称下方(可能会与TAB插件冲突,而且比较丑,不推荐)
#  PLAYER_LIST:TAB列表
#Default: SIDEBAR
#Updated:v1.0.0
scoreboard: 'SIDEBAR'

#Name:计分板更新频率
#Note:单位为tick(1/20秒)
#Default: 40
#Updated:v1.0.0
interval: 40

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