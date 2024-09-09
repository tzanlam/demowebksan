package no.modal.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class BookingDTO {
    private long idUser;
    private long idRoom;
    private String type;
    private double price;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private String status;
    private Date createdDate;
    private Date modifiedDate;
}
