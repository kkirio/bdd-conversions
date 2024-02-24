Feature: Convert temperature between F and C

	Scenario Outline: Convert temperature into opposite units (F <> C)
  		Given the temperature is <temp> <unit>
 		When the temperature is converted
  		Then the converted temperature is <output>

		Examples:
			|   temp   | unit |   output   |
			|     5    |   F  |    -15     |
			|    30    |   C  |     86     |
			|  3.6E100 |   F  |   2.0E100  |
			|  3.6E100 |   C  |  6.48E100  |

	Rule: Repeated conversion of a temperature maintains precision,
		  allowing original value to be recovered

		Scenario: Convert the same temperature twice
			Given the temperature is 5 F
			When the temperature is converted 2 times
			Then the converted temperature is 5 F

		Scenario: Convert the same temperature many times
			Given the temperature is 5 F
			When the temperature is converted 100 times
			Then the converted temperature is 5 F


	Rule: Converting a temperature fails if the calculation exceeds the maximum or minimum
		  value that the computer can store

		Scenario: Convert a very large temperature
			Given the temperature is 1.7E308 C
			When the temperature is converted
			Then an error message states "There was an error performing this calculation"

		Scenario: Convert a very small temperature
			Given the temperature is -1.7E308 C
			When the temperature is converted
			Then an error message states "There was an error performing this calculation"


	Rule: Conversion of an invalid temperature value fails

		Scenario: Temperature is positive infinity
			Given the temperature is positive infinity
			When the temperature is converted
			Then an error message states "Invalid temperature"

		Scenario: Temperature is negative infinity
			Given the temperature is negative infinity
			When the temperature is converted
			Then an error message states "Invalid temperature"

		Scenario: Temperature is not a number
			Given the temperature is not a number
			When the temperature is converted
			Then an error message states "Invalid temperature"


	Rule: Conversion of a valid temperature with an invalid unit fails

		Scenario: Unit is not provided
			Given the temperature is 5 ""
			When the temperature is converted
			Then an error message states "Invalid temperature unit"

		Scenario: Unit is unsupported
			Given the temperature is 5 K
			When the temperature is converted
			Then an error message states "Invalid temperature unit"

	
	Rule: Conversion of an invalid temperature with an invalid unit fails

		Scenario: Infinite temperature and invalid unit
			Given the temperature is positive infinity K
			When the temperature is converted
			Then an error message states "Invalid temperature"
	

