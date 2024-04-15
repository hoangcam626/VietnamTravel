package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.placeschedule.sdi.PlaceScheduleCreateSdi;
import com.travel.vietnamtravel.dto.placeschedule.sdi.PlaceScheduleDeleteSdi;
import com.travel.vietnamtravel.dto.placeschedule.sdi.PlaceScheduleSelfSdi;
import com.travel.vietnamtravel.dto.placeschedule.sdi.PlaceScheduleUpdateSdi;
import com.travel.vietnamtravel.dto.placeschedule.sdo.PlaceScheduleCreateSdo;
import com.travel.vietnamtravel.dto.placeschedule.sdo.PlaceScheduleDeleteSdo;
import com.travel.vietnamtravel.dto.placeschedule.sdo.PlaceScheduleSelfSdo;
import com.travel.vietnamtravel.dto.placeschedule.sdo.PlaceScheduleUpdateSdo;
import com.travel.vietnamtravel.dto.scheduletrip.sdi.ScheduleTripCreateSdi;
import com.travel.vietnamtravel.dto.scheduletrip.sdi.ScheduleTripDeleteSdi;
import com.travel.vietnamtravel.dto.scheduletrip.sdi.ScheduleTripSelfSdi;
import com.travel.vietnamtravel.dto.scheduletrip.sdi.ScheduleTripUpdateSdi;
import com.travel.vietnamtravel.dto.scheduletrip.sdo.ScheduleTripCreateSdo;
import com.travel.vietnamtravel.dto.scheduletrip.sdo.ScheduleTripDeleteSdo;
import com.travel.vietnamtravel.dto.scheduletrip.sdo.ScheduleTripSelfSdo;
import com.travel.vietnamtravel.dto.scheduletrip.sdo.ScheduleTripUpdateSdo;

import java.util.List;

public interface ScheduleTripService {
    ScheduleTripCreateSdo create(ScheduleTripCreateSdi req);

    ScheduleTripUpdateSdo update(ScheduleTripUpdateSdi req);

    ScheduleTripDeleteSdo delete(ScheduleTripDeleteSdi req);

    ScheduleTripSelfSdo self(ScheduleTripSelfSdi req);

    PlaceScheduleCreateSdo createPlace(PlaceScheduleCreateSdi req);

    PlaceScheduleUpdateSdo updatePlace(PlaceScheduleUpdateSdi req);

    PlaceScheduleDeleteSdo deletePlace(PlaceScheduleDeleteSdi req);

    PlaceScheduleSelfSdo selfPlace(PlaceScheduleSelfSdi req);

    List<ScheduleTripSelfSdo> mySchedules();
}
