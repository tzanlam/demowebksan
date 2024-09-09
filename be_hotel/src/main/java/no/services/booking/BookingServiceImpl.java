package no.services.booking;

import no.modal.entity.Booking;
import no.modal.entity.Room;
import no.modal.entity.constants.BookingStatus;
import no.modal.request.BookingRequest;
import no.repositoty.BookingRepository;
import no.repositoty.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public Booking createBooking(BookingRequest bookingRequest) {
        Room room = roomRepository.findById(bookingRequest.getIdRoom()).get();
        if (room.getRoomQuantity() == 0) {
            return null;
        }else { 
            Booking booking = bookingRequest.asBooking();
            bookingRepository.save(booking);
            room.setRoomQuantity(-1);
            return booking;
        }
    }

    @Override
    public Page<Booking> getBookings(int page, int size) {
        return bookingRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Booking findById(long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public Booking updateBooking(BookingRequest bookingRequest, long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            bookingRequest.updateBooking(booking);
            bookingRepository.save(booking);
            return booking;
        }else {
            return null;
        }
    }

    @Override
    public boolean deleteBooking(long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            booking.setStatusBooking(BookingStatus.CANCELED);
            return true;
        }else {
            return false;
        }
    }
}
