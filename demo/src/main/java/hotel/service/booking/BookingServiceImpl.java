package hotel.service.booking;

import hotel.modal.entity.Booking;
import hotel.modal.entity.Room;
import hotel.modal.entity.StatusBooking;
import hotel.modal.request.BookingRequest;
import hotel.repository.BookingRepository;
import hotel.repository.RoomRepository;
import hotel.service.global.EmailService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public Page<Booking> find(int page) {
        return bookingRepository.findAll(PageRequest.of(page,10));
    }

    @Override
    public Booking create(BookingRequest request) {

        Booking booking = request.asBooking();
        booking = bookingRepository.save(booking);

        emailService.sendBookingEmail(booking);
        return booking;
    }

    @Override
    public Booking update(int id, BookingRequest request) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));

        Room room = roomRepository.findById(request.getRoomId()).orElseThrow(() -> new EntityNotFoundException("Room not found"));
        Booking updatedBooking = request.updateBooking(booking);
        updatedBooking = bookingRepository.save(updatedBooking);
        emailService.sendBookingEmail(updatedBooking);
        return updatedBooking;
    }

    @Override
    public Booking delete(int id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));

        booking.setStatus(StatusBooking.CANCELLED);
        emailService.sendBookingEmail(booking);

        return booking;
    }
}
