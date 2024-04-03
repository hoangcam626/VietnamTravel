package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.placeschedule.sdi.*;
import com.travel.vietnamtravel.dto.placeschedule.sdo.PlaceScheduleCreateSdo;
import com.travel.vietnamtravel.dto.placeschedule.sdo.PlaceScheduleDeleteSdo;
import com.travel.vietnamtravel.dto.placeschedule.sdo.PlaceScheduleSelfSdo;
import com.travel.vietnamtravel.dto.placeschedule.sdo.PlaceScheduleUpdateSdo;

import java.util.List;

public interface PlaceScheduleService {
    PlaceScheduleCreateSdo create(PlaceScheduleCreateSdi req);

    PlaceScheduleUpdateSdo update(PlaceScheduleUpdateSdi req);

    PlaceScheduleDeleteSdo delete(PlaceScheduleDeleteSdi req);

    PlaceScheduleSelfSdo self(PlaceScheduleSelfSdi req);

    List<PlaceScheduleSelfSdo> placesInSchedule(PlaceScheduleJoinSdi req);
}
