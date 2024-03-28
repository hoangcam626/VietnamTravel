package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.administrative.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepo extends JpaRepository<Province, Long> {
    Province findByCode(String code);
}
