package patientintake;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class ClinicCalendar {

   private List<PatientAppointment> appointments;
   private LocalDate today;

   public ClinicCalendar(LocalDate today) {
      this.appointments = new ArrayList<>();
      this.today = today;
   }

   public void addAppointment(String patientFirstName, String patientLastName, String doctorKey,
                              String dateTime) {
      Doctor doc = Doctor.valueOf(doctorKey.toLowerCase());
      LocalDateTime localDateTime = DateTimeConverter.convertStringToDateTime(dateTime, today);
      PatientAppointment appointment = new PatientAppointment(patientFirstName, patientLastName,
         localDateTime, doc);
      appointments.add(appointment);
   }

//private LocalDateTime convertToDateFromString(String dateTime) {
//	LocalDateTime localDateTime;
//      try {
//    	  if (dateTime.toLowerCase().startsWith("today")) {
//    		  String[] parts = dateTime.split(" ", 2);
//    		  LocalTime time = LocalTime.parse(parts[1].toUpperCase(),
//    				  DateTimeFormatter.ofPattern("h:mm a", Locale.US));
//    		  localDateTime = LocalDateTime.of(today, time);
//    	  }
//    	  else {
//         localDateTime = LocalDateTime.parse(dateTime.toUpperCase(),
//            DateTimeFormatter.ofPattern("M/d/yyyy h:mm a", Locale.US));
//    	  }
//      } catch (Throwable t) {
//         throw new RuntimeException("Unable to create date time from: [" +
//            dateTime.toUpperCase() + "], please enter with format [M/d/yyyy h:mm a]" + t.getMessage());
//      }
//	return localDateTime;
//}

   public List<PatientAppointment> getAppointments() {
      return this.appointments;
   }

   public List<PatientAppointment> getTodayAppointments(){
	   return appointments.stream()
			   .filter(appt -> appt.getAppointmentDateTime().toLocalDate().equals(today))
			   .collect(Collectors.toList());
   }
   
   public List<PatientAppointment> getTomorrowAppointments() {
	      LocalDate tomorrow = today.plusDays(1);
	      return getAppointmentsForDate(tomorrow);
	}
   
   public List<PatientAppointment> getUpcomingAppointments() {
	   return appointments.stream()
			  .filter(appt -> appt.getAppointmentDateTime().toLocalDate().isAfter(today))
			  .collect(Collectors.toList());
	}

   private List<PatientAppointment> getAppointmentsForDate(LocalDate tomorrow) {
	   return appointments.stream()
			   .filter(appt -> appt.getAppointmentDateTime().toLocalDate().equals(tomorrow))
			   .collect(Collectors.toList());
	}
   
   public boolean hasAppointment(LocalDate date) {
	   return appointments.stream()
			   .anyMatch(appt -> appt.getAppointmentDateTime().toLocalDate().equals(date));
   }
}
