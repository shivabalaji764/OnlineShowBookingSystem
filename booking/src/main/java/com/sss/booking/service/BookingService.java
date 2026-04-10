package com.sss.booking.service;

import com.sss.booking.dto.BookingDTO;
import com.sss.booking.dto.DetailsDTO;
import com.sss.booking.model.*;
import com.sss.booking.repository.*;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private ShowSeatService showSeatService;

    public @Nullable DetailsDTO getDetails(Long showId, List<Integer> seats, Double cost) {
        DetailsDTO details = new DetailsDTO();
        Optional<ShowModel> showModel = showRepository.findById(showId);
        if(showModel.isEmpty()) return null;
        ShowModel show = showModel.get();
        details.setShowName(show.getShowName());
        details.setDate(show.getShowDateAndTime());
        Integer theaterId = show.getTheaterId();
        Integer screenLayoutId = show.getScreenLayoutId();


        Optional<Theater> theaterOptional = theaterRepository.findById(theaterId);
        if(theaterOptional.isEmpty()) return null;
        Theater theater = theaterOptional.get();
        details.setTheater(theater.getTheaterName());
        details.setAddress(theater.getAddress());

        Optional<ScreenLayout> screenLayout = screenRepository.findById(screenLayoutId);
        if(screenLayout.isEmpty()) return null;
        ScreenLayout screen = screenLayout.get();
        details.setScreen(screen.getScreenNo());

        StringBuilder allSeats = new StringBuilder();
        for(Integer i:seats){
            Optional<ShowSeat> showSeat = showSeatRepository.findById(i);
            if(showSeat.isEmpty()) return null;
            ShowSeat seat = showSeat.get();
            allSeats.append(" ").append((char)(seat.getSeatRow()+'A')).append(seat.getSeatColumn()+1);
        }
        details.setSeats(allSeats.toString());
        details.setTotalPrice(cost);

        return details;
    }


    public Boolean confirmBooking(Long userId, Long showId, List<Integer> seats, Double cost) {
        Booking booking = new Booking();

        Optional<ShowModel> showModel = showRepository.findById(showId);
        if(showModel.isEmpty()) return  false;
        ShowModel show = showModel.get();
        booking.setShow(show);

        Optional<Users> users = userRepository.findById(userId);
        if(users.isEmpty()) return  false;
        Users user = users.get();
        booking.setUser(user);

        booking.setBookingTime(LocalDateTime.now());

        List<ShowSeat> showSeats = new ArrayList<>();
        for(Integer id: seats){
            Optional<ShowSeat> showSeat = showSeatRepository.findById(id);
            if(showSeat.isEmpty()) return false;
            ShowSeat seat = showSeat.get();
            if(seat.getLockTime().plusMinutes(9).plusSeconds(30).isBefore(LocalDateTime.now())){
                return false;
            }
            showSeats.add(seat);
        }
        booking.setSeats(showSeats);

        booking.setCost(cost);

        bookingRepository.save(booking);

        showSeatService.bookSeats(seats, userId, booking);

        return true;
    }

    public List<BookingDTO> getBookings(Long userId) {
        List<Booking> bookings = bookingRepository.findAllByUserUserId(userId);
        List<BookingDTO> bookingDTOS = new ArrayList<>();
        for (Booking booking: bookings){
            BookingDTO bookingDTO = new BookingDTO();

            bookingDTO.setBookingId(booking.getBookingId());
            bookingDTO.setTotalPrice(booking.getCost());

            ShowModel show = booking.getShow();
            bookingDTO.setShowName(show.getShowName());
            bookingDTO.setShowDateAndTime(show.getShowDateAndTime());
            bookingDTO.setShowLength(show.getShowLength());

            Theater theater = theaterRepository.findById(show.getTheaterId())
                    .orElseThrow(() -> new RuntimeException("Theater not found"));
            bookingDTO.setTheaterName(theater.getTheaterName());
            bookingDTO.setAddress(theater.getAddress());

            ScreenLayout screen = screenRepository.findById(show.getScreenLayoutId())
                    .orElseThrow(()->new RuntimeException("Screen not Found"));
            bookingDTO.setScreenNo(screen.getScreenNo());

            List<ShowSeat> seats = booking.getSeats();
            StringBuilder showSeats = new StringBuilder();
            for (ShowSeat seat:seats){
                showSeats.append((char)(seat.getSeatRow()+'A')).append(seat.getSeatColumn()).append(" ");
            }
            bookingDTO.setSeats(showSeats.toString());

            bookingDTOS.add(bookingDTO);
        }
        return bookingDTOS;
    }
}