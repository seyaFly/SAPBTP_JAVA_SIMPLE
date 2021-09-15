[English](/README.md) | [中文](/README_ZH.md)

本仓库为 SAP BTP Java 应用程序的简单实例， 我们可以用此实例来熟悉SAP BTP 中的java应用开开发流程.

在你完成改示例java应用程序以后， 我们可以直接使用该java应用程序进行后续的开发流程， 或直接构建一个新的JAVA应用程序来支持新的业务开发

## 目录
<!-- MarkdownTOC -->
- [目录](#目录)
  - [准备阶段](#准备阶段)
    - [BTP 账户](#btp-账户)
    - [工具](#工具)
  - [开发](#开发)
  - [构建](#构建)
  - [测试(本地)](#测试本地)
  - [部署](#部署)
  - [测试 （BTP subaccount）](#测试-btp-subaccount)
  - [参考](#参考)
<!-- /MarkdownTOC -->

### 准备阶段 

#### BTP 账户
使用该 [link](https://help.sap.com/viewer/65de2977205c403bbc107264b8eccf4b/Cloud/en-US/e50ab7b423f04a8db301d7678946626e.html) 申请BTP的试用账户或者配置企业账户

#### 工具
   **Java** 

   安装 [JDK1.8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html) 或者更高版本

   **Maven**
    
   安装 [Maven 3.5.0](http://maven.apache.org/docs/3.5.0/release-notes.html) 或者更高版本
    
   **IDE**

   我们可以使用 [Eclipse](https://www.eclipse.org/), [Idea](https://www.jetbrains.com/idea/) , [Theia](https://theia-ide.org/) 或者 [VSC](https://code.visualstudio.com/) 作为我们的集成开发环境 或者其他合适的集成开发环境

   * **Eclipse** 
   
     安装 [STS](https://marketplace.eclipse.org/content/spring-tools-4-aka-spring-tool-suite-4) 工具包到eclpse集成开发环境

   * **Idea**
   
     Idea 已经集成 [SpringBoot Intializer](https://start.spring.io), 无需更多配置

   * **VSC**
    
     安装 [SpringBoot Pack Extention](https://marketplace.visualstudio.com/items?itemName=Pivotal.vscode-boot-dev-pack) , 以及 [Java Extention](https://marketplace.visualstudio.com/items?itemName=pverest.java-ide-pack), 或者 [Spring Boot Developer pack](https://marketplace.visualstudio.com/items?itemName=developersoapbox.vscode-springboot-developer-pack), 依照此 [文档](https://github.com/redhat-developer/vscode-java) 配置JAVA 开发环境

   * **Theia** 

      Eclisp旗下用于作为Visual Studio Code 的替代开发集成环境， 配置与VSC相似，还处于Beta开发中， 喜欢尝试的朋友可以试一下，做first bird

  **CF命令行**
     
   下载并配置: [Download and Install the Cloud Foundry Command Line Interface](https://help.sap.com/viewer/65de2977205c403bbc107264b8eccf4b/Cloud/en-US/4ef907afb1254e8286882a2bdef0edf4.html)
   
   直接下载链接: [https://github.com/cloudfoundry/cli#downloads](https://github.com/cloudfoundry/cli#downloads)



### 开发

步骤 :

1. 通过 [SpringBoot Intializer](https://start.spring.io) 创建 Spring Boot application .
   
   在此应用程序，我们配置了如下下信息:
    ``` 
        groupId : onem.seya 
	    artifactId: javademo
    ```

2. 添加依赖， spring web  + Rest Repository
   
   ![Denpendcy](/img/Dependency.png)

3. 创建**controller**文件夹并添加类**HelloWorldController**
   
   ![JavaPackage](/img/JavaPackage.png)

4. 添加以下示例代码，并保存
   ```Java
    @RestController
    @RequestMapping(HelloWorldController.PATH)
    public class HelloWorldController {
        public static final String PATH = "/api";

        @GetMapping(value = "/welcome")
        public String printHelloWord() {
            String welcomeMessage = "Hello, this is BTP SAP JAVA Simple Demo";
            return welcomeMessage;
        }
    }
   ```
### 构建

构建 java 应用程序包 ，请使用命令 ： ``` mvn package```

### 测试(本地)

运行java应用应用程序，请使用命令 ``` Java -jar XXXX.jar ```

测试链接 :
[http://localhost:8080/api/welcome](http://localhost:8080/api/welcome)


返回消息.

``` Hello, this is BTP SAP JAVA Simple Demo```

获得了上述返回消息， 则整个应用程序已经正常运行

### 部署

部署至BTP，步骤如下:
1. 设置 cloud foundry endpoint
   
   命令行 :

      ```cf api {EndpointURL} ```

   **EndpointURL** 你可以在子账户中看到对应的API endpint :
   ![APIEndPoint](/img/APIEndPoint.png)

2. 使用你的BTP账户登录对应的BTP CF环境
   
   命令行 :

      ```cf login ```

3. 在JAVA应用程序根目录下，添加 **manifest.yml** 
4. 配置 [route](https://help.sap.com/viewer/65de2977205c403bbc107264b8eccf4b/Cloud/en-US/53daaafe8f8345fc9b8497b86d17c9d9.html?q=routes) 和相应的 [java buildpacks](https://help.sap.com/viewer/65de2977205c403bbc107264b8eccf4b/Cloud/en-US/a3f90069d6cd41da82f34a6123d82ce6.html)

推荐使用以下方式形成对应的route :

 ```
   {subdomain}-{appname}.{cfappdoman}
 ```

**subdomain:** 

![subdomain](/img/subdomain.png)

**appname**: 由业务定义

**cfappsdomain:**  可使用命令行 ```cf domains ``` 获取对应的domains

![cfappdomain](/img/cfappdoman.png)

样例:

   ```
      ---
      applications:
      - name: demo
      memory: 400M
      path: target/btp_JavaApplicatin.jar
      instances: 1
      buildpacks: 
         - sap_java_buildpack
      env:
         TARGET_RUNTIME: tomee7
      routes: 
         - route: 91ccc175trial-demo.cfapps.ap21.hana.ondemand.com 
   ```
5. 部署jar包到你的BTP环境中
   
   命令行

    ```cf push ```

### 测试 （BTP subaccount）

1. 导航到到 sapce
   
 ![space](/img/space.png)

2. 进入到 applcation 
   
 ![space_application](/img/space_application.png)

3.  查看 applicaiton URL
   
 ![applicationOverview](/img/applicaiton_overview.png)

4. 用以下链接来测试
   
   ```
   {applicaitonURL}/api/welcome
   ```

   获取到如下的返回消息后， 我们的java应用程序已经部署到BTP CF环境，并已运行正常

   ``` Hello, this is BTP SAP JAVA Simple Demo```

### 参考

SpringBoot Rest Service Guide : [SpringBoot Rest Service Guide](https://spring.io/guides/gs/rest-service/)

SAP BTP JAVA Development: [Developing Java in the Cloud Foundry Environment](https://help.sap.com/viewer/65de2977205c403bbc107264b8eccf4b/Cloud/en-US/a3f90069d6cd41da82f34a6123d82ce6.html)


