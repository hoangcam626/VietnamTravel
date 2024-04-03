package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.scheduletrip.sdi.ScheduleTripCreateSdi;
import com.travel.vietnamtravel.dto.scheduletrip.sdi.ScheduleTripDeleteSdi;
import com.travel.vietnamtravel.dto.scheduletrip.sdi.ScheduleTripSelfSdi;
import com.travel.vietnamtravel.dto.scheduletrip.sdi.ScheduleTripUpdateSdi;
import com.travel.vietnamtravel.dto.scheduletrip.sdo.ScheduleTripCreateSdo;
import com.travel.vietnamtravel.dto.scheduletrip.sdo.ScheduleTripDeleteSdo;
import com.travel.vietnamtravel.dto.scheduletrip.sdo.ScheduleTripSelfSdo;
import com.travel.vietnamtravel.dto.scheduletrip.sdo.ScheduleTripUpdateSdo;
import com.travel.vietnamtravel.entity.ScheduleTrip;
import com.travel.vietnamtravel.exception.CustomException;

import java.util.ArrayList;
import java.util.List;

import static com.travel.vietnamtravel.constant.Error.ERROR_NOT_EXIT;
import static com.travel.vietnamtravel.util.DataUtil.copyProperties;

public interface ScheduleTripService {
    ScheduleTripCreateSdo create(ScheduleTripCreateSdi req);

    ScheduleTripUpdateSdo update(ScheduleTripUpdateSdi req);

    ScheduleTripDeleteSdo delete(ScheduleTripDeleteSdi req);

    ScheduleTripSelfSdo self(ScheduleTripSelfSdi req);

    List<ScheduleTripSelfSdo> mySchedules();
}
