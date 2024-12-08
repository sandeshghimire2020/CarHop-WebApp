package com.dotoku.carhop.repository;

import com.dotoku.carhop.dto.HopRequestDto;
import com.dotoku.carhop.entity.HopRequest;
import com.dotoku.carhop.entity.HopSession;
import com.dotoku.carhop.entity.HopUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<HopRequest, Long> {

    @Query("SELECT r FROM HopRequest r WHERE r.hopUser.id = :userId")
    List<HopRequest> findByUserId(@Param("userId") Long userId);


}
