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



## Installation
*  Clone the project.
*  `cd compose`
*  `docker-compose up -d`