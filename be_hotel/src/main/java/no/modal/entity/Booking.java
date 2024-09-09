package no.modal.entity;

import jakarta.persistence.*;
import lombok.Data;
import no.modal.entity.constants.BookingStatus;
import no.modal.entity.constants.TypeBooking;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "Booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_user", nullable = false)
    private long idUser;

    @Column(name = "id_room", nullable = false)
    private long idRoom;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeBooking type;

    @Column(name = "price")
    private double price;

    @Column(name = "checkin", nullable = false)
    private LocalDateTime checkin;

    @Column(name = "checkout", nullable = false)
    private LocalDateTime checkout;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus statusBooking;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

}
