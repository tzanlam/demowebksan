package no.controller;

import no.modal.DTO.RoomDTO;
import no.modal.request.RoomRequest;
import no.services.room.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("Room")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private ModelMapper modelMapper;


    @PostMapping("/createRoom")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody RoomRequest roomRequest) {
        return new ResponseEntity<>(modelMapper.map(roomService.createRoom(roomRequest), RoomDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/updateRoom/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> update(@RequestBody RoomRequest roomRequest, @PathVariable long id) {
        return new ResponseEntity<>(modelMapper.map(roomService.update(roomRequest, id), RoomDTO.class), HttpStatus.OK);
    }

    @GetMapping("getAllRooms/{page}/{size}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<?> getAll(@PathVariable int page, @PathVariable int size) {
        return new ResponseEntity<>(modelMapper.map(roomService.getAllRooms(page, size), RoomDTO.class), HttpStatus.OK);
    }

    @GetMapping("findById/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return new ResponseEntity<>(modelMapper.map(roomService.findById(id), RoomDTO.class), HttpStatus.OK);
    }

    @GetMapping("/deleteRoom/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return new ResponseEntity<>(roomService.deleteRoom(id), HttpStatus.OK);
    }
}
