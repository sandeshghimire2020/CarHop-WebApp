package com.dotoku.carhop.controller;

import com.dotoku.carhop.dto.HopSessionDto;
import com.dotoku.carhop.dto.SessionResponseDto;
import com.dotoku.carhop.entity.ExpirationDuration;
import com.dotoku.carhop.entity.HopSession;
import com.dotoku.carhop.service.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Session controller")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/session")
public class SessionController {
    
        private final SessionService sessionService;

        @Operation(summary = "Start a session for a ride", responses = {
                @ApiResponse(responseCode = "200", description = "Session started Successfully."),
                @ApiResponse(responseCode = "400", description = "Bad request, unable start session.") })
        @PostMapping()
        public ResponseEntity<SessionResponseDto> startSession(@Valid @RequestBody HopSessionDto hopSessionDto,
                                                               @RequestParam(required = false, defaultValue = "THIRTY_MIN") ExpirationDuration expireAt) {
            return sessionService.startSession(hopSessionDto, expireAt);
        }

        @Operation(summary = "Get sessions for a ride", responses = {
                @ApiResponse(responseCode = "200", description = "Sessions found Successfully."),
                @ApiResponse(responseCode = "400", description = "Bad request, unable find session.") })
        @GetMapping()
        public List<HopSession> getAllSessions(
                @RequestParam(required = false) String originAddress,
                @RequestParam(required = false) String originCity,
                @RequestParam(required = false) String originState,
                @RequestParam(required = false) String originZip,
                @RequestParam(required = false) String destinationAddress,
                @RequestParam(required = false) String destinationCity,
                @RequestParam(required = false) String destinationState,
                @RequestParam(required = false) String destinationZip
        ) {
                return sessionService.getSessionsWithFilters(
                        originAddress, originCity, originState, originZip,
                        destinationAddress, destinationCity, destinationState, destinationZip
                );
          }


          //update time on session
        //delete session
        //update other details on session


        
}
