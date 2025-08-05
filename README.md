<h1>2018 Spring Boot Source 2.1.2</h1>
<img width="516" height="145" alt="Image" src="https://github.com/user-attachments/assets/96534a0b-b471-4144-922d-4e14e4d43137" />
<h2>= Java Spring Boot site and news =</h2>
The 2018, first iteration of the news site was built in Java Spring Boot and Apache Tomcat and a deployment script I wrote in Python.  In my opinion, Java Spring has the best security framework out of all the languages and frameworks ever used which made it very hard to migrate away from.  Much of the work was standard Bcrypt encryption Spring Security using Gradle for builds instead of maven and a vanilla / jQuery javascript frontend before moving to an older version of React.
<br/>
<img width="400" height="400" alt="Image" src="https://github.com/user-attachments/assets/2c923254-da66-43bf-90a4-b91ecfeaed2c" />
<br/>
The original newsfeed implementation for Java Spring Boot 2.1.2 used the gson library for the Rest API calls.    Since then, all projects were ported to Go which I personally found much more productive in regards to API design and testing as seen here: https://www.gallantone.com/news
