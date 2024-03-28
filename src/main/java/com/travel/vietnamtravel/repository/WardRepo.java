package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.administrative.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WardRepo extends JpaRepository<Ward, Long> {
    Ward findByCode(String code);
}
