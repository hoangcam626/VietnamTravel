package com.travel.vietnamtravel.controller;

import com.travel.vietnamtravel.dto.placeschedule.sdi.*;
import com.travel.vietnamtravel.dto.placeschedule.sdo.*;
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
    public ResponseEntity<ScheduleTripCreateSdo> create(@RequestBody ScheduleTripCreateSdi req) {
        return ResponseEntity.ok(scheduleTripService.create(req));
    }

    @PutMapping("/update")
    public ResponseEntity<ScheduleTripUpdateSdo> update(@RequestBody ScheduleTripUpdateSdi req) {
        return ResponseEntity.ok(scheduleTripService.update(req));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ScheduleTripDeleteSdo> delete(@RequestBody ScheduleTripDeleteSdi req) {
        return ResponseEntity.ok(scheduleTripService.delete(req));
    }

    @GetMapping("/self")
    public ResponseEntity<ScheduleTripSelfSdo> self(@RequestBody ScheduleTripSelfSdi req) {
        return ResponseEntity.ok(scheduleTripService.self(req));
    }

    @PostMapping("/place-of-schedule/create")
    public ResponseEntity<PlaceScheduleCreateSdo> createPlace(PlaceScheduleCreateSdi req) {
        return ResponseEntity.ok(scheduleTripService.createPlace(req));
    }

    @PutMapping("/place-of-schedule/update")
    public ResponseEntity<PlaceScheduleUpdateSdo> updatePlace(@RequestBody PlaceScheduleUpdateSdi req) {
        return ResponseEntity.ok(scheduleTripService.updatePlace(req));
    }

    @DeleteMapping("/place-of-schedule/delete")
    public ResponseEntity<PlaceScheduleDeleteSdo> deletePlace(PlaceScheduleDeleteSdi req) {
        return ResponseEntity.ok(scheduleTripService.deletePlace(req));
    }

    @GetMapping("/place-of-schedule/self")
    public ResponseEntity<PlaceScheduleSelfSdo> selfPlace(PlaceScheduleSelfSdi req) {
        return ResponseEntity.ok(scheduleTripService.selfPlace(req));
    }


    @GetMapping("/my-schedules")
    public ResponseEntity<List<ScheduleTripSelfSdo>> mySchedules() {
        return ResponseEntity.ok(scheduleTripService.mySchedules());
    }
}
