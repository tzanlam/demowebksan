package no.controller;

import no.modal.DTO.BookingDTO;
import no.modal.entity.Booking;
import no.modal.request.BookingRequest;
import no.services.booking.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("Booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/getAllBookings/{page}/{size}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getAll(@PathVariable int page, @PathVariable int size) {
        Page<Booking> bookingPage = bookingService.getBookings(page, size);
        List<BookingDTO> bookings = bookingPage.getContent().stream()
                .map(booking -> modelMapper.map(booking, BookingDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/findById/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return ResponseEntity.ok(modelMapper.map(bookingService.findById(id), BookingDTO.class));
    }

    @PostMapping("/createBooking")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<?> create(@RequestBody BookingRequest bookingRequest) {
        return new ResponseEntity<>(modelMapper.map(bookingService.createBooking(bookingRequest), BookingDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("updateBooking/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody BookingRequest bookingRequest) {
        return new ResponseEntity<>(modelMapper.map(bookingService.updateBooking(bookingRequest, id), BookingDTO.class), HttpStatus.OK);
    }

    @GetMapping("/deleteBooking/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return new ResponseEntity<>(bookingService.deleteBooking(id), HttpStatus.OK);
    }
}
