package com.miaat.MiaatHotel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message="ChekIn date is required")
    private LocalDate checkInDate;

    @Future(message="ChekOut date must be in future")
    private LocalDate checkOutDate;

    @Min(value=1, message = "Adult must be at least one")
    private int numbOfAdults;

    @Min(value=0, message = "Number can not be negative")
    private int numbOfChildren;

    private int totalNumOfGuests;

    private String bookingConfirmationCode;

    @ManyToOne(fetch = FetchType.EAGER) // One user can have may bookings
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) // One room can have may bookings
    @JoinColumn(name="room_id")
    private Room room;

    public void culcTotalNumberOfguests(){
        this.totalNumOfGuests = this.numbOfAdults + this.numbOfChildren;
    }

    public void setNumbOfAdults(@Min(value = 1, message = "Adult must be at least one") int numbOfAdults) {
        this.numbOfAdults = numbOfAdults;
        culcTotalNumberOfguests();
    }

    public void setNumbOfChildren(@Min(value = 0, message = "Number can not be negative") int numbOfChildren) {
        this.numbOfChildren = numbOfChildren;
        culcTotalNumberOfguests();
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", numbOfAdults=" + numbOfAdults +
                ", numbOfChildren=" + numbOfChildren +
                ", totalNumOfGuests=" + totalNumOfGuests +
                ", bookingConfirmationCode='" + bookingConfirmationCode + '\'' +
                '}';
    }
}
