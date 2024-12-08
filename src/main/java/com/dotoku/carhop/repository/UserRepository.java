package com.dotoku.carhop.repository;

import com.dotoku.carhop.dto.HopRequestDto;
import com.dotoku.carhop.dto.HopUserDto;
import com.dotoku.carhop.entity.HopUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<HopUser, Long> {

    Optional<HopUser> findByEmail(String email);

}
