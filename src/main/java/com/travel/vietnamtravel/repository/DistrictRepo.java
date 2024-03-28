package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.administrative.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepo extends JpaRepository<District, Long> {
    District findByCode(String code);
}
