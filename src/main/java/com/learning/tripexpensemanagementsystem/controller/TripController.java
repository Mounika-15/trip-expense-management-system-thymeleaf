package com.learning.tripexpensemanagementsystem.controller;

import com.learning.tripexpensemanagementsystem.dto.AddTripRequestDto;
import com.learning.tripexpensemanagementsystem.entity.Trip;
import com.learning.tripexpensemanagementsystem.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trips")
public class TripController {

    private final TripService tripService;

    @GetMapping
    public ResponseEntity<List<Trip>> getAll() {
        return new ResponseEntity<>(tripService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Trip> add(@RequestBody AddTripRequestDto addTripRequestDto) {
        return new ResponseEntity<>(tripService.add(addTripRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> update(@PathVariable Long id, @RequestBody Trip trip) {
        return new ResponseEntity<>(tripService.update(id, trip), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        tripService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
