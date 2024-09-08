package com.dotoku.carhop.service;

import com.dotoku.carhop.dto.HopSessionDto;
import com.dotoku.carhop.dto.SessionResponseDto;
import com.dotoku.carhop.entity.ExpirationDuration;
import com.dotoku.carhop.entity.HopSession;
import com.dotoku.carhop.entity.HopUser;
import com.dotoku.carhop.repository.SessionRepository;
import com.dotoku.carhop.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    public ResponseEntity<SessionResponseDto> startSession(HopSessionDto hopSessionDto, ExpirationDuration expirationDuration){

        //call microservice user to get User info and vehical info

        HopSession hopSession = new HopSession();

        HopUser hopUser = userRepository.findById(hopSessionDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        hopSession.setAvailableSeats(hopSessionDto.getAvailableSeats());
        hopSession.setOriginAddress(hopSessionDto.getOriginAddress());
        hopSession.setOriginCity(hopSessionDto.getOriginCity());
        hopSession.setOriginZip(hopSessionDto.getOriginZip());
        hopSession.setDestinationAddress(hopSessionDto.getDestinationAddress());
        hopSession.setDestinationCity(hopSessionDto.getDestinationCity());
        hopSession.setDestinationZip(hopSessionDto.getDestinationZip());
        hopSession.setReturnBack(hopSessionDto.getReturnBack());
        hopSession.setReturnTime(hopSessionDto.getReturnTime());

        //time right now
        LocalDateTime localDateTime = LocalDateTime.now();
        hopSession.setTime(localDateTime);

        //expires at
        hopSession.setExpiresAt(localDateTime.plusMinutes(expirationDuration.getMinutes()));

        //store user
        hopSession.setHopUser(hopUser);

        sessionRepository.save(hopSession);

        SessionResponseDto sessionResponseDto = new SessionResponseDto();
        sessionResponseDto.setSessionId(hopSession.getId());
        sessionResponseDto.setStartAt(hopSession.getTime());
        sessionResponseDto.setExpireAt(hopSession.getExpiresAt());
        sessionResponseDto.setStartedBy(hopUser.getId());

        return ResponseEntity.ok(sessionResponseDto);
    }

    public List<HopSession> getSessionsWithFilters(
            String originAddress, String originCity, String originState, String originZip,
            String destinationAddress, String destinationCity, String destinationState, String destinationZip
    ) {
        LocalDateTime currentDateTime = LocalDateTime.now();

        //TODO: if everything is null getAllSessions should I do it?

        return sessionRepository.findByFilters(
                originAddress,
                originCity,
                originState,
                originZip,
                destinationAddress,
                destinationCity,
                destinationState,
                destinationZip,
                currentDateTime
        );
    }

}
