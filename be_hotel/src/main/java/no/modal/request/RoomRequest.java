package no.modal.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import no.modal.entity.Room;

import java.time.LocalDate;

import static no.ultils.MethodConvertTime.convertDate;

@Data
public class RoomRequest {
    private int bedQuantity;
    @NotNull
    private int roomQuantity;
    @NotNull
    private String name;
    @NotNull
    private double price;
    private String description;
    private String image;
    private String createdDate;
    private String modifiedDate;

    public Room asRoom(){
        Room room = new Room();
        room.setBedQuantity(this.bedQuantity);
        room.setRoomQuantity(this.roomQuantity);
        room.setName(this.name);
        room.setPrice(this.price);
        room.setDescription(this.description);
        room.setImage(this.image);
        if (this.createdDate == null || this.createdDate.isEmpty()) {
            room.setCreatedDate(convertDate(LocalDate.now().toString()));
        }
        else {
            room.setCreatedDate(convertDate(this.createdDate));
        }
        if (this.modifiedDate == null || this.modifiedDate.isEmpty()) {
            room.setModifiedDate(convertDate(LocalDate.now().toString()));
        }
        else {
            room.setModifiedDate(convertDate(this.modifiedDate));
        }
        return room;
    }

    public Room updateRoom(RoomRequest roomRequest){
        Room room = new Room();
        room.setBedQuantity(roomRequest.getBedQuantity());
        room.setRoomQuantity(roomRequest.getRoomQuantity());
        room.setName(roomRequest.getName());
        room.setPrice(roomRequest.getPrice());
        room.setDescription(roomRequest.getDescription());
        room.setImage(roomRequest.getImage());
        if (this.modifiedDate == null || this.modifiedDate.isEmpty()) {
            room.setModifiedDate(convertDate(LocalDate.now().toString()));
        }
        return room;
    }
}
