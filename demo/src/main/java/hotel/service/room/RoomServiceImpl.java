package hotel.service.room;

import hotel.modal.entity.Room;
import hotel.modal.request.RoomRequest;
import hotel.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RoomServiceImpl implements RoomService {

    private static final Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Page<Room> findAll(int page) {
        return roomRepository.findAll(PageRequest.of(page, 10));
    }

    @Override
    public Room create(RoomRequest request) {
        Room room = request.asRoom();
        roomRepository.save(room);
        logger.info("Created new room with id: {}", room.getId());
        return room;
    }

    @Override
    public Room update(int id, RoomRequest request) throws Exception {
        logger.info("Updating room with id: {}", id);

        return roomRepository.findById(id).map(existingRoom -> {
            Room updatedRoom = request.updateRoom(existingRoom);
            updatedRoom.setId(id);  // Đảm bảo id được giữ nguyên
            roomRepository.save(updatedRoom);
            logger.info("Room updated successfully with id: {}", id);
            return updatedRoom;
        }).orElseThrow(() -> {
            logger.error("Room not found with id: {}", id);
            return new EntityNotFoundException("Room not found with id: " + id);
        });
    }

    @Override
    public Room delete(int id) {
        logger.info("Deleting room with id: {}", id);

        return roomRepository.findById(id).map(room -> {
            room.setRoomQuantity(0);  // Xóa soft delete (chỉ đặt số lượng phòng về 0)
            roomRepository.save(room);
            logger.info("Room marked as deleted (quantity set to 0) for id: {}", id);
            return room;
        }).orElseThrow(() -> {
            logger.error("Room not found with id: {}", id);
            return new EntityNotFoundException("Room not found with id: " + id);
        });
    }

    @Override
    public Room findById(int id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found with id: " + id));
    }
}
