package hotel.controller;

import hotel.modal.entity.Booking;
import hotel.modal.request.BookingRequest;
import hotel.service.booking.BookingService;
import hotel.service.global.AuthService;
import hotel.service.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/all")
public class AllController {
    @Autowired
    private AuthService authService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private BookingService bookingService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody String username, @RequestBody String password) {
        return new ResponseEntity<>(authService.login(username, password), HttpStatus.OK);
    }
    @GetMapping("/findRooms/{page}")
    public ResponseEntity<?> findAllRoom(@PathVariable int page) {
        return new ResponseEntity<>(roomService.findAll(page), HttpStatus.OK);
    }

    @PostMapping("/createBooking")
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest request) {
        return new ResponseEntity<>(bookingService.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/findRoomById/{id}")
    public ResponseEntity<?> findRoomById(@PathVariable int id) {
        return new ResponseEntity<>(roomService.findById(id), HttpStatus.OK);
    }
}
