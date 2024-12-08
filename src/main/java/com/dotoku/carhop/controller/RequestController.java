package com.dotoku.carhop.controller;

import com.dotoku.carhop.dto.HopRequestDto;
import com.dotoku.carhop.dto.HopSessionDto;
import com.dotoku.carhop.dto.ReviewDto;
import com.dotoku.carhop.entity.RequestStatus;
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
    public ResponseEntity<String> deleteRequest(@PathVariable Long requestId) {
        return requestService.deleteRequest(requestId);
    }

    @Operation(summary = "Update request Status", responses = {
            @ApiResponse(responseCode = "200", description = "Request status updated Successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request, unable get reviews of user.") })
    @PutMapping(path = "/{requestId}")
    public ResponseEntity<String> approveRequest(@PathVariable Long requestId, @RequestParam(required = true) RequestStatus requestStatus) {

        if(requestStatus.equals(RequestStatus.APPROVED)){
            return requestService.approveRequest(requestId);
        } else if (requestStatus.equals(RequestStatus.REJECTED)){
            return requestService.rejectRequest(requestId);
        } else {
            throw new IllegalStateException("The status is incorrect. Please approve or reject the request.");
        }
    }

    @Operation(summary = "Get all requests made by a user", responses = {
            @ApiResponse(responseCode = "200", description = "Success."),
            @ApiResponse(responseCode = "400", description = "Bad request, unable to get requests.")
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HopRequestDto>> getRequestOfUser(@PathVariable long userId) {
        return requestService.getRequestsByUserId(userId);
    }

    @Operation(summary = "Get a request using request IDr", responses = {
            @ApiResponse(responseCode = "200", description = "Success."),
            @ApiResponse(responseCode = "400", description = "Bad request, unable to get request.")
    })
    @GetMapping("/{requestId}")
    public ResponseEntity<HopRequestDto> getRequestByRequestId(@PathVariable long requestId) {
        return requestService.getRequestByRequestId(requestId);
    }

}
