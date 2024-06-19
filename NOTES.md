Modular Monolith vs Microservices:

https://medium.com/codex/what-is-better-modular-monolith-vs-microservices-994e1ec70994
Java Spring Boot - How to Create Maven Multi Module Project:
https://www.youtube.com/watch?v=od6HHvuxgAo&list=PLJyMAT_Wb6qp9RiusxeWxvsfi7VeZtdcY

//--------------------------------------------------------------------------------------
SECURITY:

My solution is based on: 2024 01. 30:
https://www.youtube.com/watch?v=RnZmeczS_DI&t=11s

USEFUL -> To start:
2024 ápr. 12.:(!!!!!)
Users management Portal using Spring-boot, React.js, Mysql | Spring-Security, JWT, Roles, Auth
https://www.youtube.com/watch?v=5_5oBtHWA5I or
https://www.youtube.com/watch?v=GH7L4D8Q_ak

Username/Password Authentication:
https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/index.html#servlet-authentication-unpwd


More details: 
csfr->
https://portswigger.net/web-security/csrf

authorizeHttpRequests->
This tells Spring Security that any endpoint in your application requires that the security context a
t a minimum be authenticated in order to allow it.
https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html#request-authorization-architecture

!!!! https://medium.com/javarevisited/spring-boot-securing-api-with-basic-authentication-bdd3ad2266f5

https://www.linkedin.com/pulse/jwt-token-api-authentication-authorization-haroon-idrees/
https://techdocs.akamai.com/api-definitions/docs/json-web-token-jwt-val
https://medium.com/@thecodebean/implementing-jwt-authentication-in-a-spring-boot-application-5a7a94d785d1

//--------------------------------------------------------------------------------------
Logout:

How to Logout from Spring Security - JWT:
Bouali Ali: https://www.youtube.com/watch?v=0GGFZdYe-FY

//--------------------------------------------------------------------------------------
Cookie: 

Cookie-based JWT Authentication with Spring Security:
https://medium.com/spring-boot/cookie-based-jwt-authentication-with-spring-security-756f70664673

//--------------------------------------------------------------------------------------
ResponseEntity, Spring ResponseEntity : Customizing the response in Spring Boot:

https://technicalsand.com/using-responseentity-in-spring/
ResponseEntity is used when you need to change HTTP headers or HTTP status code based upon your business logic or incoming request. 
ResponseEntity wraps the original object as its body which is optional. 

Dan Vega: https://www.youtube.com/watch?v=B5Zrn1Tzyqw
Telusko: https://www.youtube.com/watch?v=yrfY3zCJ_tA //ResponseEntity<>("success", HttpStatus.CREATED) -> 201
https://www.youtube.com/watch?v=qo56g2PlS5o

//--------------------------------------------------------------------------------------
Error Handling for REST with Spring

https://www.baeldung.com/exception-handling-for-rest-with-spring
https://naveen-metta.medium.com/mastering-exception-handling-in-spring-boot-a-comprehensive-guide-fa3f916d1981
https://dev.to/tienbku/global-exception-handler-in-spring-boot-3mbp

//--------------------------------------------------------------------------------------
Retrieve User Information in Spring Security:

https://www.baeldung.com/get-user-in-spring-security
https://www.javaguides.net/2024/04/spring-security-principal.html

Principal represents the user's identity, which can be the username, a user object, or any form of user identification. 
It is a key reference for making authorization decisions and customizing user interactions based on the authenticated user's details.
//--------------------------------------------------------------------------------------

Spring Data JPA One-To-One Bidirectional Relationship:

https://medium.com/@bectorhimanshu/spring-data-jpa-one-to-one-bidirectional-relationship-9ef3674ec2d7
https://www.javaguides.net/2022/02/spring-data-jpa-one-to-one-bidirectional-mapping.html
(to which field belong the "mappedBy" its Repository will store the whole: .save())

//--------------------------------------------------------------------------------------

ModelMapper:

https://www.baeldung.com/java-modelmapper
https://www.geeksforgeeks.org/how-to-use-modelmapper-in-spring-boot-with-example-project/

//--------------------------------------------------------------------------------------

Dynamically ignore fields while serializing Java Object to JSON:

https://iamvickyav.medium.com/spring-boot-dynamically-ignore-fields-while-converting-java-object-to-json-e8d642088f55

//--------------------------------------------------------------------------------------

partial data update:
https://ololx.medium.com/partial-data-update-in-java-rest-services-1-332fdc621631
//--------------------------------------------------------------------------------------

PAGINATION:

https://www.youtube.com/watch?v=6ur2DU9jyc0&list=PLoyb0HJlmv_kKDuTCZqBwUrKDXbGLPriw&index=3
https://dev.to/brunbs/how-to-return-paginated-data-in-spring-boot-11cm
//--------------------------------------------------------------------------------------

Fullstack:

Users management Portal using Spring-boot, React.js, Mysql | Spring-Security, JWT, Roles, Auth:
https://www.youtube.com/watch?v=5_5oBtHWA5I

React js Live Search With Real API | React js Live Search with Hooks | React Js tutorial:
https://www.youtube.com/watch?v=7T75wqx1tHg
//--------------------------------------------------------------------------------------

Lazy:
https://www.baeldung.com/spring-boot-lazy-initialization
//--------------------------------------------------------------------------------------

Cache:
https://www.youtube.com/watch?v=fZilHxyj39Q
//--------------------------------------------------------------------------------------

Guide to Spring Email:
https://www.baeldung.com/spring-email
