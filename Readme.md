## Architecture

There are 3 domain models.
1. Books - The books in stock (bookname and number in stock).
2. Customer - The customers on the platform
3. Orders - The orders by a customer on the platform.

### Relationships

There is a Many to One relationship between orders and customer. 
A customer can have many orders.

There is a Many to Many relationship between orders and books.
A book can belong in many different orders. An order can contain many books.

### Postman Test Collection
https://www.getpostman.com/collections/087a954348323af60793

### Swagger/OpenApi Url
{{baseurl}}/swagger-ui/index.html e.g http://localhost:8080/swagger-ui/index.html 

### Stack
Spring Boot, H2 (database).

### To Run
mvn spring-boot:run
