package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.relationship.PlaceSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaceScheduleRepo extends JpaRepository<PlaceSchedule, Long> {

    @Query("SELECT ps.id FROM PlaceSchedule ps WHERE ps.scheduleId =:scheduleId")
    List<Long> findByScheduleId(Long scheduleId);
}
