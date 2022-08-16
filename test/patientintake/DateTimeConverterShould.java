package patientintake;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DateTimeConverter should") //acceptable code, but @DisplayName doesn't seem to function in Eclipse IDE
public class DateTimeConverterShould {
	
	@Test
	@DisplayName("convert string with 'today' keyword correctly")
	public void convertTodayStringCorrectly() {
		LocalDate today = LocalDate.of(2022, 9, 1);
		LocalDateTime result = DateTimeConverter.convertStringToDateTime("today 1:00 pm", today);
		assertEquals(result, LocalDateTime.of(2022, 9, 1, 13, 0), () -> "Failed to convert 'today' string to expected date time, today passed was: " +
		today);
	}
	
	@Test
	@DisplayName("convert string with 'today' keyword correctly regardless of case")
	public void convertTodayStringCorrectlyCaseInsensitive() {
		LocalDate today = LocalDate.of(2022, 9, 1);
		LocalDateTime result = DateTimeConverter.convertStringToDateTime("ToDay 1:00 pm", today);
		assertEquals(result, LocalDateTime.of(2022, 9, 1, 13, 0), () -> "Failed to convert 'today' string to expected date time, today passed was: " +
		today);
	}

	@Test
	@DisplayName("convert expected date time pattern in string correctly")
	public void convertCorrectPatternToDateTime() {
		LocalDateTime result = DateTimeConverter.convertStringToDateTime("9/2/2022 1:00 pm", LocalDate.of(2022, 9, 1));
		assertEquals(result, LocalDateTime.of(2022, 9, 2, 13, 0));
	}
	
	@Test
	@DisplayName("throw exception if entered pattern of string is incorrect")
	public void throwExceptionIfIncorrectPatternProvided() {
		Throwable error = assertThrows(RuntimeException.class, () -> 
		DateTimeConverter.convertStringToDateTime("9/2/2022 100 pm", LocalDate.of(2022, 9, 1)));
		assertEquals("Unable to create date time from: [9/2/2022 100 PM], " + 
		"please enter with format [M/d/yyyy h:mm a]Text '9/2/2022 100 PM' " + 
		"could not be parsed at index 12", error.getMessage());
	}

}
