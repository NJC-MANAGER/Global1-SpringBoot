package com.wisdomwave.global1.business;

import com.wisdomwave.global1.data.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date; // Correctly importing java.sql.Date
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationForDate(java.util.Date date) {
        // Convert java.util.Date to java.sql.Date
        Date sqlDate = new Date(date.getTime());

        // Step 1: Retrieve all rooms and populate RoomReservation instances
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();

        // Populate the RoomReservation map using Room data
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getId(), roomReservation);
        });

        // Step 2: Retrieve reservations for the given date
        Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(sqlDate);

        // Step 3: Map reservations to RoomReservation instances and populate guest information
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            if (roomReservation != null) {
                roomReservation.setGuestId(reservation.getGuestId());
                roomReservation.setDate(reservation.getReservationDate());

                // Retrieve guest details using the guest ID
                Optional<Guest> guestOptional = this.guestRepository.findById(reservation.getGuestId());
                guestOptional.ifPresent(guest -> {
                    roomReservation.setFirstName(guest.getFirstName());
                    roomReservation.setLastName(guest.getLastName());
                });
            }
        });

        // Step 4: Return the populated list of RoomReservation objects
        return roomReservationMap.values().stream().collect(Collectors.toList());
    }
}
