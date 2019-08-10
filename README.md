# Transactions Processor

## Summary
Suppose you are part of a scrum team developing a component “Transaction Processor” that
processes financial transactions.

The Transaction Processor allows users to import financial transactions (each transaction is
either of type debit or credit), it shall handle transactions in different formats; namely CSV
(comma-separated values) and XML. Once the transactions are imported, the system can validate
them and reports back any violation; Violation is defined in case that file contains an invalid
transaction record e.g. Invalid transaction type, missing amount...etc. The component can also
check if the loaded transactions are balanced or not; the balanced transactions means that sum of
Credit transactions must equals the sum of Debit transactions



## Installation via docker compose
*  Clone the project.
* `mvn install`
* `docker-compose up -d`
* Hosted on `localhost:5000`


## Installation via deployments
* `kubectl create -f deployment-config.yaml`
* Hosted on `<pods-ip>:8080`. Get the pods ip using `minikube dashboard` 

## Installation via service
* `kubectl create -f service-config.yaml`.
* Hosted on `<cluster-ip>:8080`.


## HELM
* `helm init --history-max 200`
* `helm install my-app --name my-app`


## Usage
The exposed endpoints are:

| Endpoints | Description |
| ------   | ------       |
| `GET` localhost:5000/transactions?fileName=data.xml |  Customizable endpoint - possible value for fileName are: data.csv, data.xml, bad.csv |
| `GET` localhost:5000/transactions/xml | For getting data.xml   |   
| `GET` localhost:5000/transactions/csv | For getting data.csv |   

----------
-----------

## MySQL
This application uses MySQL to store logs of endpoints hit counts. 
The logging persistence is handled using Spring AOP (with the help of `@ShouldBeLogged` annotation).

### MySQL Docker 
```shell script
docker run -p 3306:3306 --name=mysql-server --env="MYSQL_ROOT_PASSWORD=123456" mysql
docker exec -ti mysql-server bash 
mysql -u root -p
CREATE DATABASE testdb
```

The application.properties is:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL55Dialect
```

### MySQL Helm
```shell script
  helm install --name my-sql \
  --set mysqlRootPassword=secretpassword,mysqlUser=my-user,mysqlPassword=my-password,mysqlDatabase=testdb \
    stable/mysql
```


## References
* [MYSQL DockerHub](https://hub.docker.com/_/mysql)
* [Spring AOP](https://www.springboottutorial.com/spring-boot-and-aop-with-spring-boot-starter-aop)
* [MySQL Docker setup](https://itnext.io/create-a-mysql-server-with-docker-55ea405f64b0)
* [MySQL Helm Git](https://github.com/helm/charts/tree/master/stable/mysql)
