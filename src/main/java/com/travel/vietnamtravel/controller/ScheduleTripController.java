package com.travel.vietnamtravel.controller;

import com.travel.vietnamtravel.dto.scheduletrip.sdi.*;
import com.travel.vietnamtravel.dto.scheduletrip.sdo.*;
import com.travel.vietnamtravel.service.ScheduleTripService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/schedule-trip")
public class ScheduleTripController {
    private final ScheduleTripService scheduleTripService;

    @PostMapping("/create")
    public ResponseEntity<ScheduleTripCreateSdo> create(ScheduleTripCreateSdi req) {
        return ResponseEntity.ok(scheduleTripService.create(req));
    }

    @PutMapping("/update")
    public ResponseEntity<ScheduleTripUpdateSdo> update( ScheduleTripUpdateSdi req) {
        return ResponseEntity.ok(scheduleTripService.update(req));
    }

    @PostMapping("/delete")
    public ResponseEntity<ScheduleTripDeleteSdo> delete(ScheduleTripDeleteSdi req) {
        return ResponseEntity.ok(scheduleTripService.delete(req));
    }

    @PostMapping("/self")
    public ResponseEntity<ScheduleTripSelfSdo> self( ScheduleTripSelfSdi req) {
        return ResponseEntity.ok(scheduleTripService.self(req));
    }

    @GetMapping("/my-schedules")
    public ResponseEntity<List<ScheduleTripSelfSdo>> mySchedules() {
        return ResponseEntity.ok(scheduleTripService.mySchedules());
    }
}
