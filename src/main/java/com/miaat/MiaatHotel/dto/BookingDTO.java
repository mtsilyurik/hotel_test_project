package com.miaat.MiaatHotel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDTO {
    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numOfAdult;
    private int numOfChildren;
    private int totalNumOfGuests;
    private String bokingConfirmationCode;
    private UserDTO user;
    private RoomDTO room;
}
