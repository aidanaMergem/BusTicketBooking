package com.busticketbooking.DTO;
import lombok.Data;

@Data
public class SeatDTO {
    private int seatId;
    private int seatNumber;
    private boolean isAvailable;

  //  private int busId;
}
