package no.services.booking;

import no.modal.entity.Booking;
import no.modal.request.BookingRequest;
import org.springframework.data.domain.Page;

public interface BookingService {
    // post
    Booking createBooking(BookingRequest bookingRequest);
    // get
    Page<Booking> getBookings(int page, int size);
    Booking findById(long id);
    // put
    Booking updateBooking(BookingRequest bookingRequest, long id);
    //delete
    boolean deleteBooking(long id);

}
