
package com.dotoku.carhop.repository;

import com.dotoku.carhop.entity.HopSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SessionRepository extends JpaRepository<HopSession, Long> {

    @Query("SELECT h FROM HopSession h WHERE " +
            "(:originAddress IS NULL OR h.originAddress = :originAddress) AND " +
            "(:originCity IS NULL OR h.originCity = :originCity) AND " +
            "(:originState IS NULL OR h.originState = :originState) AND " +
            "(:originZip IS NULL OR h.originZip = :originZip) AND " +
            "(:destinationAddress IS NULL OR h.destinationAddress = :destinationAddress) AND " +
            "(:destinationCity IS NULL OR h.destinationCity = :destinationCity) AND " +
            "(:destinationState IS NULL OR h.destinationState = :destinationState) AND " +
            "(:destinationZip IS NULL OR h.destinationZip = :destinationZip) AND " +
            "h.expiresAt > :currentDateTime")
    List<HopSession> findByFilters(
            @Param("originAddress") String originAddress,
            @Param("originCity") String originCity,
            @Param("originState") String originState,
            @Param("originZip") String originZip,
            @Param("destinationAddress") String destinationAddress,
            @Param("destinationCity") String destinationCity,
            @Param("destinationState") String destinationState,
            @Param("destinationZip") String destinationZip,
            @Param("currentDateTime") LocalDateTime currentDateTime
    );


}
