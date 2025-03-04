# CalculatorChallenge_Java

CalculatorChallenge_Java is an implementation of a programming interview challenge ported to Java as an exercise.

## Installation

CalculatorChallenge is built using JDK v21 and Gradle.

You can build the project using:

```bash
gradle clean bootjar
```

And run the web api applicaiton:

```bash
java -jar "./build/libs/calculatorchallenge-0.0.1-SNAPSHOT.jar"
```

## Docker

This application can also be built into a container image and run using docker.

Build the image with:
```bash
docker build . -t Calculator
```

And run the resultant image with:
```bash
docker run -p 8080:8080 calculator
```

## Usage

The restful request must be a get request with a string body that is a syntaxicly correct mathematical formula in a string format.
There is support for:
* Rational numbers (Negitive, Positive, Decimal)
* Addition, Subtraction, Division, Multiplication, Exponents
* Parenthises

```bash
curl -X GET 'http://localhost:8080/calculate' --header 'Content-Type: application/json' --data-raw '5+5'
```