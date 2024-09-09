package no.services.room;

import no.modal.entity.Room;
import no.modal.request.BookingFilterRequest;
import no.modal.request.RoomRequest;
import org.springframework.data.domain.Page;

public interface RoomService {
    // post
    Room createRoom(RoomRequest request);

    // get
    Page<Room> getAllRooms(int page, int size);
    Room findById(long id);

    //put
    Room update(RoomRequest request, long id);

    // delete
    boolean deleteRoom(long id);

    //exception
    boolean checkRoomAvailability(BookingFilterRequest request);
}
