package com.dotoku.carhop.controller;

import com.dotoku.carhop.dto.HopUserDto;
import com.dotoku.carhop.dto.HopUserRequestDto;
import com.dotoku.carhop.dto.LoginResponseDto;
import com.dotoku.carhop.dto.RoleDto;
import com.dotoku.carhop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CarHop User controller")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/auth")
public class AuthController {

    private final UserService userService;

    @Operation(summary = "Register a new user", responses = {
            @ApiResponse(responseCode = "200", description = "User Added Successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request, unable to register the user data.") })
    @PostMapping("/register")
    public ResponseEntity<HopUserDto> registerUser(@Valid @RequestBody HopUserRequestDto hopUserDto) {
        return userService.addUser(hopUserDto);
    }

    @Operation(summary = "Login with id and password", responses = {
            @ApiResponse(responseCode = "200", description = "User Logged in Successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request, Id or Password didn't match.") })
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestParam String email, @RequestParam String password) {
        return userService.loginUser(email, password);
    }

    @Operation(summary = "Set role to User", responses = {
            @ApiResponse(responseCode = "200", description = "User Logged in Successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request, Id or Password didn't match.") })
    @PostMapping("/role")
    public ResponseEntity<RoleDto> loginUser(@RequestBody RoleDto roleDto) {
        return userService.assignRole(roleDto);
    }

}

