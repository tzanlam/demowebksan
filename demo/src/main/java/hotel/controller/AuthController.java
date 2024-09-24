package hotel.controller;

import hotel.modal.request.BookingRequest;
import hotel.modal.request.RoomRequest;
import hotel.service.booking.BookingService;
import hotel.service.global.AuthService;
import hotel.service.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private BookingService bookingService;

    @PostMapping("/createRoom")
    public ResponseEntity<?> create(@RequestBody RoomRequest roomRequest) {
        return new ResponseEntity<>(roomService.create(roomRequest), HttpStatus.CREATED);
    }

    @PutMapping("/updateRoom/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody RoomRequest roomRequest) throws Exception {
        return new ResponseEntity<>(roomService.update(id, roomRequest),HttpStatus.OK );
    }

    @GetMapping("/deleteRoom/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(roomService.delete(id),HttpStatus.OK);
    }

    @GetMapping("/findBookings/{page}")
    public ResponseEntity<?> findBookings(@PathVariable int page){
        return new ResponseEntity<>(bookingService.find(page),HttpStatus.OK);
    }

    @GetMapping("/deleteBooking/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable int id) {
        return new ResponseEntity<>(bookingService.delete(id), HttpStatus.OK);
    }

    @PutMapping("/updateBooking/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable int id, @RequestBody BookingRequest bookingRequest) {
        return new ResponseEntity<>(bookingService.update(id, bookingRequest),HttpStatus.OK);
    }
}
