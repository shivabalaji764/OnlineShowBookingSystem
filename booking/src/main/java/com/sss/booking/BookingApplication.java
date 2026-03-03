package com.sss.booking;

import com.sss.booking.model.Client;
import com.sss.booking.model.ScreenLayout;
import com.sss.booking.model.Seat;
import com.sss.booking.model.Theater;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class BookingApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(BookingApplication.class, args);


	}

}
