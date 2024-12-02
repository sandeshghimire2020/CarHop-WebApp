package com.dotoku.carhop.controller;

import com.dotoku.carhop.dto.HopUserDto;
import com.dotoku.carhop.dto.VehicleDto;
import com.dotoku.carhop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@Tag(name = "CarHop User controller")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Add a new user", responses = {
            @ApiResponse (responseCode = "200", description = "User Added Successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request, unable to process user data.") })
    @PostMapping()
    public ResponseEntity<HopUserDto> addUser(@Valid @RequestBody HopUserDto hopUserDto) {
        return userService.addUser(hopUserDto);
    }

    @Operation(summary = "Get all users", responses = {
            @ApiResponse(responseCode = "200", description = "Success."),
            @ApiResponse(responseCode = "400", description = "Bad request, unable to process data.")
    })

    @GetMapping
    public ResponseEntity<List<HopUserDto>> getAllUsers(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        List<HopUserDto> users = userService.getAllUsers(pageNumber, pageSize);
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Get user", responses = {
            @ApiResponse (responseCode = "200", description = "Get User Success."),
            @ApiResponse(responseCode = "400", description = "Bad request, unable to process user data.") })
    @GetMapping(path = "/{userId}")
    public ResponseEntity<HopUserDto> getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @Operation(summary = "Update a user", responses = {
            @ApiResponse (responseCode = "200", description = "User updated Successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request, unable to process user data.") })
    @PutMapping(path = "/{userId}")
    public ResponseEntity<HopUserDto> updateUser(@Valid @RequestBody HopUserDto hopUserDto, @PathVariable Long userId) {
        return userService.updateUser(hopUserDto, userId);
    }

    @Operation(summary = "Delete a new user", responses = {
            @ApiResponse (responseCode = "200", description = "User deleted Successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request, unable to process user data.") })
    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }

    @Operation(summary = "Update a vehicle", responses = {
            @ApiResponse (responseCode = "200", description = "Vehical updated Successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request, unable to process user data.") })
    @PutMapping(path = "/{userId}/vehicle")
    public ResponseEntity<String> updateUserVehicle(@Valid @RequestBody VehicleDto vehicleDto, @PathVariable Long userId) {
        return userService.updateVehicle(vehicleDto, userId);
    }


}
