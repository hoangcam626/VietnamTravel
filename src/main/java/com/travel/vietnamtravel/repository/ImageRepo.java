package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.media.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, Long> {
}
