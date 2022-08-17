package patientintake;

import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

@DisplayName("BMI calculator should...")
public class BMICalculatorShould {
	
	@Test
	@DisplayName("calculate BMI to two places correctly via inches and pounds")
	public void calculateCorrectly() {
		assertEquals(19.2, BMICalculator.calculateBmi(69, 130));
		assertEquals(21.52, BMICalculator.calculateBmi(70, 150));
	}

}
