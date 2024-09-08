package com.dotoku.carhop.controller;

import com.dotoku.carhop.dto.HopRequestDto;
import com.dotoku.carhop.dto.HopSessionDto;
import com.dotoku.carhop.dto.ReviewDto;
import com.dotoku.carhop.service.RequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User Request controller")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/request")
public class RequestController {

    private final RequestService requestService;

    @Operation(summary = "Send a request to join a user", responses = {
            @ApiResponse(responseCode = "200", description = "Request sent Successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request, unable to send request.") })
    @PostMapping()
    public ResponseEntity<HopRequestDto> sendRequest(@Valid @RequestBody HopRequestDto hopRequestDto) {
        return requestService.sendRequest(hopRequestDto);
    }

    @Operation(summary = "Cancel request", responses = {
            @ApiResponse(responseCode = "200", description = "Request canceled Successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request, unable get reviews of user.") })
    @DeleteMapping(path = "/{requestId}")
    public ResponseEntity<String> cancelRequest(@PathVariable Long requestId) {
        return requestService.cancelRequest(requestId);
    }


}
