package no.modal.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import no.modal.entity.Booking;
import no.modal.entity.constants.BookingStatus;
import no.modal.entity.constants.TypeBooking;

import java.time.LocalDate;

import static no.ultils.MethodConvertTime.convertDate;
import static no.ultils.MethodConvertTime.convertDateTime;

@Data
public class BookingRequest {
    @NotNull
    private long idUser;
    @NotNull
    private long idRoom;
    @NotNull
    private String typeBooking;
    private double price;
    @NotNull
    private String checkin;
    @NotNull
    private String checkout;
    private String status;
    private String createdDate;
    private String modifiedDate;

    public Booking asBooking() {
        Booking booking = new Booking();
        booking.setIdUser(idUser);
        booking.setIdRoom(idRoom);
        booking.setType(TypeBooking.valueOf(typeBooking));
        booking.setPrice(price);
        booking.setCheckin(convertDateTime(this.checkin));
        booking.setCheckout(convertDateTime(this.checkout));
        booking.setStatusBooking(BookingStatus.WAITTING);
        if (this.createdDate != null || this.createdDate.isEmpty()) {
            booking.setCreatedDate(convertDate(LocalDate.now().toString()));
        }
        if (this.modifiedDate != null || this.modifiedDate.isEmpty()) {
            booking.setModifiedDate(convertDate(LocalDate.now().toString()));
        }
        return booking;
    }

    public Booking updateBooking(Booking booking) {
        booking.setIdRoom(idRoom);
        booking.setType(TypeBooking.valueOf(typeBooking));
        booking.setPrice(price);
        booking.setCheckin(convertDateTime(this.checkin));
        booking.setCheckout(convertDateTime(this.checkout));
        booking.setStatusBooking(BookingStatus.WAITTING);
        if (this.createdDate != null || this.createdDate.isEmpty()) {
            booking.setCreatedDate(convertDate(LocalDate.now().toString()));
        }
        if (this.modifiedDate != null || this.modifiedDate.isEmpty()) {
            booking.setModifiedDate(convertDate(LocalDate.now().toString()));
        }
        return booking;
    }
}
