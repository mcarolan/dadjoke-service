# Dad Joke Service

This is a back end service that exposes dad jokes over a HTTP endpoint. It was built to demonstrate Consumer Driven Contract Testing.

It uses principles of hexagonal architecture to allow the service to be cleanly stubbed out for the contract test. The main interesting place for explorers is [PactTest.java](https://github.com/mcarolan/dadjoke-service/blob/main/src/test/java/net/mcarolan/dadjoke/PactTest.java).
This is the test in which consumer expectations are verified.

## How to run

```bash
$ mvn clean install
$ mvn exec:java -Dexec.mainClass="net.mcarolan.dadjoke.App"
```