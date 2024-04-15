package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.place.sdi.PlaceDeleteSdi;
import com.travel.vietnamtravel.dto.placeimage.sdi.PlaceImageCreateSdi;
import com.travel.vietnamtravel.dto.placeimage.sdo.PlaceImageCreateSdo;
import com.travel.vietnamtravel.dto.placeimage.sdo.PlaceImageDeleteSdo;

public interface PlaceImageService {
    PlaceImageCreateSdo create(PlaceImageCreateSdi req);

//    PlaceImageUpdateSdo update(PlaceImageUpdateSdi req);

    PlaceImageDeleteSdo delete(PlaceDeleteSdi req);
}
