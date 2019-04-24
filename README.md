# Admin
# 引言

Admin 项目是根据JeeSite 4.0 进行改造的一个 Java EE 企业级快速开发平台，基于经典技术组合（Spring Boot、Spring MVC、Apache Shiro、Flyway、Beetl、BeetlSQL、Bootstrap、AdminLTE），在线代码生成功能，包括核心模块如：组织机构、角色用户、菜单及按钮授权、数据权限、系统参数、内容管理、工作流等。采用松耦合设计；界面无刷新，一键换肤；众多账号安全设置，密码策略；在线定时任务配置；支持集群，支持SAAS；支持多数据源。

> JeeSite 4.0内部framework、core包全部代码混淆，正常开发修改源码很吃力，很难认识内部机制。下载本项目更有利与企业快速开发运用。基本有所有的JeeSite 4.0的特性。具体使用结合[官方文档](https://jeesite.gitee.io/docs/)。

Admin 快速开发平台的主要目的是能够让初级的研发人员快速的开发出复杂的业务功能，让开发者注重专注业务，其余有平台来封装技术细节，降低技术难度，从而节省人力成本，缩短项目周期，提高软件安全质量。

现在Admin 是基于 JeeSite 4.0 的升级，是将JeeSite 4.0核心混淆包完成了一次全部重构，也纳入很多新的思想。不管是从开发者模式、底层架构、逻辑处理还是到用户界面，用户交互体验上都有很大的进步，在不忘学习成本、提高开发效率的情况下，安全方面也做和很多工作，包括：身份认证、密码策略、安全审计、日志收集。


#  JeeSite 4.0 授权协议官方声明

1. 已开源的代码，授权协议采用 AGPL v3 + Apache Licence v2 进行发行。
2. 您可以免费使用、修改和衍生代码，但不允许修改后和衍生的代码做为闭源软件发布。
3. 修改后和衍生的代码必须也按照AGPL协议进行流通，对修改后和衍生的代码必须向社会公开。
4. 如果您修改了代码，需要在被修改的文件中进行说明，并遵守代码格式规范，帮助他人更好的理解您的用意。
5. 在延伸的代码中（修改和有源代码衍生的代码中）需要带有原来代码中的协议、版权声明和其他原作者规定需要包含的说明（请尊重原作者的著作权，不要删除或修改文件中的`@author`信息）。
6. 您可以应用于商业软件，但必须遵循以上条款原则（请协助改进本作品）。
7. 关系平台的发展战略考虑，底层部分代码暂未开源，但这不影响您的二次开发。
8. 请知悉社区版，用户数不可超过100个，最大允许10个用户同时在线（不含匿名）。

#  Admin-test 说明

1. Admin-test 是运用Admin项目的demo示例工程，单独独立的，只是放在了Admin工程下。
2. application.yml配置文件说明：集成自动部署工具flyway，加入了初始化数据库脚本，需要的话开启`flyway.enabled=true`,配置`flyway.locations=classpath:db/framework/migration`即可。
3. pom.xml文件说明： 运用了公司manven私服，主要下载admin-framework、admin-core两工具包。配置如下：
  ```
         <repository>
  			<id>cammm-repos</id>
  			<url>http://nexus.cammm.com.cn/repository/maven-public/</url>
  			<releases>
  				<enabled>true</enabled>
  			</releases>
  			<snapshots>
  				<enabled>true</enabled>
  			</snapshots>
  		</repository>
  ```
  
+ 启动工程，导入数据库，访问两个示例：`http://localhost:86/admin-test/hellow/index  或 http://localhost:86/admin-test/hellow/index1`

> ##### Admin项目是根据JeeSite 4.0 进行改造的，若有侵权行为，请发邮件（wugaoshang@hotmail.com）联系作者!谢谢！