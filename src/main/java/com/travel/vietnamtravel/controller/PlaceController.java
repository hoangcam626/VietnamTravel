package com.travel.vietnamtravel.controller;

import com.travel.vietnamtravel.dto.likeplace.sdi.*;
import com.travel.vietnamtravel.dto.likeplace.sdo.*;
import com.travel.vietnamtravel.dto.place.sdi.*;
import com.travel.vietnamtravel.dto.place.sdo.*;
import com.travel.vietnamtravel.service.PlaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/place")
public class PlaceController {
    private final PlaceService placeService;
    @PostMapping
    public ResponseEntity<PlaceCreateSdo> create( PlaceCreateSdi req){
        return ResponseEntity.ok(placeService.create(req));
    }
    @PutMapping
    public ResponseEntity<PlaceUpdateSdo> update( PlaceUpdateSdi req){
        return ResponseEntity.ok(placeService.update(req));
    }
    @DeleteMapping
    public ResponseEntity<PlaceDeleteSdo> delete( PlaceDeleteSdi req){
        return ResponseEntity.ok(placeService.delete(req));
    }

    @GetMapping
    public ResponseEntity<PlaceSelfSdo> self( PlaceSelfSdi req){
        return ResponseEntity.ok(placeService.self(req));
    }

    @PostMapping("/like")
    public ResponseEntity<LikePlaceCreateSdo> like( LikePlaceCreateSdi req){
        return ResponseEntity.ok(placeService.like(req));
    }

    @DeleteMapping("/unlike")
    public ResponseEntity<LikePlaceDeleteSdo> like( LikePlaceDeleteSdi req){
        return ResponseEntity.ok(placeService.unlike(req));
    }

    @GetMapping("/favourites")
    public ResponseEntity<List<PlaceSelfSdo>> favourites(LikePlaceJoinUserSdi req){
        return ResponseEntity.ok(placeService.favorites(req));
    }
}
