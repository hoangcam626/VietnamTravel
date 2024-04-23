package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {
    UserInfo findByUserId(Long userId);

    @Query("select count(p) from UserInfo u INNER join Post p on p.createdBy = u.userId WHERE u.userId =:userId")
    Long countMyPost(Long userId);

    @Query("select count(r) from UserInfo u INNER join Review r on r.createdBy = u.userId WHERE u.userId =:userId")
    Long countMyReview(Long userId);

    @Query("select count(v) from UserInfo u INNER join Visit v on v.userId = u.userId WHERE u.userId =:userId")
    Long countMyVisit(Long userId);

}
