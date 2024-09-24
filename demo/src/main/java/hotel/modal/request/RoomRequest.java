package hotel.modal.request;

import hotel.modal.entity.Room;
import lombok.Data;

@Data
public class RoomRequest {
    private int bedQuantity;
    private int roomQuantity;
    private String nameRoom;
    private String description;
    private String image;
    private double priceDay;
    private double priceNight;

    public Room asRoom(){
        Room room = new Room();
        populateRoom(room);
        return room;
    }

    public Room updateRoom(Room room){
        populateRoom(room);
        return room;
    }

    private Room populateRoom(Room room){
        room.setBedQuantity(this.bedQuantity);
        room.setRoomQuantity(this.roomQuantity);
        room.setNameRoom(this.nameRoom);
        room.setDescription(this.description);
        room.setImage(this.image);
        room.setPriceDay(this.priceDay);
        room.setPriceNight(this.priceNight);
        return room;
    }
}
