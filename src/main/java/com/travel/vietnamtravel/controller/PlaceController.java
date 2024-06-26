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

    @PostMapping("/create")
    public ResponseEntity<PlaceCreateSdo> create(PlaceCreateSdi req) {
        return ResponseEntity.ok(placeService.create(req));
    }

    @PutMapping("/update")
    public ResponseEntity<PlaceUpdateSdo> update(PlaceUpdateSdi req) {
        return ResponseEntity.ok(placeService.update(req));
    }

    @PostMapping("/delete")
    public ResponseEntity<PlaceDeleteSdo> delete(PlaceDeleteSdi req) {
        return ResponseEntity.ok(placeService.delete(req));
    }

    @PostMapping("/self")
    public ResponseEntity<PlaceSelfSdo> self(PlaceSelfSdi req) {
        return ResponseEntity.ok(placeService.self(req));
    }

    @PostMapping("/like")
    public ResponseEntity<LikePlaceCreateSdo> like(LikePlaceCreateSdi req) {
        return ResponseEntity.ok(placeService.like(req));
    }

    @PostMapping("/unlike")
    public ResponseEntity<LikePlaceDeleteSdo> like(LikePlaceDeleteSdi req) {
        return ResponseEntity.ok(placeService.unlike(req));
    }

    @PostMapping("/favourites")
    public ResponseEntity<List<PlaceSelfSdo>> favourites(LikePlaceJoinUserSdi req) {
        return ResponseEntity.ok(placeService.favorites(req));
    }

    @PostMapping("/search")
    public ResponseEntity<List<PlaceSelfSdo>> search(PlaceSearchSdi req) {

        return ResponseEntity.ok(placeService.search(req));
    }
    @PostMapping("/rating")
    public ResponseEntity<PlaceRatingSdo> showRating(PlaceRatingSdi req) {
        return ResponseEntity.ok(placeService.showRating(req));
    }

    @PostMapping("/places/code")
    public ResponseEntity<List<PlaceSelfSdo>> getPlaces(PlaceSdi req) {
        return ResponseEntity.ok(placeService.getPlaces(req));
    }

    @GetMapping("/places")
    public ResponseEntity<List<PlaceSelfSdo>> getAll() {
        return ResponseEntity.ok(placeService.getAll());
    }
}
