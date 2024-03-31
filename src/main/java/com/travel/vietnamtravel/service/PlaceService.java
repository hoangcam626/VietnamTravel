package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.place.sdi.PlaceCreateSdi;
import com.travel.vietnamtravel.dto.place.sdi.PlaceDeleteSdi;
import com.travel.vietnamtravel.dto.place.sdi.PlaceSelfSdi;
import com.travel.vietnamtravel.dto.place.sdi.PlaceUpdateSdi;
import com.travel.vietnamtravel.dto.place.sdo.PlaceCreateSdo;
import com.travel.vietnamtravel.dto.place.sdo.PlaceDeleteSdo;
import com.travel.vietnamtravel.dto.place.sdo.PlaceSelfSdo;
import com.travel.vietnamtravel.dto.place.sdo.PlaceUpdateSdo;

public interface PlaceService {

    PlaceCreateSdo create(PlaceCreateSdi req);

    PlaceDeleteSdo delete(PlaceDeleteSdi req);

    PlaceUpdateSdo update(PlaceUpdateSdi req);

    PlaceSelfSdo self(PlaceSelfSdi req);
}
