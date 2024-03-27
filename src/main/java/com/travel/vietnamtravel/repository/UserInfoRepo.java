package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {
    UserInfo findByUserId(Long userId);
}
