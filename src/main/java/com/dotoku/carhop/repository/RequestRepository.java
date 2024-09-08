package com.dotoku.carhop.repository;

import com.dotoku.carhop.entity.HopRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<HopRequest, Long> {
}
