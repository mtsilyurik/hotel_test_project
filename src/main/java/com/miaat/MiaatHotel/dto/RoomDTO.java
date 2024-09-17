package com.miaat.MiaatHotel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.miaat.MiaatHotel.entity.Booking;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDTO {

    private Long id;
    private String roomType;
    private BigDecimal roomPrica;
    private String roomPhotoUrl;
    private String roomDescription;
    private List<BookingDTO> bookings;
}
