# Warehouse Service

REST API for managing item warehouse.

### Technologies used:

Java 11, Spring Boot, JPA, H2 database, JUnit5, Maven, Docker

### How to run:

Run with Docker: `docker run -p {your-port}:8080 gvabal/warehouse-service`

Run with Maven: `./mvnw package && java -jar target/*.jar`

----
### Endpoints:

**GET /api/items** - returns all items.

**GET /api/items/{id}** - returns item with specified id.

**POST /api/items +Item {"name":String, "quantity":Integer, "expirationDate":"yyyy-mm-dd"}** - adds new item.

**PUT /api/items/{id} +Item {"name"?:String, "quantity"?:Integer, "expirationDate"?:"yyyy-mm-dd"}** - edits item with specified id.

**DELETE /api/items/{id}** - removes item.

**GET /api/items/withQuantityLessThan/{amount}** - returns all items with quantity less than specified amount.

**GET /api/items/withExpirationDateBefore/{date}** - returns all items with expiration date before specified date. Date has to be formated like "yyyy-mm-dd".

----
### Example requests and responses (assuming application is running locally on port 8080):

* Add new item: 

`curl -X POST -H "Content-Type: application/json" -d '{"name":"Milk", "quantity":50, "expirationDate":"2020-10-01"}' http://localhost:8080/api/items`

Response:

![Screenshot 2020-09-24 at 18 53 21](https://user-images.githubusercontent.com/49102436/94169227-3e7b3480-fe97-11ea-93af-748aa1fc6742.png)

* Find out of stock items: 

`curl -X GET http://localhost:8080/api/items/withQuantityLessThan/1`

Response:

![Screenshot 2020-09-24 at 18 57 11](https://user-images.githubusercontent.com/49102436/94169737-d6791e00-fe97-11ea-96e4-291190e757f3.png)

![Screenshot 2020-09-24 at 18 57 33](https://user-images.githubusercontent.com/49102436/94169782-e3960d00-fe97-11ea-99f9-fe8ecda90c80.png)
