package patientintake;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class DateTimeConverterShould {
	
	@Test
	public void convertTodayStringCorrectly() {
		LocalDateTime result = DateTimeConverter.convertStringToDateTime("today 1:00 pm", LocalDate.of(2022, 9, 1));
		assertEquals(result, LocalDateTime.of(2022, 9, 1, 13, 0));
	}

	@Test
	public void convertCorrectPatternToDateTime() {
		LocalDateTime result = DateTimeConverter.convertStringToDateTime("9/2/2022 1:00 pm", LocalDate.of(2022, 9, 1));
		assertEquals(result, LocalDateTime.of(2022, 9, 2, 13, 0));
	}
	
	@Test
	public void throwExceptionIfIncorrectPatternProvided() {
		Throwable error = assertThrows(RuntimeException.class, () -> 
		DateTimeConverter.convertStringToDateTime("9/2/2022 100 pm", LocalDate.of(2022, 9, 1)));
		assertEquals("Unable to create date time from: [9/2/2022 100 PM], " + 
		"please enter with format [M/d/yyyy h:mm a]Text '9/2/2022 100 PM' " + 
		"could not be parsed at index 12", error.getMessage());
	}

}
