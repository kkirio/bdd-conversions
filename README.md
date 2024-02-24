# BDD Automated Software Tests

`Conversions` is a simple class which converts between metric/Imperial units for temperature and distance.

Tests for this class include the normal path, repeated conversions, extreme valid inputs and invalid inputs.

### Usage
Run `mvn clean test`

An HTML report will be generated in target/cucumber. For other reporting options, see [Cucumber docs](https://cucumber.io/docs/cucumber/reporting/?sbsearch=HTML+reports&lang=java).

![Report Excerpt](https://github.com/kkirio/bdd-conversions/report-excerpt.png)