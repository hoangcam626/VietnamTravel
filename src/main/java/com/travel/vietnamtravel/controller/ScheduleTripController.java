package com.travel.vietnamtravel.controller;

import com.travel.vietnamtravel.dto.scheduletrip.sdi.ScheduleTripCreateSdi;
import com.travel.vietnamtravel.dto.scheduletrip.sdi.ScheduleTripDeleteSdi;
import com.travel.vietnamtravel.dto.scheduletrip.sdi.ScheduleTripSelfSdi;
import com.travel.vietnamtravel.dto.scheduletrip.sdi.ScheduleTripUpdateSdi;
import com.travel.vietnamtravel.dto.scheduletrip.sdo.ScheduleTripCreateSdo;
import com.travel.vietnamtravel.dto.scheduletrip.sdo.ScheduleTripDeleteSdo;
import com.travel.vietnamtravel.dto.scheduletrip.sdo.ScheduleTripSelfSdo;
import com.travel.vietnamtravel.dto.scheduletrip.sdo.ScheduleTripUpdateSdo;
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

    @PostMapping
    public ResponseEntity<ScheduleTripCreateSdo> create(@RequestBody ScheduleTripCreateSdi req){
        return ResponseEntity.ok(scheduleTripService.create(req));
    }
    @PutMapping
    public ResponseEntity<ScheduleTripUpdateSdo> update(@RequestBody ScheduleTripUpdateSdi req){
        return ResponseEntity.ok(scheduleTripService.update(req));
    }
    @DeleteMapping
    public ResponseEntity<ScheduleTripDeleteSdo> delete(@RequestBody ScheduleTripDeleteSdi req){
        return ResponseEntity.ok(scheduleTripService.delete(req));
    }

    @GetMapping
    public ResponseEntity<ScheduleTripSelfSdo> self(@RequestBody ScheduleTripSelfSdi req){
        return ResponseEntity.ok(scheduleTripService.self(req));
    }

    @GetMapping("/my-schedules")
    public ResponseEntity<List<ScheduleTripSelfSdo>> mySchedules(){
        return ResponseEntity.ok(scheduleTripService.mySchedules());
    }
}
