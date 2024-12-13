# Currency Conversion Service

Current conversion service developed using Spring Boot and microservices.

The code is based on the course material of [In28Minutes Master Microservices with Spring Boot and
Spring Cloud](https://github.com/in28minutes/spring-microservices-v3).

Major revision including

1. Use multi-module in order to enable module dependency.
2. Adopt Lombok and reduce boiler plates.
3. Application properties adjustment to ensure the reachability of all microservices (when not using
   docker compose).

## URLs

### Eureka Naming Service

- http://localhost:8761/

### Currency Exchange Service

- http://localhost:8000/currency-exchange/from/USD/to/INR

### Currency Conversion Service

- http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
- http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10

### API Gateway

- http://localhost:8765/currency-exchange/from/USD/to/INR
- http://localhost:8765/currency-conversion/from/USD/to/INR/quantity/10
- http://localhost:8765/currency-conversion-feign/from/USD/to/INR/quantity/10
- http://localhost:8765/currency-conversion-new/from/USD/to/INR/quantity/10

### Zipkin

- http://localhost:9411/

## Build

In project root,

1. install modules to local repository
   ```shell
   mvn dependency:resolve
   ```

2. update a module in local repository
   ```shell
   mvn install -pl currency-exchange-service -am
   ```

## Deployment

1. Start zipkin server
   ```shell
   docker run -p 9411:9411 openzipkin/zipkin:3.4
   ```
2. Start Eureka naming service
3. Start Currency Exchange Service
4. Start Currency Conversion Service
5. Start API Gateway
