package com.example.product_service_12052024.services;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@EnableScheduling
public class ScheduleTask {

   @Scheduled(cron = "1 * * * * *")
   void  sendEmail() {

//	  Business logics
	  printCurrentTime ("Sending email");
//	  System.out.println ();
   }

   public static void printCurrentTime(String message) {
	  // Get the current date and time
	  LocalDateTime now = LocalDateTime.now();

	  // Define a formatter to format the date and time
	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	  // Format the current date and time
	  String formattedNow = now.format(formatter);

	  // Print the formatted date and time
	  System.out.println(formattedNow+" " +message);
   }
}
