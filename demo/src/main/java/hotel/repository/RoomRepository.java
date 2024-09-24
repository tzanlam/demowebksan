package hotel.repository;

import hotel.modal.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository  extends JpaRepository<Room, Integer> {
    Room findByName(String roomName);
}
