package com.dotoku.carhop.repository;

import com.dotoku.carhop.entity.HopUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<HopUser, Long> {

}
