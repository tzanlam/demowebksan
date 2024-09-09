package no.repositoty;

import no.modal.entity.Booking;
import no.modal.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.idRoom = :room AND " +
            "((b.checkin BETWEEN :checkinStart AND :checkinEnd) OR " +
            "(b.checkout BETWEEN :checkoutStart AND :checkoutEnd) OR " +
            "(b.checkin <= :checkinStart AND b.checkout >= :checkoutEnd))")
    int countByCheckinBetween(Room room, LocalDateTime checkinStart, LocalDateTime checkinEnd, LocalDateTime checkoutStart, LocalDateTime checkoutEnd);
}
