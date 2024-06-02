package com.learning.tripexpensemanagementsystem.service.impl;

import com.learning.tripexpensemanagementsystem.dto.AddTripRequestDto;
import com.learning.tripexpensemanagementsystem.entity.Trip;
import com.learning.tripexpensemanagementsystem.exception.FoundException;
import com.learning.tripexpensemanagementsystem.exception.InvalidInputException;
import com.learning.tripexpensemanagementsystem.exception.NotFoundException;
import com.learning.tripexpensemanagementsystem.repository.TripRepository;
import com.learning.tripexpensemanagementsystem.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;

    @Override
    public List<Trip> getAll() {
        return tripRepository.findAll();
    }

    @Override
    public Trip add(AddTripRequestDto addTripRequestDto) {
        if (addTripRequestDto.getStartDate().after(addTripRequestDto.getEndDate())) {
            throw new InvalidInputException("INVALID_INPUT_DATES", "Trip start date is after end date, please modify it");
        }

        return tripRepository.save(Trip.builder()
                .destination(addTripRequestDto.getDestination())
                .startDate(addTripRequestDto.getStartDate())
                .endDate(addTripRequestDto.getEndDate())
                .build());
    }

    @Override
    public Trip update(Long id, Trip trip) {
        var tripExists = getById(id);

        tripExists.setDestination(trip.getDestination());
        tripExists.setStartDate(trip.getStartDate());
        tripExists.setEndDate(trip.getEndDate());

        return tripRepository.save(tripExists);
    }

    @Override
    public void delete(Long id) {
        getById(id);

        tripRepository.deleteById(id);

        var tripDeleted = tripRepository.findById(id);
        if (tripDeleted.isPresent()) {
            throw new FoundException("TRIP_NOT_DELETED", "Trip not deleted with id: " + id);
        }
    }

    @Override
    public Trip getById(Long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("TRIP_NOT_FOUND", "Trip details not found with id: " + id));
    }
}
