Feature: Convert distance between Imperial and metric units

	Scenario Outline: Convert distance into opposite units
  		Given the distance is <distance> <unit>
 		When the distance is converted
  		Then the converted distance is <output>

		Examples:
			| distance |  unit  |          output         |
			|     5    |   mi   |         8.04672         |
			|     5    |   km   |    3.1068559611866697   |
			|     5    |   ft   |           1.524         |
			|     5    |    m   |    16.404199475065617   |
			|     5    |   in   |           12.7          |
			|     5    |   cm   |     1.968503937007874   |
			|   2E100  |   mi   |       3.218688E100      |
			|   2E100  |   km   |  1.242742384474668E100  |
			|   2E100  |   ft   |          6.096E99       |
			|   2E100  |    m   |  6.5616797900262466E100 |
			|   2E100  |   in   |          5.08E100       |
			|   2E100  |   cm   |   7.874015748031496E99  |
			|  2E-100  |   mi   |      3.218688E-100      |
			| 1.79E308 |   km   |  1.1122544341048278E308 |

	Rule: Repeated conversion of a distance maintains precision,
		  allowing original value to be recovered

		Scenario: Convert the same distance twice
			Given the distance is 5 mi
			When the distance is converted 2 times
			Then the converted distance is 5 km

		Scenario: Convert the same distance many times
			Given the distance is 5 mi
			When the distance is converted 100 times
			Then the converted distance is 5 km

	Rule: Converting a distance fails if the calculation exceeds the maximum or minimum
		  value that the computer can store

		Scenario: Convert a very large distance
			Given the distance is 1.7E308 mi
			When the distance is converted
			Then an error message states "There was an error performing this calculation"

		Scenario: Convert a very small distance
			Given the distance is -1.7E308 mi
			When the distance is converted
			Then an error message states "There was an error performing this calculation"

		Scenario: Convert a distance very close to zero
			Given the distance is 4.9E-324 ft
			When the distance is converted
			Then an error message states "There was an error performing this calculation"


	Rule: Conversion of an invalid distance value fails

		Scenario: Distance is positive infinity
			Given the distance is positive infinity
			When the distance is converted
			Then an error message states "Invalid distance"

		Scenario: Distance is negative infinity
			Given the distance is negative infinity
			When the distance is converted
			Then an error message states "Invalid distance"

		Scenario: Distance is not a number
			Given the distance is not a number
			When the distance is converted
			Then an error message states "Invalid distance"


	Rule: Conversion of a valid distance with an invalid unit fails

		Scenario: Unit is not provided
			Given the distance is 5 ""
			When the distance is converted
			Then an error message states "Invalid distance unit"

		Scenario: Unit is unsupported
			Given the distance is 5 mm
			When the distance is converted
			Then an error message states "Invalid distance unit"

	
	Rule: Conversion of an invalid distance with an invalid unit fails

		Scenario: Infinite distance and invalid unit
			Given the distance is positive infinity mm
			When the distance is converted
			Then an error message states "Invalid distance"
	

