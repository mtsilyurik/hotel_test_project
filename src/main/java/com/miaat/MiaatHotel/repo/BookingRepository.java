package com.miaat.MiaatHotel.repo;

import com.miaat.MiaatHotel.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByRoomId(Long roomId);

    List<Booking> findByBookingConfirmationCode(String bookingCode);

    List<Booking> findByUserId(Long userId);
}
