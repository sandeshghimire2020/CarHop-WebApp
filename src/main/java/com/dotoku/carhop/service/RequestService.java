package com.dotoku.carhop.service;

import com.dotoku.carhop.dto.HopRequestDto;
import com.dotoku.carhop.entity.HopRequest;
import com.dotoku.carhop.entity.HopSession;
import com.dotoku.carhop.entity.HopUser;
import com.dotoku.carhop.entity.RequestStatus;
import com.dotoku.carhop.repository.RequestRepository;
import com.dotoku.carhop.repository.ReviewRepository;
import com.dotoku.carhop.repository.SessionRepository;
import com.dotoku.carhop.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final RequestRepository requestRepository;

    public ResponseEntity<HopRequestDto> sendRequest(HopRequestDto hopRequestDto){

        HopUser hopUser = userRepository.findById(hopRequestDto.getHopUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        HopSession hopSession = sessionRepository.findById(hopRequestDto.getHopSessionId())
                .orElseThrow(() -> new EntityNotFoundException("Session Not found!"));

        if(hopSession.getExpiresAt().isBefore(LocalDateTime.now())){
            throw new RuntimeException("The session has expired. Couldn't send request!");
        }

        HopRequest hopRequest = new HopRequest();

        hopRequest.setHopUser(hopUser);
        hopRequest.setHopSession(hopSession);
        hopRequest.setRequestTime(LocalDateTime.now());
        hopRequest.setStatus((RequestStatus.PENDING).toString());

        requestRepository.save(hopRequest);
        hopRequestDto.setId(hopRequest.getId()); //setting the id for request
        hopRequestDto.setRequestTime(hopRequest.getRequestTime());

        return ResponseEntity.ok(hopRequestDto);
    }

    public ResponseEntity<String> cancelRequest(Long requestId){

        HopRequest hopRequest = requestRepository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException("Request Not found!"));

        requestRepository.delete(hopRequest);

        return ResponseEntity.ok("Request Canceled!");
    }

}
