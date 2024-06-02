package com.learning.tripexpensemanagementsystem.service;

import com.learning.tripexpensemanagementsystem.dto.AddTripRequestDto;
import com.learning.tripexpensemanagementsystem.entity.Trip;

import java.util.List;

public interface TripService {
    Trip getById(Long id);

    List<Trip> getAll();

    Trip add(AddTripRequestDto addTripRequestDto);

    Trip update(Long id, Trip trip);

    void delete(Long id);
}
