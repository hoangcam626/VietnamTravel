package com.travel.vietnamtravel.controller;

import com.travel.vietnamtravel.dto.likeplace.sdi.LikePlaceCreateSdi;
import com.travel.vietnamtravel.dto.likeplace.sdi.LikePlaceDeleteSdi;
import com.travel.vietnamtravel.dto.likeplace.sdo.LikePlaceCreateSdo;
import com.travel.vietnamtravel.dto.likeplace.sdo.LikePlaceDeleteSdo;
import com.travel.vietnamtravel.dto.place.sdi.PlaceCreateSdi;
import com.travel.vietnamtravel.dto.place.sdi.PlaceDeleteSdi;
import com.travel.vietnamtravel.dto.place.sdi.PlaceSelfSdi;
import com.travel.vietnamtravel.dto.place.sdi.PlaceUpdateSdi;
import com.travel.vietnamtravel.dto.place.sdo.PlaceCreateSdo;
import com.travel.vietnamtravel.dto.place.sdo.PlaceDeleteSdo;
import com.travel.vietnamtravel.dto.place.sdo.PlaceSelfSdo;
import com.travel.vietnamtravel.dto.place.sdo.PlaceUpdateSdo;
import com.travel.vietnamtravel.service.LikePlaceService;
import com.travel.vietnamtravel.service.PlaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/place")
public class PlaceController {
    private final PlaceService placeService;
    private final LikePlaceService likePlaceService;
    @PostMapping
    public ResponseEntity<PlaceCreateSdo> create(@RequestBody PlaceCreateSdi req){
        return ResponseEntity.ok(placeService.create(req));
    }
    @PutMapping
    public ResponseEntity<PlaceUpdateSdo> update(@RequestBody PlaceUpdateSdi req){
        return ResponseEntity.ok(placeService.update(req));
    }
    @DeleteMapping
    public ResponseEntity<PlaceDeleteSdo> delete(@RequestBody PlaceDeleteSdi req){
        return ResponseEntity.ok(placeService.delete(req));
    }

    @GetMapping
    public ResponseEntity<PlaceSelfSdo> self(@RequestBody PlaceSelfSdi req){
        return ResponseEntity.ok(placeService.self(req));
    }

    @PostMapping("/like")
    public ResponseEntity<LikePlaceCreateSdo> like(@RequestBody LikePlaceCreateSdi req){
        return ResponseEntity.ok(likePlaceService.like(req));
    }

    @DeleteMapping("/unlike")
    public ResponseEntity<LikePlaceDeleteSdo> like(@RequestBody LikePlaceDeleteSdi req){
        return ResponseEntity.ok(likePlaceService.unlike(req));
    }
}
