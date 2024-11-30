package com.dotoku.carhop.service;

import com.dotoku.carhop.dto.HopSessionDto;
import com.dotoku.carhop.dto.SessionResponseDto;
import com.dotoku.carhop.dto.mapper.SessionMapper;
import com.dotoku.carhop.entity.ExpirationDuration;
import com.dotoku.carhop.entity.HopSession;
import com.dotoku.carhop.entity.HopUser;
import com.dotoku.carhop.repository.SessionRepository;
import com.dotoku.carhop.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Session;
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
    private final SessionMapper sessionMapper;

    public ResponseEntity<SessionResponseDto> startSession(HopSessionDto hopSessionDto, ExpirationDuration expirationDuration){

        //TODO: Vehical map check if it is included as a part of user or not

        HopSession hopSession = sessionMapper.mapDtoToEntity(hopSessionDto);

        HopUser hopUser = userRepository.findById(hopSessionDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        //store user
        hopSession.setHopUser(hopUser);

        //time right now
        LocalDateTime localDateTime = LocalDateTime.now();
        hopSession.setTime(localDateTime);

        //expires at
        hopSession.setExpiresAt(localDateTime.plusMinutes(expirationDuration.getMinutes()));

        sessionRepository.save(hopSession);

        SessionResponseDto sessionResponseDto = new SessionResponseDto();
        sessionResponseDto.setSessionId(hopSession.getId());
        sessionResponseDto.setStartAt(hopSession.getTime());
        sessionResponseDto.setExpireAt(hopSession.getExpiresAt());
        sessionResponseDto.setStartedBy(hopUser.getId());

        return ResponseEntity.ok(sessionResponseDto);
    }

    public ResponseEntity<HopSessionDto> updateSession(long sessionId, HopSessionDto hopSessionDto) {

        //TODO: Vehical is included in User but see if we can show default vehical

        HopSession hopSession = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("Session not found!"));
        HopUser hopUser = userRepository.findById(hopSessionDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));
        if (!hopUser.getId().equals(hopSession.getHopUser().getId())) {
           throw new IllegalStateException("User cannot be changed for a session.");
         }

        hopSession = sessionMapper.mapDtoToEntity(hopSessionDto);
        sessionRepository.save(hopSession);

        HopSessionDto responseHopSessionDto = sessionMapper.mapEntityToDto(hopSession);

        return ResponseEntity.ok(responseHopSessionDto);

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

    public ResponseEntity<String> deleteSession(long sessionId){
        HopSession hopSession = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("Session not found!"));
        sessionRepository.delete(hopSession);
        return ResponseEntity.ok("Session deleted successfully.");
    }

    public ResponseEntity<String> increaseSessionTime(long sessionId, ExpirationDuration updateTime){
        HopSession hopSession = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("Session not found!"));
        LocalDateTime localDateTime = LocalDateTime.now();
        if(!localDateTime.isAfter(hopSession.getExpiresAt().minusMinutes(10))){
            throw new IllegalStateException("Request session time increase when you have less than 10 minutes.");
        } else {
            hopSession.setExpiresAt(localDateTime.plusMinutes(updateTime.getMinutes()));
        }
        return ResponseEntity.ok("The session time is increased by " + updateTime.getMinutes() + " minutes.");
    }

}
