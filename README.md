# 02组JAVA-哈工大企业实训
## 一.项目概述

**本项目为一个餐厅点单系统**

**实现的功能:**

**用户注册和登陆**

**用户信息,菜单,订单的增删改查**

**项目的架构:**

**数据库:Mysql**

**后端:Spring+SpringMVC+Mybatis**

**前端:vue**

## 二.目录结构

|--ssm	后端代码

​		|--src/main	源文件

​				|--java	java源代码

​						|--controller	控制器

​						|--dao	数据访问层

​						|--interceptor	拦截器

​						|--po	持久化层

​						|--service	业务逻辑层

​						|--utils	工具类

​				|--resource

​						|--keystore.p12	用于https的证书文件

​						|--applicationContext.xml	spring的配置文件

​						|--mybatisConfig.xml	mybatis的配置文件

​						|--db.properties	数据库的配置文件

​				|--webapp	静态资源/springMVC的配置文件

​		|--pom.xml	maven配置文件

|--vue	后端代码

​		|--src	源文件

​				|--components	组件

​				|--router	路由

​				main.js

​				App.vue

## 三.具体实现

### DAO层实现

```xml
Mybatis
MyBatis 是一款优秀的持久层框架，它支持自定义 SQL、存储过程以及高级映射。MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作。MyBatis 可以通过简单的 XML 或注解来配置和映射原始类型、接口和 Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。

lombok
对POJO进行注解,自动生成setter,getter,无参有参构造,toString等方法
        <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.20</version>
            <scope>provided</scope>
        </dependency>

//PO 持久化类
@Data
public class User {
    private int userID;
    private String username;
private String password;
}

//Dao接口
public interface UserDao{
	List<User> getUserList();
}

<!--		Mapper Mybatis映射文件-->
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="mapper.UserMapper">
    <select id="getUserList" resultType=" User">
        select * from user;
    </select>
</mapper>

<!--加载配置文件-->
    <context:property-placeholder location="classpath:config/db.properties"/>
<!--    加载数据源-->
<bean id="dataSource" 
class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>

<!--    配置Mybatis工厂的SqlsessionFactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="configLocation" value="classpath:config/mybatisConfig.xml"/>
</bean>

<!--    配置Mapper扫描器-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="dao"/>
</bean>

Mybatis多表联合查询
查询结果嵌套
    <resultMap id="map" type="po.Order">
        <result property="id" column="id"/>
        <result property="customer" column="customer"/>
        <result property="cook" column="cook"/>
        <result property="isdone" column="isdone"/>
        <result property="total" column="total"/>
        <collection property="items" javaType="List" ofType="po.Menu">
            <result column="random"/>
            <result property="menuid" column="menuid"/>
            <result property="name" column="name"/>
            <result property="details" column="details"/>
            <result property="price" column="price"/>
        </collection>
</resultMap>
SQL多表联合查询
<select id="getOrderList" resultMap="map" resultType="po.Order">
        select *
        from orders,
             orderitem,
             menu
        where orders.id = orderitem.orderid
          and menu.menuid = orderitem.menuid
    </select>
Spring实现事务管理
<!--    事务管理器-->
<bean id="transactionManager" 
class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>
<!--    开启事务注解-->
    <tx:annotation-driven transaction-manager="transactionManager"/>

```

### Service层实现

```xml
Service层实现主要的服务,将一个或多个Dao进行封装,这里不再是原子操作,需要事务控制.

<!--    扫描service-->
<context:component-scan base-package="service"/>

Service层接口
 
Service层实现类
 
Service层使用@Service进行注解
@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserDao userDao;

    @Override
    public String checkLogin(String username, String password) {
        if (username != null && password != null) {
            User user = userDao.getUserByUsername(username);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    return JwtUtil.sign(username);
                }
            }
        }
        return null;
    }
}
Spring的事务控制
<!--    事务管理器-->
<bean id="transactionManager" 
class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>
<!--    开启事务注解-->
<tx:annotation-driven transaction-manager="transactionManager"/>

使用注解@Transactional进行事务管理
Spring 实现事务的注解@Transactional 是可以被继承的，
通过查看它的源码可以看到@Inherited。

```

### Controller实现

```xml
使用Controller注解,指示Spring,类是一个控制器, 使用该注解后不需要实现Controller接口,只需要通过Spring的扫描机制找到即可.
<!--    配置包扫描器,扫描@Controller注解的类-->
<context:component-scan base-package="controller"/>

注解
@RequestMapping
 
还有@PostMapping和@GetMapping等注解

数据绑定
 
Restful风格
在http请求中,使用put,delete,post,get方式分别对应添加,删除,修改,查询操作

```

### Interceptor实现

```java
<!--    配置视图解析器-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <property name="suffix" value=".jsp"/>
</bean>
<!--    注册拦截器-->
    <mvc:interceptors>
        <mvc:interceptor>
            <mvc:mapping path="/**"/>
            <mvc:exclude-mapping path="/login"/>
            <mvc:exclude-mapping path="/register"/>
            <bean class="interceptor.TokenInterceptor"/>
        </mvc:interceptor>
    </mvc:interceptors>
</beans>

拦截器需要实现HandlerInterceptor接口或者继承HandlerInterceptor接口的实现类
在每次请求前,验证Header携带的token
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("******进入拦截器******");

        //放行OPTIONS请求
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            System.out.println("放行OPTIONS请求");
            return true;
        }

        String token = request.getHeader("token");
        System.out.println("token::"+token);
        boolean b = token != null && JwtUtil.verify(token);
        System.out.println(b);
        return b;
    }
}


HandlerInterceptor接口的方法
 

token与cookie相比较的优势
1、支持跨域访问，将token置于请求头中，而cookie是不支持跨域访问的；
2、无状态化，服务端无需存储token，只需要验证token信息是否正确即可，而session需要在服务端存储，一般是通过cookie中的sessionID在服务端查找对应的session；
3、无需绑定到一个特殊的身份验证方案（传统的用户名密码登陆），只需要生成的token是符合我们预期设定的即可；
4、更适用于移动端（Android，iOS，小程序等等），像这种原生平台不支持cookie，比如说微信小程序，每一次请求都是一次会话，当然我们可以每次去手动为他添加cookie，详情请查看博主另一篇博客；
5、避免CSRF跨站伪造攻击，还是因为不依赖cookie；

Token的实现
//token私钥
private static final String TOKEN_SECRET = "f26e587c28064d0e855e72c0a6a0e618";
/**
     * 生成签名
     * @param username 用户名
     * @return 加密的token
     */
public static String sign(String username) {
        try {
//            过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
//            私钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
//            设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            // 附带username，userId信息，生成签名
            return JWT.create()
                    .withHeader(header)
                    .withClaim("username", username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
}

    /**
     * 校验token是否正确
     * @param token 密钥
     * @return 是否正确
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

```

### Https实现

```xml
证书生成
Keytool是一个Java数据证书的管理工具,Keytool将密钥（key）和证书（certificates）存在一个称为keystore的文件中。
构建自签名证书:
keytool -genkeypair -keyalg RSA -keysize 1024 -sigalg MD5withRSA -va
lidity 365 -alias bo -keystore bo.keystore
生成keystore.p12文件

Tomcat配置
     <Connector port="8443" protocol="HTTP/1.1" SSLEnabled="true"
               maxThreads="150" scheme="https" secure="true"
               clientAuth="false" sslProtocol="TLS"
               keystoreFile="C:\keystore.p12"
                keystorePass="123456" />

SpringMVC配置
对所有请求转换成https请求
    <security-constraint>
        <!-- Authorization setting for SSL -->
        <web-resource-collection >
            <web-resource-name >SSL</web-resource-name>
            <url-pattern>/*</url-pattern>
        </web-resource-collection>
        <user-data-constraint>
            <transport-guarantee>CONFIDENTIAL</transport-guarantee>
        </user-data-constraint>
</security-constraint>


测试访问
能够通过https访问
 
前端也能通过https访问
 

```

### Vue每次请求携带Token

```js
vue 实现每次请求携带Token
在登陆完成后,在session中保存token
window.sessionStorage.setItem("token",res.token)
在main.js文件中设置拦截器,在请求头中添加token
import axios from "axios";
axios.defaults.baseURL="https://127.0.0.1:8443/"
axios.interceptors.request.use(config=>{
    console.log(config)
    config.headers.token=window.sessionStorage.getItem('token')
    console.log(config)
    return config
})
Vue.prototype.$http=axios
```

