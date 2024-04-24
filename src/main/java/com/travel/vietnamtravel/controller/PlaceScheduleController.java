package com.travel.vietnamtravel.controller;

import com.travel.vietnamtravel.dto.placeschedule.sdi.*;
import com.travel.vietnamtravel.dto.placeschedule.sdo.*;
import com.travel.vietnamtravel.service.PlaceScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/on-schedule")
@AllArgsConstructor
public class PlaceScheduleController {
    private final PlaceScheduleService scheduleTripService;
    @PostMapping("/create")
    public ResponseEntity<PlaceScheduleCreateSdo> create(PlaceScheduleCreateSdi req) {
        return ResponseEntity.ok(scheduleTripService.create(req));
    }

    @PutMapping("/update")
    public ResponseEntity<PlaceScheduleUpdateSdo> update(@RequestBody PlaceScheduleUpdateSdi req) {
        return ResponseEntity.ok(scheduleTripService.update(req));
    }

    @PostMapping("/delete")
    public ResponseEntity<PlaceScheduleDeleteSdo> delete(PlaceScheduleDeleteSdi req) {
        return ResponseEntity.ok(scheduleTripService.delete(req));
    }

    @PostMapping("/self")
    public ResponseEntity<PlaceScheduleSelfSdo> self(PlaceScheduleSelfSdi req) {
        return ResponseEntity.ok(scheduleTripService.self(req));
    }

    @PostMapping("/is-complete")
    public ResponseEntity<PlaceCompleteSdo> isComplete(PlaceCompleteSdi req){
        return ResponseEntity.ok(scheduleTripService.isComplete(req));
    }

    @PostMapping("/on-date")
    public ResponseEntity<List<PlaceScheduleSelfSdo>> placesInScheduleOnDate(PlaceScheduleJoinSdi req){
        return ResponseEntity.ok(scheduleTripService.placesInScheduleOnDate(req));
    }
}
