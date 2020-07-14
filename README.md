# MCDNDC
MineCraftDoNotDoChallenge  
  
当前版本:  
稳定版:v1.2.0(Release)    |   开发版:v1.2.2(Snapshot)  

本插件的源码使用GPL-3.0进行授权，具体条款已在LICENSE文件写出  

## 插件功能
一个能在MC中实现一种小游戏(不要做挑战)的插件

## 前置插件
```
TAB 最新版本 - 可以在 https://www.spigotmc.org/resources/57806/ 获取
```

## 插件下载
#### 稳定版下载方式
1. 进入稳定版发布页https://github.com/jingwenMC/MCDNDC/releases  
2. 下载`Latest release`中的第一个Jar文件(插件本体)  
3. 下载前置插件  
4. 将下载的两个Jar文件复制到服务器的`/plugins`目录  
5. 启动服务器，并进行配置
#### 开发版下载方式
1. 进入开发版CI https://github.com/jingwenMC/MCDNDC/actions
2. 在`All workflows`中点击最新的一次构建
3. 在`Artifacts`中下载zip文件`Jar_Snapshot_Build`
4. 提取其中的Jar文件
5. 下载前置插件
6. 将提取和下载的两个Jar文件复制到服务器的`/plugins`目录
7. 启动服务器，并进行配置
## 插件指令
```text
/dndc:插件主指令. 别名:[/mcdndc,/dnd,/byz]
子命令:
   help              - 打开帮助菜单
   next              - 切换词语
   set <玩家> <分数> - 设置分数
   reload            - 重载配置
   restart           - 重载游戏
/wordkeeper:词语保管器. 别名:[/wk,/wkeeper]
子命令:
   set <词语代号> <词语> - 设置代号对应的词语
   get <词语代号>        - 获取代号对应的词语
     注:获取词语指显示在头上,不会直接透露词语
```
注:带`*`的指令为开发版指令 

## 插件权限

权限 | 说明 | 默认
----|----|----
dndc.restart      | 允许重新加载游戏     |OP
dndc.reload       | 允许重新加载配置文件,需要`dndc.restart` |OP
dndc.play         | 允许进行/dndc next   |玩家
dndc.set          | 允许设置分数         |OP
dndc.keep.add     | 允许添加词语到保管器 |玩家
dndc.keep.use     | 允许从保管器获取词语 |玩家

注:带`*`的权限为开发版权限,  
`dndc.reload`权限必须要有`dndc.restart`权限才能起到作用


## 配置文件(稳定版)
```yaml
####################
#MCDNDC Config File
#Config Language: 简体中文/Chinese(Simplified)
#You Can Download More Language At https://github.com/jingwenMC/MCDNDC/tree/master/langs
####################
#作者:jingwenMC
#开源许可:GPLv3
#版本:v1.2.0-RELEASE
####################

#Name:配置文件版本
#Note:此项用于配置文件结构版本的确认。为防止出错，请不要自行更改
#Default: 5
#Updated:v0.1
config_version: 5

#Name:选择语言
#Note:取决于lang.yml的设定
#Defaut: 'zh_CN'
#Updated:v1.0.0
lang: 'zh_CN'

#Name:计分板位置
#Note:计分板的显示位置,请从[SIDEBAR,BELOW_NAME,PLAYER_LIST]中填写一个
#  SIDEBAR:侧边栏
#  BELOW_NAME:名称下方(可能会与TAB插件冲突,可能需要对TAB插件进行配置,比较丑,不推荐)
#  PLAYER_LIST:TAB列表(可能会与TAB插件冲突,可能需要对TAB插件进行配置)
#Default: 'SIDEBAR'
#Updated:v1.0.0
scoreboard: 'SIDEBAR'

#Name:计分板更新频率
#Note:单位为tick(1/20秒)
#Default: 40
#Updated:v1.0.0
interval: 40

#Name:模块列表
#Note:请按照第一次释放配置时的格式填写,避免出错
#     模块相当于官方扩展,可根据自身需求进行启用/禁用
#Default:访问https://github.com/jingwenMC/MCDNDC/blob/master/src/main/resources/config.yml
#Updated:v1.2
modules:
  #自动在达到一定分值时，以玩家ID作为词语代号,对玩家进行词语设置
  #Updated:v1.2
  auto_switch_from_keeper:
    #是否启用
    enabled: true
    #要求的分值
    required_score: 4
    #切换时的提示信息
    msg_switch: '&b[MCDNDC]由于达到指定分值,玩家%player新的词语来自于词语保管器.'
  #是否在每一局游戏的开始显示广告(插件作者及开源地址)信息
  #如果你开启,我会感谢你!
  #Updated:v1.2
  ads:
    #是否启用
    enabled: true

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
暂无,欢迎提交issues

## 作者的话
我目前只是个Java以及BukkitAPI的初学者，有些代码可能不是很熟练，
有些代码可能不是很好，敬请谅解！（同时欢迎各位dalao提交issue以及PullRequest）  
联系我的一些方式：  
Bilibili:https://space.bilibili.com/289155331  
Email:2313055824@qq.com(本插件问题请不要给我发送邮件，请提交一个issue)  