import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

	double input;
	String unit;
	double output;
	String errorMessage;

	@Given("the {word} is {double} {word}")
	public void init(String measure, double input, String unit) {
		this.input = input;
		this.unit = unit;
	}

	@Given("the {word} is {word} infinity")
	public void initInfinity(String measure, String sign) {
		if (sign.equals("positive")) {
			this.input = Double.POSITIVE_INFINITY;
		} else {
			this.input = Double.NEGATIVE_INFINITY;
		}
		this.unit = "F";
	}

	@Given("the {word} is {word} infinity {word}")
	public void initInfinityUnit(String measure, String sign, String unit) {
		if (sign.equals("positive")) {
			this.input = Double.POSITIVE_INFINITY;
		} else {
			this.input = Double.NEGATIVE_INFINITY;
		}
		this.unit = unit;
	}

	@Given("the {word} is not a number")
	public void initNaN(String measure) {
		this.input = Double.NaN;
		this.unit = "F";
	}

	@When("the temperature is converted")
	public void convertTemp() {
		try {
			this.output = Conversions.convertTemp(input, unit);
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
		}
	}

	@When("the temperature is converted {int} times")
	public void convertTempRepeated(int times) {
		double temp = input;
		for (int i = 0; i < times/2; i++) {
			temp = Conversions.convertTemp(temp, "F");
			temp = Conversions.convertTemp(temp, "C");
		}
		this.output = temp;
	}

	@When("the distance is converted")
	public void convertDistance() {
		try {
			this.output = Conversions.convertDistance(input, unit);
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
		}
	}

	@When("the distance is converted {int} times")
	public void convertDistanceRepeated(int times) {
		double distance = input;
		for (int i = 0; i < times/2; i++) {
			distance = Conversions.convertTemp(distance, "F");
			distance = Conversions.convertTemp(distance, "C");
		}
		this.output = distance;
	}

	@Then("the converted {word} is {double}")
	public void checkOutput(String measure, double expectedOutput) {
		assertEquals(expectedOutput, this.output);
	}

	@Then("the converted {word} is {double} {word}")
	public void checkOutputUnit(String measure, double expectedOutput, String unit) {
		assertEquals(expectedOutput, this.output);
	}

	@Then("an error message states {string}")
	public void checkError(String expectedMessage) {
		assertEquals(expectedMessage, this.errorMessage);
	}
}