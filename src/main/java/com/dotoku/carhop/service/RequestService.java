package com.dotoku.carhop.service;

import com.dotoku.carhop.dto.HopRequestDto;
import com.dotoku.carhop.entity.HopRequest;
import com.dotoku.carhop.entity.HopSession;
import com.dotoku.carhop.entity.HopUser;
import com.dotoku.carhop.entity.RequestStatus;
import com.dotoku.carhop.repository.RequestRepository;
import com.dotoku.carhop.repository.SessionRepository;
import com.dotoku.carhop.repository.UserRepository;
import com.dotoku.carhop.security.SecurityUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public ResponseEntity<String> deleteRequest(Long requestId){

        HopRequest hopRequest = requestRepository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException("Request Not found!"));

        String loggedInEmail = SecurityUtil.getLoggedInEmail();
        if (!hopRequest.getHopUser().getEmail().equals(loggedInEmail)) {
            throw new IllegalStateException("You are not authorized to delete this request.");
        }

        requestRepository.delete(hopRequest);

        return ResponseEntity.ok("Request Canceled!");
    }

    public ResponseEntity<String> approveRequest(Long requestId){

        HopRequest hopRequest = requestRepository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException("Request Not found!"));

        String loggedInEmail = SecurityUtil.getLoggedInEmail();
        if (!hopRequest.getHopSession().getHopUser().getEmail().equals(loggedInEmail)) {
            throw new IllegalStateException("You are not authorized to approve this session.");
        }

        hopRequest.setStatus(RequestStatus.APPROVED.toString());

        requestRepository.save(hopRequest);

        return ResponseEntity.ok("Request Approved!");
    }

    public ResponseEntity<String> rejectRequest(Long requestId){

        HopRequest hopRequest = requestRepository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException("Request Not found!"));

        String loggedInEmail = SecurityUtil.getLoggedInEmail();
        if (!hopRequest.getHopSession().getHopUser().getEmail().equals(loggedInEmail)) {
            throw new IllegalStateException("You are not authorized to reject this request.");
        }

        hopRequest.setStatus(RequestStatus.REJECTED.toString());
        requestRepository.delete(hopRequest);

        return ResponseEntity.ok("Request Rejected!");
    }

    public ResponseEntity<List<HopRequestDto>> getRequestsByUserId(Long userId) {
        String loggedInEmail = SecurityUtil.getLoggedInEmail();

        HopUser hopUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        if (!hopUser.getEmail().equals(loggedInEmail)) {
            throw new IllegalStateException("You are not authorized to view these requests.");
        }

        List<HopRequest> requests = requestRepository.findByUserId(userId);

        List<HopRequestDto> hopRequestDtos = requests.stream()
                .map(this::mapRequestEntityToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(hopRequestDtos);
    }


    public ResponseEntity<HopRequestDto> getRequestByRequestId(Long requestId) {
        String loggedInEmail = SecurityUtil.getLoggedInEmail();

        HopRequest hopRequest = requestRepository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException("Request not found!"));

        if (!hopRequest.getHopUser().getEmail().equals(loggedInEmail)) {
            throw new IllegalStateException("You are not authorized to view these requests.");
        }

        HopRequestDto hopRequestDto = mapRequestEntityToDto(hopRequest);

        return ResponseEntity.ok(hopRequestDto);
    }

    public HopRequestDto mapRequestEntityToDto(HopRequest hopRequest){
        HopRequestDto hopRequestDto = new HopRequestDto();
        hopRequestDto.setHopSessionId(hopRequest.getHopSession().getId());
        hopRequestDto.setHopUserId(hopRequest.getHopUser().getId());
        hopRequestDto.setRequestTime(hopRequest.getRequestTime());
        hopRequestDto.setStatus(hopRequest.getStatus());
        hopRequestDto.setId(hopRequest.getId());
        return hopRequestDto;
    }

}
