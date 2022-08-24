## Product Registration


### Used technologies

This project was developed with:

* **Java SE 18 LTS**
* **Spring Boot 2.7.3**
* **Maven**
* **PostgresSql 13**


### Execution

~~~~ 
git clone https://github.com/v-venturi/product-registration.git

./mvnw spring-boot:run
~~~~

## Features

This API provides HTTP endpoint's and tools for the following:

Items:
* Create an item: `POST/http://localhost:8080/api/items'
* List all items: `GET/http://localhost:8080/api/items'
* List specific item: `GET/http://localhost:8080/api/items/{itemId}'
* Update an item: `PUT/http://localhost:8080/api/items/{itemId}'
* Delete an item: `DELETE/http://localhost:8080/api/items/{itemId}'

Order:
* Create an order: `POST/http://localhost:8080/api/orders'
* List all orders: `GET/http://localhost:8080/api/orders'
* Update an order: `PUT/http://localhost:8080/api/orders/{orderId}'
* List an order: `GET/http://localhost:8080/api/orders/{orderId}'
* Delete an order: `DELETE//http://localhost:8080/api/orders/{orderId}'
* Close order: `POST//http://localhost:8080/api/orders/{orderId}/close'

OrderItems:
* Create an orderItem: `POST/http://localhost:8080/api/orderItems/{orderId}/items'
* List all orderItems: `GET/http://localhost:8080/api/orderItems'
* List an orderItem: `PUT/http://localhost:8080/api/orderItems/{orderId}/items/{itemId}
* Update an orderItem: `PUT/http://localhost:8080/api/orderItems/{orderId}/{itemId}'
* Delete an orderItem: `DELETE//http://localhost:8080/api/orderItems/{orderId}/{itemId}'






* **Postman** collection link to test the API 
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/64885b5229d4c5853989?action=collection%2Fimport)
* HerokuAPI  Deployment  https://product-registration.herokuapp.com/api/items