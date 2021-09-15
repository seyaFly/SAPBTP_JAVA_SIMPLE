[English](/README.md) | [中文](/README_ZH.md)

This Repository is the BTP Simple Demo Application for Java Applicaiton. we can use this demo to familiary with the SAP BTP Java Development Lifecycle.

After that ,  you can directly user the template for further development or build another applicaitons with you own development

## Table Of Content
<!-- MarkdownTOC -->
- [Table Of Content](#table-of-content)
  - [Preparation](#preparation)
    - [BTP Account](#btp-account)
    - [Tools](#tools)
  - [Development](#development)
  - [Build](#build)
  - [Test(local)](#testlocal)
  - [Deployment](#deployment)
  - [Test(BTP subaccount)](#testbtp-subaccount)
  - [Reference](#reference)
<!-- /MarkdownTOC -->

### Preparation 

#### BTP Account
Use this [link](https://help.sap.com/viewer/65de2977205c403bbc107264b8eccf4b/Cloud/en-US/e50ab7b423f04a8db301d7678946626e.html) to apply and setup the BTP trial or enterpise enviroment

#### Tools
   **Java** 
    
   Install and setup  the Java [JDK1.8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html) or higher

   **Maven**
    
   Install and setup [Maven 3.5.0](http://maven.apache.org/docs/3.5.0/release-notes.html) or higher 
    
   **IDE**
    
   we can use the [Eclipse](https://www.eclipse.org/), [Idea](https://www.jetbrains.com/idea/) , [Theia](https://theia-ide.org/) or [VSC](https://code.visualstudio.com/) as you favourite development IDE, or other IDE you want.

   * **Eclipse** 
   
     Install the [STS](https://marketplace.eclipse.org/content/spring-tools-4-aka-spring-tool-suite-4) tools fomr you Eclipse IDE

   * **Idea**
     
     Idea already Integrate the [SpringBoot Intializer](https://start.spring.io), no more configuration needed

   * **VSC**
      
     Install the [SpringBoot Pack Extention](https://marketplace.visualstudio.com/items?itemName=Pivotal.vscode-boot-dev-pack) , and [Java Extention](https://marketplace.visualstudio.com/items?itemName=pverest.java-ide-pack), or [Spring Boot Developer pack](https://marketplace.visualstudio.com/items?itemName=developersoapbox.vscode-springboot-developer-pack), follow this [instruction](https://github.com/redhat-developer/vscode-java) to configue the java runtime

   * **Theia** 
  
      As alternative IDF fo VSC under Eclpse orgnization,  and it is still in Beta development , if you want to be the first bird , we can try it.

  **CF Command line**
    
   Dowanload and Configration : [Download and Install the Cloud Foundry Command Line Interface](https://help.sap.com/viewer/65de2977205c403bbc107264b8eccf4b/Cloud/en-US/4ef907afb1254e8286882a2bdef0edf4.html)

   Direct Download link: [https://github.com/cloudfoundry/cli#downloads](https://github.com/cloudfoundry/cli#downloads)

### Development

Steps :

1. create Spring Boot application via [SpringBoot Intializer](https://start.spring.io).
   In this Simple Application , here are the information we configured:
    ``` 
        groupId : onem.seya 
	    artifactId: javademo
    ```

2. Add Dependency, Spirng Web + Rest Repository
   
   ![Denpendcy](/img/Dependency.png)

3. create **controller**folder and add the class **HelloWorldController**
   
   ![JavaPackage](/img/JavaPackage.png)

4. Add the sample code and save it
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
### Build

build java application package with the command line ``` mvn package```

### Test(local)

Run java application package with command line ``` Java -jar XXXX.jar ```

Test it with the link :
[http://localhost:8080/api/welcome](http://localhost:8080/api/welcome)


and get the response.

``` Hello, this is BTP SAP JAVA Simple Demo```

After that , this whole java applciation is working fine

### Deployment

Deploy for BTP:
1. set cloud foundry endpoint
   command :

      ```cf api {EndpointURL} ```

   **EndpointURL** you can find in your subaccount :
   ![APIEndPoint](/img/APIEndPoint.png)

2. login to your BTP endpoint with your btp user and password
   command :

      ```cf login ```

3. add manifest.yml for CF BTP development
4. configure the [route](https://help.sap.com/viewer/65de2977205c403bbc107264b8eccf4b/Cloud/en-US/53daaafe8f8345fc9b8497b86d17c9d9.html?q=routes) and [java buildpacks](https://help.sap.com/viewer/65de2977205c403bbc107264b8eccf4b/Cloud/en-US/a3f90069d6cd41da82f34a6123d82ce6.html)

here we suggest use bellow format as recomendation :

 ```
   {subdomain}-{appname}.{cfappdoman}
 ```

**subdomain:** 

![subdomain](/img/subdomain.png)

**appname**: defined by yourself

**cfappsdomain:** user the command ```cf domains ``` to get the domain url

![cfappdomain](/img/cfappdoman.png)

Example:

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
5. Deploy your jar packges to your BTP Subaccount
   
   command :
   
    ```cf push ```

### Test(BTP subaccount)

1. Navigte to your space
   
 ![space](/img/space.png)

2. Go you applcation 
   
 ![space_application](/img/space_application.png)

3.  Get applicaiton URL
   
 ![applicationOverview](/img/applicaiton_overview.png)

4. Test it with the URL 
   
   ```
   {applicaitonURL}/api/welcome
   ```

   After get the below response, then your application is runtime fine

   ``` Hello, this is BTP SAP JAVA Simple Demo```

### Reference

SpringBoot Rest Service Guide : [SpringBoot Rest Service Guide](https://spring.io/guides/gs/rest-service/)

SAP BTP JAVA Development: [Developing Java in the Cloud Foundry Environment](https://help.sap.com/viewer/65de2977205c403bbc107264b8eccf4b/Cloud/en-US/a3f90069d6cd41da82f34a6123d82ce6.html)


