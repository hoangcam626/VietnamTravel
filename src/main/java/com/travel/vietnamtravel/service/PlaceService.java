package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.place.sdi.*;
import com.travel.vietnamtravel.dto.place.sdo.*;
import com.travel.vietnamtravel.entity.Place;

public interface PlaceService {

    PlaceCreateSdo create(PlaceCreateSdi req);

    PlaceDeleteSdo delete(PlaceDeleteSdi req);

    PlaceUpdateSdo update(PlaceUpdateSdi req);

    PlaceSelfSdo self(PlaceSelfSdi req);

    Place getPlace(Long id);

    PlaceRatingSdo showRating(PlaceRatingSdi req);
}
