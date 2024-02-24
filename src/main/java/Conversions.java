import java.util.Objects;

public class Conversions {

	/**
	 * Converts a temperature from Fahrenheit to Celcius or from Celcius to Fahrenheit.
	 * 
	 * @param temp                      the temperature to be converted
	 * @param unit                      the unit of the temperature argument.
	 *                                  Accepts F, C.
	 * @return                          the temperature converted to the opposite scale
	 * @throws ArithmeticException      if the calculation results in overflow/underflow
	 * @throws IllegalArgumentException if temperature is not a real number 
	 *                                  or the unit is not supported
	 */
	public static double convertTemp(double temp, String unit) throws IllegalArgumentException, ArithmeticException {

		if (!Double.isFinite(temp)) {
			throw new IllegalArgumentException("Invalid temperature");
		}
				
		// Convert depending on input unit
		double output;
		if (Objects.equals(unit, "F")) {
			output = (temp-32) / 1.8;
		} else if (Objects.equals(unit, "C")) {
			output = temp*1.8 + 32;
		} else {
			throw new IllegalArgumentException("Invalid temperature unit");
		}

		// Check for overflow/underflow
		return checkArithmetic(output);
	}

	/**
	 * Converts a unit of distance from Imperial to metric or from metric to Imperial. 
	 * Maintains the approximate magnitude of the input unit (mi <> km, ft <> m, in <> cm)
	 * 
	 * @param distance                  the distance to be converted
	 * @param unit                      the unit of the distance argument.
	 *                                  Accepts mi, km, ft, m, in, cm.
	 * @return                          the distance converted to the opposite scale
	 * @throws ArithmeticException      if the calculation results in overflow/underflow
	 * @throws IllegalArgumentException if distance is not a real number
	 *                                  or the unit is not supported
	 */
	public static double convertDistance(double distance, String unit) throws IllegalArgumentException, ArithmeticException {

		if (!Double.isFinite(distance)) {
			throw new IllegalArgumentException("Invalid distance");
		}
		
		// Convert depending on unit
		double output;
		if (Objects.equals(unit, "mi")) {
			output = distance * 1.609344;
		} else if (Objects.equals(unit, "km")) {
			output = distance / 1.609344;
		} else if (Objects.equals(unit, "ft")) {
			output = distance * 0.3048;
		} else if (Objects.equals(unit, "m")) {
			output = distance / 0.3048;
		} else if (Objects.equals(unit, "in")) {
			output = distance * 2.54;
		} else if (Objects.equals(unit, "cm")) {
			output = distance / 2.54;
		} else {
			throw new IllegalArgumentException("Invalid distance unit");
		}

		// Check for overflow/underflow
		return checkArithmetic(output);
	}

	/**
	 * Checks for double overflow or underflow and throws an ArithmeticException if appropriate.
	 * 
	 * @param d                    the double to be checked
	 * @return                     the double if no overflow/underflow was found
	 * @throws ArithmeticException if the input is a double overflow or underflow
	 */
	private static double checkArithmetic(double d) throws ArithmeticException {
		if (d == Double.POSITIVE_INFINITY || d == Double.NEGATIVE_INFINITY ||
			Double.compare(+0.0f, d) == 0 || Double.compare(-0.0f, d) == 0) 
		{
			throw new ArithmeticException("There was an error performing this calculation");
		} else {
			return d;
		}
	}
}