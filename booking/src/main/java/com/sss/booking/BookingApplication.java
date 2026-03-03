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
		Client c = context.getBean(Client.class);
		Theater t = context.getBean(Theater.class);
		ScreenLayout s1 = context.getBean(ScreenLayout.class);
		ScreenLayout s2 = context.getBean(ScreenLayout.class);
		Seat a1 = context.getBean(Seat.class);
		Seat a2 = context.getBean(Seat.class);
		Seat b1 = context.getBean(Seat.class);
		Seat b2 = context.getBean(Seat.class);
		Seat b3 = context.getBean(Seat.class);
		Seat b4 = context.getBean(Seat.class);
		a1.setScreen(s1);
		a1.setSeatColumn(0);
		a1.setSeatRow(0);
		a1.setSeat_name("A1");
		a2.setScreen(s1);
		a2.setSeatRow(0);
		a2.setSeatColumn(1);
		a2.setSeat_name("A2");
		b1.setScreen(s2);
		b1.setSeatColumn(0);
		b1.setSeatRow(0);
		b1.setSeat_name("A1");
		b2.setScreen(s2);
		b2.setSeatColumn(1);
		b2.setSeatRow(0);
		b2.setSeat_name("A2");
		b3.setScreen(s2);
		b3.setSeatColumn(0);
		b3.setSeatRow(1);
		b3.setSeat_name("B1");
		b4.setScreen(s2);
		b4.setSeatColumn(1);
		b4.setSeatRow(1);
		b4.setSeat_name("B2");
		s1.setTheater(t);
		s1.setRows(1);
		s1.setColumns(2);
		s1.setScreen_type("70mm");
		s1.setBase_price(100);
		s1.setSeats(new ArrayList<>(Arrays.asList(a1,a2)));
		s2.setTheater(t);
		s2.setRows(2);
		s2.setColumns(2);
		s2.setBase_price(100);
		s2.setScreen_type("35mm");
		s2.setSeats(new ArrayList<>(Arrays.asList(b1, b2, b3, b4)));
		t.setClient(c);
		t.setAddress("hello,hyderabad");
		t.setLatitude(2.3424324);
		t.setLongitude(3.254242415);
		t.setScreen_count(2);
		t.setScreens(new ArrayList<>(Arrays.asList(s1, s2)));
		c.setName("shiva");
		c.setPassword("efsgaewrewfsdzdwrtgs");
		c.setMobile("8790354765");
		c.setEmail("shivatalla764@gmail.com");
		ArrayList<Theater> theater = new ArrayList<>();
		theater.add(t);
		c.setTheaters(theater);

		JPAClient repo = context.getBean(JPAClient.class);
		repo.save(c);

		System.out.println(c);

	}

}
