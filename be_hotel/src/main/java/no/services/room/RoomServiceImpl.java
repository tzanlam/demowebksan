package no.services.room;

import no.modal.entity.Room;
import no.modal.request.BookingFilterRequest;
import no.modal.request.RoomRequest;
import no.repositoty.BookingRepository;
import no.repositoty.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static no.ultils.MethodConvertTime.convertDateTime;

@Service
public class RoomServiceImpl  implements RoomService{
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Override
    public Room createRoom(RoomRequest request) {
        Room room = request.asRoom();
        roomRepository.save(room);
        return room;
    }

    @Override
    public Page<Room> getAllRooms(int page, int size) {
        return roomRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Room findById(long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public Room update(RoomRequest request, long id) {
        Room room = roomRepository.findById(id).orElse(null);
        if(room != null) {
            request.updateRoom(request);
            roomRepository.save(room);
            return room;
        }
        return null;
    }

    @Override
    public boolean deleteRoom(long id) {
        Room room = roomRepository.findById(id).orElse(null);
        if(room != null) {
            room.setRoomQuantity(0);
            return true;
        }else {
            return false;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public boolean checkRoomAvailability(BookingFilterRequest bookingFilterRequest) {
        Room room = roomRepository.findById(bookingFilterRequest.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        LocalDateTime checkinStart = convertDateTime(bookingFilterRequest.getCheckinStart());
        LocalDateTime checkinEnd = convertDateTime(bookingFilterRequest.getCheckinEnd());
        LocalDateTime checkoutStart = convertDateTime(bookingFilterRequest.getCheckoutStart());
        LocalDateTime checkoutEnd = convertDateTime(bookingFilterRequest.getCheckoutEnd());

        int bookingCount = bookingRepository.countByCheckinBetween(room, checkinStart, checkinEnd, checkoutStart, checkoutEnd);

        return bookingCount < room.getRoomQuantity();
    };
}
