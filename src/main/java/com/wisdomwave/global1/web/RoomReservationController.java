package com.wisdomwave.global1.web;

import com.wisdomwave.global1.business.ReservationService;
import org.h2.util.DateTimeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservations")
public class RoomReservationController {

    private final DateTimeUtils dateTimeUtils;
    private final ReservationService reservationService;

    public RoomReservationController(DateTimeUtils dateTimeUtils, ReservationService reservationService) {
        this.dateTimeUtils = dateTimeUtils;
        this.reservationService = reservationService;
    }
}
