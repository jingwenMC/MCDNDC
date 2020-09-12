# MCDNDC
MineCraftDoNotDoChallenge  
  
当前版本:  
稳定版:v1.4.3(Release)(LTS)    |   开发版:v1.4.3(Release)(LTS)  

本插件的源码使用GPL-3.0进行授权，具体条款已在LICENSE文件写出  

## 重要说明
本插件即将进行完全重写，近期更新可能会停止除重大BUG之外的更新。  
重写后，我们可能将彻底移除TAB依赖，同时使用新的框架。  
代码整洁度也会有所提高。  
开始重写工程后，我们会在`master`分支进行更新，同时会将现有项目移至`1.x`分支。  
新的版本会争取在寒假之前上线，敬请期待！

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
3. 在`Artifacts`中下载zip文件`Jar_Snapshot_Build`(可能需要登录GitHub才能下载)
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
   set <玩家> <分数>  - 设置分数
   reload            - 重载配置
   restart           - 重载游戏
   words             - 打开词语编辑器
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
dndc.words     | 允许使用词语编辑器 |OP

注:带`*`的权限为开发版权限,  
`dndc.reload`权限必须要有`dndc.restart`权限才能起到作用


## 默认配置文件(稳定版)
请见https://github.com/jingwenMC/MCDNDC/blob/v1.4.3/src/main/resources/config.yml

## 未来计划
暂无,欢迎提交issues

## 开发者API
我们提供了一套API,你可以将其引用至你的项目中
### Javadoc
Javadoc地址:https://jingwenmc.github.io/MCDNDC/
### 引用
#### Maven
##### 仓库
```xml
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
```
##### 最新稳定版
```xml
<dependency>
	<groupId>com.github.jingwenMC</groupId>
	<artifactId>MCDNDC</artifactId>
	<version>1.4.3</version>
	<scope>provided</scope>
</dependency>
```
##### 最新开发版
```xml
<dependency>
	<groupId>com.github.jingwenMC</groupId>
	<artifactId>MCDNDC</artifactId>
	<version>master-SNAPSHOT</version>
	<scope>provided</scope>
</dependency>
```
#### Gradle
##### 仓库
```groovy
repositories {
	maven { url 'https://jitpack.io' }
}
```
##### 最新稳定版
```groovy
dependencies {
        compileOnly 'com.github.jingwenMC:MCDNDC:1.4.3'
}
```
##### 最新开发版
```groovy
dependencies {
        compileOnly 'com.github.jingwenMC:MCDNDC:master-SNAPSHOT'
}
```

## 作者的话
我目前只是个Java以及BukkitAPI的初学者，有些代码可能不是很熟练，
有些代码可能不是很好，敬请谅解！（同时欢迎各位dalao提交issue以及PullRequest）  
联系我的一些方式：  
Bilibili:https://space.bilibili.com/289155331  
Email:2313055824@qq.com(本插件问题请不要给我发送邮件，请提交一个issue)  