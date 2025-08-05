<h1>GallantMedia Spring Boot Source 2.1.2</h1>
<img width="516" height="145" alt="Image" src="https://github.com/user-attachments/assets/96534a0b-b471-4144-922d-4e14e4d43137" />
<h2>= Java Spring Boot site and news =</h2>
The first iteration of the news site was built in Java Spring Boot and Apache Tomcat.  In my opinion, Java Spring has the best security framework which made it hard to migrate away from.  Much of the work was standard Bcrypt encryption Spring Security using Gradle for builds instead of maven and a vanilla javascript frontend before moving to an older version of React.
<br/><br/>
The original newsfeed implementation for Java Spring Boot 2.1.2 used vebose gson libraries for the Rest API calls.    Since then, all projects were ported to Go which I personally found much more productive in regards to API design and testing.
