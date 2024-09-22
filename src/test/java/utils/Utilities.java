package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Utilities {
	
	
	  public final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");

      public String getDateOneWeekFromToday() {
          return LocalDate.now().plusWeeks(1).format(DATE_FORMAT);
      }

      public String getDateTwoDaysAfter(String departureDate) {
          LocalDate departure = LocalDate.parse(departureDate, DATE_FORMAT);
          return departure.plusDays(2).format(DATE_FORMAT);
      }
      
      
      

}


