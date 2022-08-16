package patientintake;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarShould {
	
	private ClinicCalendar calendar;

	@BeforeAll
	static void testClassSetup() {
		System.out.println("Before all...");
	}
	
	@BeforeEach
	void init() {
		System.out.println("Before each...");
		calendar = new ClinicCalendar(LocalDate.of(2022, 8, 15));
	}

   @Test
   void allowEntryOfAnAppointment() {
	  System.out.println("entry of appointment...");
      calendar.addAppointment("Jim", "Weaver", "avery",
         "09/01/2018 2:00 pm");
      List<PatientAppointment> appointments = calendar.getAppointments();
      assertNotNull(appointments);
      assertEquals(1, appointments.size());
      PatientAppointment enteredAppt = appointments.get(0);
      
      assertAll(
    		  () -> assertEquals("Jim", enteredAppt.getPatientFirstName()),
    		  () -> assertEquals("Weaver", enteredAppt.getPatientLastName()),
    		  () -> assertSame(Doctor.avery, enteredAppt.getDoctor()),
    		  () -> assertEquals("9/1/2018 02:00 PM",
    		  enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a", Locale.US)))
      );
   }
   
   @Test
   void returnTrueForHasAppointmentsIfThereAreAppointments() {
	   System.out.println("has appointments...");
	   calendar.addAppointment("Jim", "Weaver", "avery", "09/01/2018 2:00 pm");
	   assertTrue(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
   }
   
   @Test
   void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
	   System.out.println("no appointments...");
	   assertFalse(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
   }
   
   //Use @Disabled to exclude a given test from being run.
   @Test
   void returnCurrentDaysAppointments() {
	   System.out.println("Current day's appointments...");
	   calendar.addAppointment("Jim", "Weaver", "avery", "08/15/2022 2:00 pm");  //make sure these are set to
	   calendar.addAppointment("Jim", "Weaver", "avery", "08/15/2022 3:00 pm");  //today's date before running tests.
	   calendar.addAppointment("Jim", "Weaver", "avery", "12/15/2018 2:00 pm");
	   assertEquals(2, calendar.getTodayAppointments().size());
   }
   
   @AfterEach
   void tearDownEachTest() {
	   System.out.println("After each...");
	   
   }
   
   @AfterAll
   static void tearDownTestClass() {
	   System.out.println("After all...");
   }
}