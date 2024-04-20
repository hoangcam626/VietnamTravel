package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.placeschedule.sdi.*;
import com.travel.vietnamtravel.dto.placeschedule.sdo.*;

import java.util.List;

public interface PlaceScheduleService {
    PlaceScheduleCreateSdo create(PlaceScheduleCreateSdi req);

    PlaceScheduleUpdateSdo update(PlaceScheduleUpdateSdi req);

    PlaceScheduleDeleteSdo delete(PlaceScheduleDeleteSdi req);

    PlaceScheduleSelfSdo self(PlaceScheduleSelfSdi req);

    PlaceCompleteSdo isComplete(PlaceCompleteSdi req);

    List<PlaceScheduleSelfSdo> placesInScheduleOnDate(PlaceScheduleJoinSdi req);
}
