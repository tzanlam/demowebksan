package hotel.modal.request;

import hotel.modal.entity.Booking;
import hotel.modal.entity.Room;
import hotel.modal.entity.StatusBooking;
import hotel.modal.entity.TypeBooking;
import hotel.service.room.RoomService;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static hotel.helper.MethodConvertTime.convertDateTime;

@Data
public class BookingRequest {

    private final RoomService roomService;
    private String fullName;
    private int phoneNumber;
    private int roomId; // Changed to int for ID
    private String typeBooking;
    private String checkin;
    private String checkout;

    public Booking asBooking() {
        Booking booking = new Booking();
        populateBookingDetails(booking);
        booking.setStatus(StatusBooking.WAITING);
        return booking;
    }

    public Booking updateBooking(Booking booking) {
        populateBookingDetails(booking);
        return booking;
    }

    private void populateBookingDetails(Booking booking) {
        booking.setFullName(fullName);
        booking.setPhoneNumber(phoneNumber);

        LocalDateTime checkinTime = convertDateTime(checkin);
        LocalDateTime checkoutTime = convertDateTime(checkout);

        Room room = roomService.findById(roomId);

        if (room == null) {
            throw new IllegalArgumentException("Invalid room ID.");
        }

        switch (typeBooking) {
            case "1": { // Hourly
                booking.setTypeBooking(TypeBooking.HOUR);
                long totalHours = ChronoUnit.HOURS.between(checkinTime, checkoutTime);
                if (totalHours <= 0) {
                    throw new IllegalArgumentException("Checkout time must be after checkin time.");
                } else if (totalHours > 10) {
                    throw new IllegalArgumentException("Checkout time must be less than 10.");
                }
                booking.setTotalPrice(totalHours == 1 ? 70000 : 70000 + (totalHours - 1) * 20000);
                break;
            }
            case "2": { // Daily
                booking.setTypeBooking(TypeBooking.DAY);
                long totalDays = ChronoUnit.DAYS.between(checkinTime.toLocalDate(), checkoutTime.toLocalDate());
                totalDays = Math.max(totalDays, 1); // Tối thiểu 1 ngày
                booking.setTotalPrice(totalDays * room.getPriceDay());
                break;
            }
            case "3": { // Nightly
                booking.setTypeBooking(TypeBooking.NIGHT);
                booking.setTotalPrice(room.getPriceNight());
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid booking type.");
            }
        }
    }
}
