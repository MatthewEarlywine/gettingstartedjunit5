package patientintake;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DateTimeConverterShould {
	
	@Test
	public void convertTodayStringCorrectly() {
		LocalDateTime result = DateTimeConverter.convertStringToDateTime("today 1:00 pm", LocalDate.of(2022, 9, 1));
		assertEquals(result, LocalDateTime.of(2022, 9, 1, 13, 0));
	}

}
