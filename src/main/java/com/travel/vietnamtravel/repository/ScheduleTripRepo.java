package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.ScheduleTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleTripRepo extends JpaRepository<ScheduleTrip, Long> {

    @Query("SELECT st FROM ScheduleTrip st WHERE st.userId = :userID")
    List<Long> findByUserId(Long userId);
}
