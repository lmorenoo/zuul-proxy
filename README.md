# Set up
The human-resource-api service is a Spring application app that expones service for employees, positions and candidates using h2

zuul microservice use https://github.com/spring-cloud/spring-cloud-netflix to forward request to the service application and log the request and headers

## human-resource-api
Java 11 \
Maven \
Server port 8787

## zuul
Java 11 \
Maven \
Server port 8080

### Logs HTTP request
zuul app logs the request and headers that forward to human-resource-api. See SimpleFilter.java

## Example using zuul to call human-resource-api

### Create position
curl --location --request POST 'http://localhost:8080/human-resource-api/positions' \
--header 'accept: */*' \
--header 'Content-Type: application/json' \
--data-raw '{"name":"dev"}'

### Get positions
curl --location --request GET 'http://localhost:8080/human-resource-api/positions' \
--header 'accept: */*'
