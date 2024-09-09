package no.modal.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class RoomDTO {
    private int bedQuantity;
    private int roomQuantity;
    private String roomName;
    private double price;
    private String description;
    private String image;
    private Date createdDate;
    private Date modifiedDate;
}
