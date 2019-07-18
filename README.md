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


## Usage
The exposed endpoints are:

| Endpoints | Description |
| ------   | ------       |
| `GET` localhost:5000/transactions?fileName=data.xml |  Customizable endpoint - possible value for fileName are: data.csv, data.xml, bad.csv |
| `GET` localhost:5000/transactions/xml | For getting data.xml   |   
| `GET` localhost:5000/transactions/csv | For getting data.csv |   



