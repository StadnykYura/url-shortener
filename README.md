# Spring Boot web-app 'url-shortener'

Application receives original URL and returns a shortened URL using base62 hashing.

The application is developed using Spring Boot, JPA/Hibernate core, and H2 in-memory database.

# Build and run
1. Check out the project source code from github;
2. Build an url-shortener.jar file using maven in console or with any IDE you like.
3. Run <app_name>.jar file with console help or any IDE you like.
By default, an embedded Tomcat Server within spring boot app will run on localhost:8080/url-shortener/
4. To test: 

 - send POST Request using POSTMAN to localhost:8080/url-shortener/to-shorten with a body of type application/json: 

```
{
  'urlToShorten' : '<INSERT ORIGINAL URL>'
}
```

If the original url, is correct, you will receive a Response as a shortened URL representation, which can be used to redirect user to the original url inserted in previous request.

 - then just put this shortened URL into browser or POSTMAN as GET request and you will be redirected with this web-app to original URL.

5. All successfully shortened original URLs are saved in H2 inmemory DB which can be reached with localhost:8080/url-shortener/h2/console in browser
 - in opened window within login form enter 'jdbc:h2:mem:testdb' into field 'JDBC URL' and click 'Connect';
 - click on "URL_DATA" table in left top corner and click 'Run' to run a select statement to see all table rows;