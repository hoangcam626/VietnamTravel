package com.travel.vietnamtravel.controller;

import com.travel.vietnamtravel.dto.placeschedule.sdi.*;
import com.travel.vietnamtravel.dto.placeschedule.sdo.*;
import com.travel.vietnamtravel.service.PlaceScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/on-schedule")
public class PlaceScheduleController {
    private final PlaceScheduleService placeScheduleService;
    @PostMapping("/create")
    public ResponseEntity<PlaceScheduleCreateSdo> create(PlaceScheduleCreateSdi req) {
        return ResponseEntity.ok(placeScheduleService.create(req));
    }

    @PutMapping("/update")
    public ResponseEntity<PlaceScheduleUpdateSdo> update(@RequestBody PlaceScheduleUpdateSdi req) {
        return ResponseEntity.ok(placeScheduleService.update(req));
    }

    @PostMapping("/delete")
    public ResponseEntity<PlaceScheduleDeleteSdo> delete(PlaceScheduleDeleteSdi req) {
        return ResponseEntity.ok(placeScheduleService.delete(req));
    }

    @PostMapping("/self")
    public ResponseEntity<PlaceScheduleSelfSdo> self(PlaceScheduleSelfSdi req) {
        return ResponseEntity.ok(placeScheduleService.self(req));
    }

    @PostMapping("/is-complete")
    public ResponseEntity<PlaceCompleteSdo> isComplete(PlaceCompleteSdi req){
        return ResponseEntity.ok(placeScheduleService.isComplete(req));
    }

    @PostMapping("/on-date")
    public ResponseEntity<List<PlaceScheduleSelfSdo>> placesInScheduleOnDate(PlaceScheduleJoinSdi req){
        return ResponseEntity.ok(placeScheduleService.placesInScheduleOnDate(req));
    }
}
