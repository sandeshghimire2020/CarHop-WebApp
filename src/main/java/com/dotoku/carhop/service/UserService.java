package com.dotoku.carhop.service;

import com.dotoku.carhop.dto.*;
import com.dotoku.carhop.dto.mapper.UserMapper;
import com.dotoku.carhop.entity.HopRequest;
import com.dotoku.carhop.entity.HopUser;
import com.dotoku.carhop.entity.Review;
import com.dotoku.carhop.entity.Vehicle;
import com.dotoku.carhop.repository.UserRepository;
import com.dotoku.carhop.security.JwtUtil;
import com.dotoku.carhop.security.SecurityUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public ResponseEntity<HopUserDto> addUser(HopUserRequestDto userDto){
        HopUser hopUser = userMapper.mapDtoToEntity(userDto);
        hopUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(hopUser);
        userDto.setId(hopUser.getId());
        HopUserDto hopUserDto = userMapper.mapEntityToDto(hopUser);
        return ResponseEntity.ok(hopUserDto);
    }

    public ResponseEntity<HopUserDto> getUser(Long userId){
        HopUser hopUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));
        HopUserDto userDto = userMapper.mapEntityToDto(hopUser);
        return ResponseEntity.ok(userDto);
    }

    public ResponseEntity<HopUserDto> updateUser(HopUserDto userDto, Long userId){
        HopUser hopUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        String loggedInEmail = SecurityUtil.getLoggedInEmail();
        if (!hopUser.getEmail().equals(loggedInEmail)) {
            throw new IllegalStateException("You are not authorized to update.");
        }

        userMapper.mapDtoToEntity(userDto, hopUser);
        userRepository.save(hopUser);
        userDto.setId(hopUser.getId());
        return ResponseEntity.ok(userDto);
    }

    public ResponseEntity<String> deleteUser(Long userId) {
        HopUser hopUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        String loggedInEmail = SecurityUtil.getLoggedInEmail();
        if (!hopUser.getEmail().equals(loggedInEmail)) {
            throw new IllegalStateException("You are not authorized to delete this account.");
        }

        userRepository.delete(hopUser);
        return ResponseEntity.ok("User deleted successfully.");
    }

    public List<HopUserDto> getAllUsers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<HopUser> users = userRepository.findAll(pageable);
        List<HopUserDto> usersDto = users.stream()
                .map(userMapper::mapEntityToDto)
                .collect(Collectors.toList());
        return usersDto;
    }

    public ResponseEntity<String> updateVehicle(VehicleDto vehicleDto, long userId) {
        HopUser hopUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        String loggedInEmail = SecurityUtil.getLoggedInEmail();
        if (!hopUser.getEmail().equals(loggedInEmail)) {
            throw new IllegalStateException("You are not authorized to update this vehicle.");
        }

        Vehicle vehicle = hopUser.getVehicle();

        vehicle.setMake(vehicleDto.getMake());
        vehicle.setPlateNumber(vehicleDto.getPlateNumber());
        vehicle.setModel(vehicleDto.getModel());
        vehicle.setType(vehicleDto.getType());
        vehicle.setYear(vehicleDto.getYear());

        userRepository.save(hopUser);

        return ResponseEntity.ok("Vehicle updated successfully.");
    }

    public ResponseEntity<String> loginUser(String email, String password) {
        HopUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        // Validate password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Generate JWT
        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok(token);
    }

}
